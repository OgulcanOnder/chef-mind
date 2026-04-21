package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.mapper.RecipeMapper;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.repository.RecipeRepository;
import com.ogulcanonder.chef_mind.service.IRecipeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RecipeServiceImpl implements IRecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public DtoRecipeResponse create(DtoRecipeRequest dtoRecipeRequest) {
        try {
            Recipe recipe = recipeMapper.toRecipe(dtoRecipeRequest);
            return recipeMapper.toDtoRecipeResponse(recipeRepository.save(recipe));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Recipe already exists");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public List<DtoRecipeResponse> getAll() {
        List<Recipe> recipeList = recipeRepository.findAll();
        if (ObjectUtils.isEmpty(recipeList)) {
            throw new ResourceNotFoundException("Recipe Not Found");
        }
        return recipeMapper.toDtoRecipeResponseList(recipeList);
    }

    @Override
    public DtoRecipeResponse findById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Id: " + id));
        return recipeMapper.toDtoRecipeResponse(recipe);
    }

    @Override
    @Transactional
    public void updateById(DtoRecipeRequest dtoRecipeRequest, Long id) {
        try {
            recipeRepository.updateById(id, dtoRecipeRequest.getName());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Recipe already exists");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public void deleteById(Long id) {
        int deletedRows = recipeRepository.deleteRecipeById(id);
        if (deletedRows == 0) {
            throw new ResourceNotFoundException("Recipe Not Found");
        }
    }

    @Override
    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe Not Found"));
    }

}
