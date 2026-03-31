package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.exception.ResourceNotUniqueException;
import com.ogulcanonder.chef_mind.mapper.IngredientCategoryMapper;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import com.ogulcanonder.chef_mind.repository.IngredientCategoryRepository;
import com.ogulcanonder.chef_mind.service.IIngredientCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientCategoryServiceImpl implements IIngredientCategoryService {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientCategoryMapper ingredientCategoryMapper;

    public IngredientCategoryServiceImpl(IngredientCategoryRepository ingredientCategoryRepository,
                                         IngredientCategoryMapper ingredientCategoryMapper) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
        this.ingredientCategoryMapper=ingredientCategoryMapper;
    }

    @Override
    public DtoIngredientCategoryResponse createIngredientCategory(DtoIngredientCategoryRequest dtoIngredientCategoryRequest){
        if (ingredientCategoryRepository.existsByIngredientCategoryNameIgnoreCase(dtoIngredientCategoryRequest.getIngredientCategoryName())){
            throw new ResourceNotUniqueException("Ingredient Category Already Exists");
        }
        IngredientCategory ingredientCategory=ingredientCategoryMapper.toIngredientCategory(dtoIngredientCategoryRequest);
        IngredientCategory saveIngredientCategory= ingredientCategoryRepository.save(ingredientCategory);
        return ingredientCategoryMapper.toDtoIngredientCategoryResponse(saveIngredientCategory);
    }

    @Override
    public List<DtoIngredientCategoryResponse> getAllIngredientCategory(){
        List<IngredientCategory> ingredientList= ingredientCategoryRepository.findAll();
        if (ingredientList.isEmpty()){
            throw  new ResourceNotFoundException("Ingredient Category Not Found");
        }
        return ingredientCategoryMapper.toDtoIngredientCategoryListResponse(ingredientList);
    }
}
