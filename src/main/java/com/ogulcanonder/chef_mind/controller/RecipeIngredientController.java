package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.service.IRecipeIngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recipe-ingredients")
public class RecipeIngredientController {

    private final IRecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(IRecipeIngredientService recipeIngredientService){
        this.recipeIngredientService=recipeIngredientService;
    }

    @PostMapping
    public ResponseEntity<DtoRecipeIngredientResponse>create(@Valid @RequestBody DtoRecipeIngredientRequest dtoRecipeIngredientRequest){
        DtoRecipeIngredientResponse dtoRecipeIngredientResponse=recipeIngredientService.create(dtoRecipeIngredientRequest);
        return ResponseEntity.status(HttpStatus.OK).body(dtoRecipeIngredientResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void>update(@Valid @RequestBody DtoRecipeIngredientRequest dtoRecipeIngredientRequest,
                                                             @PathVariable(name = "id") Long id){
        recipeIngredientService.update(dtoRecipeIngredientRequest,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable(name = "id")Long id){
        recipeIngredientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Component with ID:"+id+" was DELETED");
    }
}
