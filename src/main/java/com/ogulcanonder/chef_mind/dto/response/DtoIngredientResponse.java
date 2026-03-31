package com.ogulcanonder.chef_mind.dto.response;

import com.ogulcanonder.chef_mind.model.IngredientCategory;

import java.util.List;

public class DtoIngredientResponse {
    private Long id;
    private String name;
    private Long ingredientCategoryId;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIngredientCategoryId() {
        return ingredientCategoryId;
    }

    public void setIngredientCategoryId(Long ingredientCategoryId) {
        this.ingredientCategoryId = ingredientCategoryId;
    }
}
