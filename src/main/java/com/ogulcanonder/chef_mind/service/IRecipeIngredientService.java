package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;

import java.util.List;

public interface IRecipeIngredientService {
    public DtoRecipeIngredientResponse create(DtoRecipeIngredientRequest dtoRecipeIngredientRequest);
    public void update(DtoRecipeIngredientRequest dtoRecipeIngredientRequest, Long id);
    public void delete(Long id);
}