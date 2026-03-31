package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;

public interface IRecipeIngredientService {
    public DtoRecipeIngredientResponse create(DtoRecipeIngredientRequest dtoRecipeIngredientRequest);
    public DtoRecipeIngredientResponse update(DtoRecipeIngredientRequest dtoRecipeIngredientRequest, Long id);
    public void delete(Long id);
}