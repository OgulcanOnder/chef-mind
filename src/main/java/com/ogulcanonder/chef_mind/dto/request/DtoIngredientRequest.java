package com.ogulcanonder.chef_mind.dto.request;

import com.ogulcanonder.chef_mind.model.IngredientCategory;

import java.util.List;

public class DtoIngredientRequest {
    private String name;
    private Long ingredientCategoryId;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Long getIngredientCategoryId() {
        return ingredientCategoryId;
    }

    public void setIngredientCategoryId(Long ingredientCategoryId) {
        this.ingredientCategoryId = ingredientCategoryId;
    }
}
