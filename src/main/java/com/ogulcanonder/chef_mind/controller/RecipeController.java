package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.ai.RecipeAiService;
import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeAiResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.service.IRecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {

    private final IRecipeService recipeService;
    private final RecipeAiService recipeAiService;

    public RecipeController(IRecipeService recipeService, RecipeAiService recipeAiService) {
        this.recipeService = recipeService;
        this.recipeAiService = recipeAiService;
    }

    @PostMapping
    public ResponseEntity<DtoRecipeResponse> createRecipe(@Valid @RequestBody DtoRecipeRequest dtoRecipeRequest) {
        DtoRecipeResponse recipeResponse = recipeService.create(dtoRecipeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeResponse);
    }

    @GetMapping
    public ResponseEntity<List<DtoRecipeResponse>> getAllRecipe() {
        List<DtoRecipeResponse> recipeResponseList = recipeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(recipeResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoRecipeResponse> findByRecipe(@PathVariable(name = "id") Long id) {
        DtoRecipeResponse recipeResponse = recipeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recipeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRecipe(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody DtoRecipeRequest dtoRecipeRequest) {
        recipeService.updateById(dtoRecipeRequest, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable(name = "id") Long id) {
        recipeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/ai")
    public ResponseEntity<DtoRecipeAiResponse>generateRecipe(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(recipeAiService.generateRecipeAI(id));
    }
}
