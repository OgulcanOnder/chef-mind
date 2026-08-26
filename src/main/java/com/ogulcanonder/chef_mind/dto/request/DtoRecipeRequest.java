package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoRecipeRequest(

        @NotBlank(message = "Recipe name cannot be empty")
        @Size(max = 255, message = "Recipe name cannot exceed 255 characters")
        String name

) {
}
