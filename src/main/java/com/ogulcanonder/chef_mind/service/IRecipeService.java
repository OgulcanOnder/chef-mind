package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;

import java.util.List;

public interface IRecipeService {
    public DtoRecipeResponse create(DtoRecipeRequest dtoRecipeRequest);

    public List<DtoRecipeResponse> getAll();

    public DtoRecipeResponse findById(Long id);

    public void updateById(DtoRecipeRequest dtoRecipeRequest, Long id);

    public void deleteById(Long id);

    public Recipe findRecipeById(Long id);
}
