package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotNull;

public record DtoRecipeIngredientRequest(

        @NotNull(message = "Ingredient cannot be null")
        Long ingredientId,
        @NotNull(message = "Recipe cannot be null")
        Long recipeId,
        @NotNull(message = "Required cannot be null")
        boolean required
) {
}
