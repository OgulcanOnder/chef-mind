package com.ogulcanonder.chef_mind.dto.response;

import java.util.List;

public record DtoRecipeAiResponse(
        String description,
        List<String> steps,
        List<String> alternatives
) {
}
