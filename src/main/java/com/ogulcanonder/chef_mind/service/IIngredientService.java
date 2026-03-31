package com.ogulcanonder.chef_mind.service;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;

import java.util.List;

public interface IIngredientService {
    public DtoIngredientResponse createIngredient(DtoIngredientRequest dtoIngredientRequest);
    public List<DtoIngredientResponse>getAllIngredient();
    public  DtoIngredientResponse findByIngredient(Long id);
    public DtoIngredientResponse updateIngredient(DtoIngredientRequest dtoIngredientRequest, Long id);
    public String deleteIngredient (Long id);
}
