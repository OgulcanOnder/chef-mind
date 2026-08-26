package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DtoRecipeAiRequest(

        @NotEmpty(message = "Not ingredients cannot be empty")
        List<@NotBlank(message = "Ingredient name cannot be blank") String> ingredients
) {
}
