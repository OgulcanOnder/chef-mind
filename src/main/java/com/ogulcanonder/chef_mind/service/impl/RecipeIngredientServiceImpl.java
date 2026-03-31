package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.mapper.RecipeIngredientMapper;
import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import com.ogulcanonder.chef_mind.repository.IngredientRepository;
import com.ogulcanonder.chef_mind.repository.RecipeIngredientRepository;
import com.ogulcanonder.chef_mind.repository.RecipeRepository;
import com.ogulcanonder.chef_mind.service.IRecipeIngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecipeIngredientServiceImpl implements IRecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository,
                                       RecipeRepository recipeRepository,
                                       IngredientRepository ingredientRepository,
                                       RecipeIngredientMapper recipeIngredientMapper) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientMapper = recipeIngredientMapper;
    }

    @Override
    public DtoRecipeIngredientResponse create(DtoRecipeIngredientRequest dtoRecipeIngredientRequest) {
        Recipe recipe=recipeRepository.findById(dtoRecipeIngredientRequest.getRecipeId())
                .orElseThrow(()-> new ResourceNotFoundException("Recipe Not Found"));
        Ingredient ingredient=ingredientRepository.findById(dtoRecipeIngredientRequest.getIngredientId())
                .orElseThrow(()->new ResourceNotFoundException("Ingredient Not Found"));
        RecipeIngredient entity=recipeIngredientMapper.toRecipeIngredient(dtoRecipeIngredientRequest,recipe,ingredient);
        RecipeIngredient saved=recipeIngredientRepository.save(entity);
        return recipeIngredientMapper.toDtoRecipeIngredientResponse(saved);
    }

    @Override
    public DtoRecipeIngredientResponse update(DtoRecipeIngredientRequest dtoRecipeIngredientRequest, Long id) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe Match Not Found"));

        recipeIngredientMapper.toUpdateRecipeIngredient(dtoRecipeIngredientRequest,recipeIngredient);
        RecipeIngredient updated=recipeIngredientRepository.save(recipeIngredient);
        return recipeIngredientMapper.toDtoUpdateRecipeIngredient(updated);
    }

    @Override
    public void delete(Long id) {
        RecipeIngredient recipeIngredient=recipeIngredientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Recipe Match Not Found"));
        recipeIngredientRepository.delete(recipeIngredient);
    }

}
