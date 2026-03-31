package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.service.IRecipeIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/recipe-ingredients")
public class RecipeIngredientController {

    private final IRecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(IRecipeIngredientService recipeIngredientService){
        this.recipeIngredientService=recipeIngredientService;
    }

    @PostMapping
    public ResponseEntity<DtoRecipeIngredientResponse>create(@RequestBody DtoRecipeIngredientRequest dtoRecipeIngredientRequest){
        DtoRecipeIngredientResponse dtoRecipeIngredientResponse=recipeIngredientService.create(dtoRecipeIngredientRequest);
        return ResponseEntity.status(HttpStatus.OK).body(dtoRecipeIngredientResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DtoRecipeIngredientResponse>update(@RequestBody DtoRecipeIngredientRequest dtoRecipeIngredientRequest,
                                                             @PathVariable(name = "id") Long id){
        DtoRecipeIngredientResponse dtoRecipeIngredientResponse=recipeIngredientService.update(dtoRecipeIngredientRequest,id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoRecipeIngredientResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable(name = "id")Long id){
        recipeIngredientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Component with ID:"+id+" was DELETED");
    }
}
