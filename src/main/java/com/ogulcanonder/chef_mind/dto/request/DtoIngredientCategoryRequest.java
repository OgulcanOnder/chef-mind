package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DtoIngredientCategoryRequest {
    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    private String ingredientCategoryName;

    public String getIngredientCategoryName() {
        return ingredientCategoryName;
    }

    public void setIngredientCategoryName(String ingredientCategoryName) {
        this.ingredientCategoryName = ingredientCategoryName;
    }
}
