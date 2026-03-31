package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.ai.RecipeAiService;
import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.exception.ResourceNotUniqueException;
import com.ogulcanonder.chef_mind.mapper.RecipeMapper;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.repository.RecipeRepository;
import com.ogulcanonder.chef_mind.service.IRecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public DtoRecipeResponse createRecipe(DtoRecipeRequest dtoRecipeRequest) {
        if (recipeRepository.existsByNameIgnoreCase(dtoRecipeRequest.getName())) {
            throw new ResourceNotUniqueException("Recipe Already Exists");
        }
        Recipe recipe = recipeMapper.toRecipe(dtoRecipeRequest);
        return recipeMapper.toDtoRecipeResponse(recipeRepository.save(recipe));
    }

    @Override
    public List<DtoRecipeResponse> getAllRecipe() {
        List<Recipe> recipeList = recipeRepository.findAll();
        if (recipeList.isEmpty()) {
            throw new ResourceNotFoundException("Recipe Not Found");
        }
        return recipeMapper.toDtoRecipeResponseList(recipeList);
    }

    @Override
    public DtoRecipeResponse findByRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Id: " + id));
        return recipeMapper.toDtoRecipeResponse(recipe);
    }

    @Override
    @Transactional
    public DtoRecipeResponse updateRecipe(DtoRecipeRequest dtoRecipeRequest, Long id) {
        DtoRecipeResponse recipe = findByRecipe(id);
        if (recipe == null) {
            throw new ResourceNotFoundException("Not Found Id: " + id);
        }
        Recipe updateRecipe = recipeMapper.toRecipeDelete(recipe);
        recipeMapper.updateFromRecipe(dtoRecipeRequest, updateRecipe);
        Recipe saveRecipe = recipeRepository.save(updateRecipe);
        return recipeMapper.toDtoRecipeResponse(saveRecipe);
    }

    @Override
    public String deleteRecipe(Long id) {
        if (findByRecipe(id) == null) {
            throw new ResourceNotFoundException("Not Found Id: " + id);
        }
        Recipe deleteRecipe = recipeMapper.toRecipeDelete(findByRecipe(id));
        recipeRepository.delete(deleteRecipe);
        return "Component with ID:" + id + "was DELETED";
    }

}
