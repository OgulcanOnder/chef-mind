package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.ai.RecipeAiService;
import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeAiResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.service.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final IRecipeService recipeService;
    private final RecipeAiService recipeAiService;

    public RecipeController(IRecipeService recipeService, RecipeAiService recipeAiService) {
        this.recipeService = recipeService;
        this.recipeAiService = recipeAiService;
    }

    @PostMapping("/save")
    public ResponseEntity<DtoRecipeResponse> createRecipe(@RequestBody DtoRecipeRequest dtoRecipeRequest) {
        DtoRecipeResponse recipeResponse = recipeService.createRecipe(dtoRecipeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoRecipeResponse>> getAllRecipe() {
        List<DtoRecipeResponse> recipeResponseList = recipeService.getAllRecipe();
        return ResponseEntity.status(HttpStatus.OK).body(recipeResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoRecipeResponse> findByRecipe(@PathVariable(name = "id") Long id) {
        DtoRecipeResponse recipeResponse = recipeService.findByRecipe(id);
        return ResponseEntity.status(HttpStatus.OK).body(recipeResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DtoRecipeResponse> updateRecipe(@PathVariable(name = "id") Long id,
                                                          @RequestBody DtoRecipeRequest dtoRecipeRequest) {
        DtoRecipeResponse recipeResponse = recipeService.updateRecipe(dtoRecipeRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(recipeResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable(name = "id") Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.OK).body("Component with ID: " + id + " was DELETED");
    }

    @GetMapping("/{id}/ai")
    public ResponseEntity<DtoRecipeAiResponse>generateRecipe(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(recipeAiService.generateRecipeAI(id));
    }
}
