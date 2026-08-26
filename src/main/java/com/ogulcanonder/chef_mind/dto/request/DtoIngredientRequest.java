package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record DtoIngredientRequest(
        @NotBlank(message = "Name cannot be empty")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,
        @NotNull(message = "Ingredient category cannot be null ")
        Long ingredientCategoryId

) {
}
