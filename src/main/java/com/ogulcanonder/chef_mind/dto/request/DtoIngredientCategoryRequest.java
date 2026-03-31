package com.ogulcanonder.chef_mind.dto.request;

import com.ogulcanonder.chef_mind.model.Ingredient;

import java.util.List;

public class DtoIngredientCategoryRequest {
    private String ingredientCategoryName;

    public String getIngredientCategoryName() {
        return ingredientCategoryName;
    }

    public void setIngredientCategoryName(String ingredientCategoryName) {
        this.ingredientCategoryName = ingredientCategoryName;
    }
}
