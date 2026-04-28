package com.ogulcanonder.chef_mind.mapper;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    Recipe toRecipe(DtoRecipeRequest dtoRecipeRequest);  // DTO → Entity
    @Mapping(source = "recipeIngredients", target = "ingredients")
    DtoRecipeResponse toDtoRecipeResponse(Recipe recipe);  // Entity → DTO
    Recipe toRecipeDelete(DtoRecipeResponse dtoRecipeResponse);
    List<Recipe>toRecipeList(List<DtoRecipeRequest> dtoRecipeRequestList);
    List<DtoRecipeResponse>toDtoRecipeResponseList(List<Recipe> recipeList);
    void updateFromRecipe(DtoRecipeRequest dtoRecipeRequest, @MappingTarget Recipe recipe);
    @Mapping(source = "ingredient.id", target = "id")
    @Mapping(source = "ingredient.name", target = "name")
    @Mapping(source = "ingredient.ingredientCategory.id", target = "ingredientCategoryId")
    DtoIngredientResponse toIngredientDto(RecipeIngredient recipeIngredient);
}
