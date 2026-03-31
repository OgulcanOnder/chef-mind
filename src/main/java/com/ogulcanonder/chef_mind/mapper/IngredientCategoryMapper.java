package com.ogulcanonder.chef_mind.mapper;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientCategoryMapper {
    IngredientCategory toIngredientCategory(DtoIngredientCategoryRequest dtoIngredientCategoryRequest);
    DtoIngredientCategoryResponse toDtoIngredientCategoryResponse(IngredientCategory ingredientCategory);
    List<IngredientCategory> toIngredientCategoryList(List<DtoIngredientCategoryRequest> dtoIngredientCategoryRequestList);
    List<DtoIngredientCategoryResponse> toDtoIngredientCategoryListResponse(List<IngredientCategory> ingredientCategoryList);
}
