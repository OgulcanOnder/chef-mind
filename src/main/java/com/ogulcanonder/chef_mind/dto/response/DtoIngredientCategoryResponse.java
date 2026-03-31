package com.ogulcanonder.chef_mind.dto.response;

import com.ogulcanonder.chef_mind.model.Ingredient;

import java.util.List;

public class DtoIngredientCategoryResponse {
    private Long id;
    private String ingredientCategoryName;

    public String getIngredientCategoryName() {
        return ingredientCategoryName;
    }

    public void setIngredientCategoryName(String ingredientCategoryName) {
        this.ingredientCategoryName = ingredientCategoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
