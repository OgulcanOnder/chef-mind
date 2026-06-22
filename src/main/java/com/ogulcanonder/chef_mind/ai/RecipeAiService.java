package com.ogulcanonder.chef_mind.ai;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeAiRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeAiResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import com.ogulcanonder.chef_mind.repository.RecipeRepository;

import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeAiService {
    private final OllamaClient ollamaClient;
    private final RecipeRepository recipeRepository;

    public RecipeAiService(OllamaClient ollamaClient, RecipeRepository recipeRepository) {
        this.ollamaClient = ollamaClient;
        this.recipeRepository = recipeRepository;
    }

    public DtoRecipeAiResponse generateRecipeAI(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));

        List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();

        String requiredIngredients = recipeIngredients.stream()
                .filter(RecipeIngredient::isRequired)
                .map(ri -> ri.getIngredient().getName())
                .collect(Collectors.joining(", "));

        String optionalIngredients = recipeIngredients.stream()
                .filter(ri -> !ri.isRequired())
                .map(ri -> ri.getIngredient().getName())
                .collect(Collectors.joining(", "));

        String prompt = """
                Recipe Name: %s
                Ingredients:
                - Required: %s
                - Optional: %s
                Instructions:
                You are a professional chef AI that generates structured cooking recipes.
                
                  You will receive:
                
                  - A recipe name
                
                  - A list of ingredients
                
                  - Each ingredient includes a flag indicating whether it is required or optional.
                
                
                
                  Your task is to generate a cooking recipe using the given ingredients.
                
                
                
                  Rules:
                
                  1. Required ingredients MUST be used in the recipe.
                
                  2. Optional ingredients MAY be used if they logically fit the recipe.
                
                  3. Do not invent unrealistic ingredients unless absolutely necessary.
                
                  4. Steps must be clear, sequential, and easy to follow.
                
                  5. The response MUST be valid JSON.
                
                  6. Do NOT include explanations or text outside JSON.
                
                
                
                  Return the result using the following JSON format:
                
                
                
                  {
                
                    "description": "Short description of the dish",
                
                    "steps": [
                
                      "Step 1: ...",
                
                      "Step 2: ...",
                
                      "Step 3: ..."
                
                    ],
                
                    "alternatives": [
                
                      "Alternative ingredient or tip",
                
                      "Alternative cooking method",
                
                      "Flavor improvement suggestion"
                
                    ]
                    
                    Return ONLY valid JSON.
                
                    The JSON must strictly follow this structure:
                
                    {
                      "description": "string",
                      "steps": ["string"],
                      "alternatives": ["string"]
                    }
                
                    Do not include markdown.
                    Do not include explanations.
                    Do not include notes.
                    Do not include text outside JSON.
                """.formatted(recipe.getName(), requiredIngredients, optionalIngredients);

        String aiResponse = ollamaClient.generate(prompt);

        return parseAiResponse(aiResponse);
    }

    public DtoRecipeAiResponse generateRecipeByIngredients(DtoRecipeAiRequest dtoRecipeAiRequest) {
        List<String>ingredients = dtoRecipeAiRequest.getIngredients();
        String prompt = """
            You are a professional chef. Based on the following ingredients, generate a detailed step-by-step recipe.
            
            The ingredients below may be in Turkish. Translate them to English accurately before using them 
            in the recipe.
            
            Ingredients: %s
            
            Respond ONLY with a valid JSON object. No explanation, no markdown, no extra text.
            
            Use this exact JSON structure:
            {
              "description": "string - recipe name and brief description",
              "steps": [
                "Step 1: ...",
                "Step 2: ..."
              ],
              "alternatives": [
                "You can replace X with Y",
                "For a vegan version, ..."
              ]
            }
            """.formatted(ingredients);
        String aiResponse = ollamaClient.generate(prompt);
        return parseAiResponse(aiResponse);
    }

    // 🔹 parseAiResponse helper method
    private DtoRecipeAiResponse parseAiResponse(String aiResponse) {
        try {
            // Markdown temizleme
            String cleaned = aiResponse
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(cleaned, DtoRecipeAiResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }
    }

}
