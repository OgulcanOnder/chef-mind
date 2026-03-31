package com.ogulcanonder.chef_mind.service.impl;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;
import com.ogulcanonder.chef_mind.exception.ResourceNotFoundException;
import com.ogulcanonder.chef_mind.exception.ResourceNotUniqueException;
import com.ogulcanonder.chef_mind.mapper.IngredientMapper;
import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import com.ogulcanonder.chef_mind.repository.IngredientCategoryRepository;
import com.ogulcanonder.chef_mind.repository.IngredientRepository;
import com.ogulcanonder.chef_mind.service.IIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IIngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientCategoryRepository ingredientCategoryRepository, IngredientMapper ingredientMapper){
        this.ingredientRepository=ingredientRepository;
        this.ingredientCategoryRepository=ingredientCategoryRepository;
        this.ingredientMapper=ingredientMapper;
    }
    @Override
    public DtoIngredientResponse createIngredient(DtoIngredientRequest dtoIngredientRequest){
        if (ingredientRepository.existsByNameIgnoreCase(dtoIngredientRequest.getName())){
            throw  new ResourceNotUniqueException("Ingredient Already Exits");
        }
        Ingredient ingredient=ingredientMapper.toIngredient(dtoIngredientRequest);
        IngredientCategory ingredientCategory=ingredientCategoryRepository.findById(dtoIngredientRequest.getIngredientCategoryId())
                .orElseThrow(()-> new ResourceNotUniqueException("Ingredient Category Not Found With Id:" +dtoIngredientRequest.getIngredientCategoryId()));
        ingredient.setIngredientCategory(ingredientCategory);
        return ingredientMapper.toDtoIngredientResponse(ingredientRepository.save(ingredient));
    }

    @Override
    public List<DtoIngredientResponse> getAllIngredient() {
        List<Ingredient> ingredientList=ingredientRepository.findAll();
        if (ingredientList.isEmpty()){
            throw  new ResourceNotFoundException("Ingredient Not Found");
        }
        return ingredientMapper.toDtoIngredientListResponse(ingredientList);
    }

    @Override
    public DtoIngredientResponse findByIngredient(Long id) {
        Ingredient ingredient=ingredientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not Found Id:"+id));
        return ingredientMapper.toDtoIngredientResponse(ingredient);
    }

    @Override
    public DtoIngredientResponse updateIngredient(DtoIngredientRequest dtoIngredientRequest, Long id) {
        Ingredient ingredient=ingredientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not Found Id:"+id));
        ingredientMapper.updateFromRequest(dtoIngredientRequest,ingredient);
        Ingredient updateIngredient=ingredientRepository.save(ingredient);
        return ingredientMapper.toDtoIngredientResponse(updateIngredient);
    }

    @Override
    public String deleteIngredient(Long id) {
        if (findByIngredient(id)==null){
            throw  new ResourceNotFoundException("Not Found Id:"+id);
        }
        Ingredient deleteIngredient=ingredientMapper.toIngredientResponse(findByIngredient(id));
        ingredientRepository.delete(deleteIngredient);
        return "Component with ID:"+id+"was DELETED";
    }
}
