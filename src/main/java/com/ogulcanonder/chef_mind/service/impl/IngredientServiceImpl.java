package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.mapper.IngredientMapper;
import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import com.ogulcanonder.chef_mind.repository.IngredientRepository;
import com.ogulcanonder.chef_mind.service.IIngredientCategoryService;
import com.ogulcanonder.chef_mind.service.IIngredientService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class IngredientServiceImpl implements IIngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;
    private final IIngredientCategoryService ingredientCategoryService;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper, IIngredientCategoryService ingredientCategoryService) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
        this.ingredientCategoryService = ingredientCategoryService;
    }

    @Override
    public DtoIngredientResponse createIngredient(DtoIngredientRequest dtoIngredientRequest) {
        try {
            Ingredient ingredient = ingredientMapper.toIngredient(dtoIngredientRequest);
            IngredientCategory ingredientCategory = ingredientCategoryService.getIngredientCategoryById(dtoIngredientRequest.getIngredientCategoryId());
            ingredient.setIngredientCategory(ingredientCategory);
            return ingredientMapper.toDtoIngredientResponse(ingredientRepository.save(ingredient));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Ingredient already exists");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.");
        }
    }

    @Override
    public List<DtoIngredientResponse> getAllIngredient() {
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        if (ObjectUtils.isEmpty(ingredientList)) {
            throw new ResourceNotFoundException("Ingredient Not Found");
        }
        return ingredientMapper.toDtoIngredientListResponse(ingredientList);
    }

    @Override
    public DtoIngredientResponse findByIngredient(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Id:" + id));
        return ingredientMapper.toDtoIngredientResponse(ingredient);
    }

    @Override
    public void updateIngredient(DtoIngredientRequest dtoIngredientRequest, Long id) {
        ingredientRepository.updateIngredient(id, dtoIngredientRequest.getName(), dtoIngredientRequest.getIngredientCategoryId());
    }

    @Override
    public void deleteIngredient(Long id) {
        int deletedRows = ingredientRepository.deleteIngredient(id);
        if (deletedRows == 0) {
            throw new ResourceNotFoundException("Ingredient Not Found");
        }
    }
}
