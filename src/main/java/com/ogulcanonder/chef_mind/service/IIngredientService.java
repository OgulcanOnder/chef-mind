package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;
import com.ogulcanonder.chef_mind.model.Ingredient;

import java.util.List;

public interface IIngredientService {
    public DtoIngredientResponse create(DtoIngredientRequest dtoIngredientRequest);
    public List<DtoIngredientResponse>getAll();
    public  DtoIngredientResponse findById(Long id);
    public void updateNameAndCategoryId(DtoIngredientRequest dtoIngredientRequest, Long id);
    public void deleteById (Long id);
    public Ingredient findIngredientById(Long id);
}
