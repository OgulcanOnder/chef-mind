package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.model.IngredientCategory;

import java.util.List;

public interface IIngredientCategoryService {
    public DtoIngredientCategoryResponse create(DtoIngredientCategoryRequest dtoIngredientCategoryRequest);
    public List<DtoIngredientCategoryResponse> getAll();
    public IngredientCategory findById(Long id);
    public void updateById(Long id, DtoIngredientCategoryRequest dtoIngredientCategoryRequest);
    public void deleteById(Long id);
}
