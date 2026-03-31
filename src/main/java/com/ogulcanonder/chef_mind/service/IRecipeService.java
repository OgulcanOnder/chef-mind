package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;

import java.util.List;

public interface IRecipeService {
    public DtoRecipeResponse createRecipe(DtoRecipeRequest dtoRecipeRequest);
    public List<DtoRecipeResponse> getAllRecipe();
    public DtoRecipeResponse findByRecipe(Long id);
    public DtoRecipeResponse updateRecipe(DtoRecipeRequest dtoRecipeRequest, Long id);
    public String deleteRecipe(Long id);

    interface IRecipeIngredientService {
        public DtoRecipeIngredientResponse create(DtoRecipeIngredientRequest dtoRecipeIngredientRequest);
    }
}
