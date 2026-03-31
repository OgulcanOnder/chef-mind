package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;

import java.util.List;

public interface IIngredientCategoryService {
    public DtoIngredientCategoryResponse createIngredientCategory(DtoIngredientCategoryRequest dtoIngredientCategoryRequest);
    public List<DtoIngredientCategoryResponse> getAllIngredientCategory();
}
