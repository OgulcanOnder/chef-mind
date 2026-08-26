package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoOllamaRequest(
        @NotBlank(message = "Model cannot be empty")
        String model,
        @NotBlank(message = "Prompt cannot be empty")
        String prompt,
        @NotNull(message = "Stream cannot be null")
        boolean stream
) {
}
