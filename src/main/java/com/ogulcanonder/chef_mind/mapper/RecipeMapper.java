package com.ogulcanonder.chef_mind.mapper;

import com.ogulcanonder.chef_mind.dto.request.DtoRecipeRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeResponse;
import com.ogulcanonder.chef_mind.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    Recipe toRecipe(DtoRecipeRequest dtoRecipeRequest);  // DTO → Entity
    DtoRecipeResponse toDtoRecipeResponse(Recipe recipe);  // Entity → DTO
    Recipe toRecipeDelete(DtoRecipeResponse dtoRecipeResponse);
    List<Recipe>toRecipeList(List<DtoRecipeRequest> dtoRecipeRequestList);
    List<DtoRecipeResponse>toDtoRecipeResponseList(List<Recipe> recipeList);
    void updateFromRecipe(DtoRecipeRequest dtoRecipeRequest, @MappingTarget Recipe recipe);
}
