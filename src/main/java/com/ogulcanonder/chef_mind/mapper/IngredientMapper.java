package com.ogulcanonder.chef_mind.mapper;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientCategoryResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;
import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    @Mapping(target = "ingredientCategory",ignore = true)
    Ingredient toIngredient(DtoIngredientRequest dtoIngredientRequest);
    @Mapping(source = "ingredientCategory.id", target = "ingredientCategoryId")
    DtoIngredientResponse toDtoIngredientResponse(Ingredient ingredient);
    @Mapping(target = "ingredientCategory",ignore = true)
    Ingredient toIngredientResponse(DtoIngredientResponse dtoIngredientResponse);
    List<Ingredient>toIngredientList(List<DtoIngredientRequest> dtoIngredientRequestList);
    List<DtoIngredientResponse> toDtoIngredientListResponse(List<Ingredient> ingredientList);
    void updateFromRequest(DtoIngredientRequest dtoIngredientRequest, @MappingTarget Ingredient ingredient);

}
