package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.mapper.RecipeIngredientMapper;
import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import com.ogulcanonder.chef_mind.repository.RecipeIngredientRepository;
import com.ogulcanonder.chef_mind.service.IIngredientService;
import com.ogulcanonder.chef_mind.service.IRecipeIngredientService;
import com.ogulcanonder.chef_mind.service.IRecipeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecipeIngredientServiceImpl implements IRecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;
    private final IRecipeService recipeService;
    private final IIngredientService ingredientService;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository,
                                       RecipeIngredientMapper recipeIngredientMapper,
                                       IRecipeService recipeService,
                                       IIngredientService ingredientService) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeIngredientMapper = recipeIngredientMapper;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @Override
    public DtoRecipeIngredientResponse create(DtoRecipeIngredientRequest dtoRecipeIngredientRequest) {
        try {
            Recipe recipe = recipeService.findRecipeById(dtoRecipeIngredientRequest.getRecipeId());
            Ingredient ingredient = ingredientService.findIngredientById(dtoRecipeIngredientRequest.getIngredientId());
            RecipeIngredient recipeIngredient = recipeIngredientMapper.toRecipeIngredient(dtoRecipeIngredientRequest, recipe, ingredient);
            return recipeIngredientMapper.toDtoRecipeIngredientResponse(recipeIngredientRepository.save(recipeIngredient));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Recipe and Ingredient matching available");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }

    }

    @Override
    public void update(DtoRecipeIngredientRequest dtoRecipeIngredientRequest, Long id) {
        try {
            recipeIngredientRepository.updateById(dtoRecipeIngredientRequest.getIngredientId(), dtoRecipeIngredientRequest.getRecipeId(), dtoRecipeIngredientRequest.isRequired(), id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Recipe and Ingredient matching available");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public void delete(Long id) {
        int deletedRows = recipeIngredientRepository.deleteRecipeIngredientById(id);
        if (deletedRows == 0) {
            throw new ResourceNotFoundException("Recipe and Ingredient matching not found");
        }
    }

}
