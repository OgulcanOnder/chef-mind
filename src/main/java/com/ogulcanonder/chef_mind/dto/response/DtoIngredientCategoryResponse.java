package com.ogulcanonder.chef_mind.dto.response;

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
