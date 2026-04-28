package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.mapper.IngredientCategoryMapper;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import com.ogulcanonder.chef_mind.repository.IngredientCategoryRepository;
import com.ogulcanonder.chef_mind.service.IIngredientCategoryService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class IngredientCategoryServiceImpl implements IIngredientCategoryService {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientCategoryMapper ingredientCategoryMapper;

    public IngredientCategoryServiceImpl(IngredientCategoryRepository ingredientCategoryRepository,
                                         IngredientCategoryMapper ingredientCategoryMapper) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
        this.ingredientCategoryMapper = ingredientCategoryMapper;
    }

    @Override
    public DtoIngredientCategoryResponse create(DtoIngredientCategoryRequest dtoIngredientCategoryRequest) {
        try {
            IngredientCategory ingredientCategory = ingredientCategoryMapper.toIngredientCategory(dtoIngredientCategoryRequest);
            return ingredientCategoryMapper.toDtoIngredientCategoryResponse(ingredientCategoryRepository.save(ingredientCategory));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Ingredient Category already exists");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }

    }

    @Override
    public List<DtoIngredientCategoryResponse> getAll() {
        List<IngredientCategory> ingredientList = ingredientCategoryRepository.findAll();
        if (ObjectUtils.isEmpty(ingredientList)) {
            throw new ResourceNotFoundException("Ingredient Category Not Found");
        }
        return ingredientCategoryMapper.toDtoIngredientCategoryListResponse(ingredientList);
    }

    @Override
    public IngredientCategory findById(Long id) {
        return ingredientCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient Category Not Found"));
    }

    @Override
    public void updateById(Long id, DtoIngredientCategoryRequest dtoIngredientCategoryRequest) {
        try {
            ingredientCategoryRepository.updateById(id, dtoIngredientCategoryRequest.getIngredientCategoryName());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Ingredient Category already exists");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public void deleteById(Long id) {
        int deletedRows = ingredientCategoryRepository.deleteByIngredientCategoryId(id);
        if (deletedRows == 0) {
            throw new ResourceNotFoundException("Ingredient Category Not Found");
        }
    }
}
