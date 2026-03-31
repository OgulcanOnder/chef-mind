package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotNull;

public class DtoRecipeIngredientRequest {

    @NotNull
    private Long ingredientId;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @NotNull
    private Long recipeId;
    private boolean required;

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
