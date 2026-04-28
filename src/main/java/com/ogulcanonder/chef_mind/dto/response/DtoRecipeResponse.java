package com.ogulcanonder.chef_mind.dto.response;

import java.util.List;

public class DtoRecipeResponse {

    private Long id;
    private String name;
    private List<DtoIngredientResponse> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DtoIngredientResponse> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<DtoIngredientResponse> ingredients) {
        this.ingredients = ingredients;
    }
}
