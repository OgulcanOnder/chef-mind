package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class DtoIngredientRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(max=255,message = "Name cannot exceed 255 characters")
    private String name;
    @NotNull(message = "Ingredient category cannot be null ")
    private Long ingredientCategoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIngredientCategoryId() {
        return ingredientCategoryId;
    }

    public void setIngredientCategoryId(Long ingredientCategoryId) {
        this.ingredientCategoryId = ingredientCategoryId;
    }
}
