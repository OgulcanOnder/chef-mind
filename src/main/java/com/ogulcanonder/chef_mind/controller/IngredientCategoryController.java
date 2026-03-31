package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.service.IIngredientCategoryService;
import com.ogulcanonder.chef_mind.service.impl.IngredientCategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient-categories")
public class IngredientCategoryController {
    private final IIngredientCategoryService ingredientCategoryService;
    public IngredientCategoryController(IIngredientCategoryService ingredientCategoryService){
        this.ingredientCategoryService = ingredientCategoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<DtoIngredientCategoryResponse>createIngredientCategory(@RequestBody DtoIngredientCategoryRequest dtoIngredientCategoryRequest){
        DtoIngredientCategoryResponse saveIngredientCategory= ingredientCategoryService.createIngredientCategory(dtoIngredientCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveIngredientCategory);
    }

    @GetMapping
    public ResponseEntity<List<DtoIngredientCategoryResponse>>getAllIngredientCategory(){
       List<DtoIngredientCategoryResponse> getAllIngredientCategory= ingredientCategoryService.getAllIngredientCategory();
       return ResponseEntity.status(HttpStatus.OK).body(getAllIngredientCategory);
    }
}
