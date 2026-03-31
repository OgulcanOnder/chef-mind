package com.ogulcanonder.chef_mind.mapper;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.request.DtoRecipeIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoRecipeIngredientResponse;
import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecipeIngredientMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "recipe",source = "recipe")
    @Mapping(target = "ingredient",source = "ingredient")
    RecipeIngredient toRecipeIngredient(DtoRecipeIngredientRequest dtoRecipeIngredientRequest,
                                        Recipe recipe,
                                        Ingredient ingredient);

    @Mapping(source = "ingredient.id", target = "ingredientId")
    @Mapping(source = "ingredient.name", target = "ingredientName")
    DtoRecipeIngredientResponse toDtoRecipeIngredientResponse(RecipeIngredient recipeIngredient);

    @Mapping(target ="id",ignore = true)
    @Mapping(target = "recipe",ignore = true)
    @Mapping(target = "ingredient",ignore = true)
    void toUpdateRecipeIngredient(DtoRecipeIngredientRequest dtoRecipeIngredientRequest,
                                  @MappingTarget RecipeIngredient recipeIngredient);

    @Mapping(source = "ingredient.id",target = "ingredientId")
    @Mapping(source = "ingredient.name",target = "ingredientName")
    @Mapping(source = "recipe.id",target = "recipeId")
    @Mapping(source = "recipe.name",target = "recipeName")
    DtoRecipeIngredientResponse toDtoUpdateRecipeIngredient(RecipeIngredient recipeIngredient);
}
