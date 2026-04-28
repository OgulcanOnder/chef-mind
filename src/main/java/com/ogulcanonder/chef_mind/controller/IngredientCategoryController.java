package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.service.IIngredientCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient-categories")
public class IngredientCategoryController {
    private final IIngredientCategoryService ingredientCategoryService;

    public IngredientCategoryController(IIngredientCategoryService ingredientCategoryService) {
        this.ingredientCategoryService = ingredientCategoryService;
    }

    @PostMapping
    public ResponseEntity<DtoIngredientCategoryResponse> createIngredientCategory(@Valid @RequestBody DtoIngredientCategoryRequest dtoIngredientCategoryRequest) {
        DtoIngredientCategoryResponse saveIngredientCategory = ingredientCategoryService.create(dtoIngredientCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveIngredientCategory);
    }

    @GetMapping
    public ResponseEntity<List<DtoIngredientCategoryResponse>> getAllIngredientCategory() {
        List<DtoIngredientCategoryResponse> getAllIngredientCategory = ingredientCategoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(getAllIngredientCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIngredientCategory(@PathVariable(name = "id") Long id, @Valid @RequestBody DtoIngredientCategoryRequest dtoIngredientCategoryRequest) {
        ingredientCategoryService.updateById(id, dtoIngredientCategoryRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientCategory(@PathVariable(name = "id") Long id) {
        ingredientCategoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
