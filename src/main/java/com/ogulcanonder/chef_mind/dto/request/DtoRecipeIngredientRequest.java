package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotNull;

public class DtoRecipeIngredientRequest {

    @NotNull(message = "Ingredient cannot be null")
    private Long ingredientId;
    @NotNull(message = "Recipe cannot be null")
    private Long recipeId;
    @NotNull(message = "Required cannot be null")
    private boolean required;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
