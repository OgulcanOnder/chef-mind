package com.ogulcanonder.chef_mind.dto.response;

public record DtoRecipeIngredientResponse(

        Long recipeId,
        String recipeName,
        Long ingredientId,
        String ingredientName,
        boolean required

) {
}
