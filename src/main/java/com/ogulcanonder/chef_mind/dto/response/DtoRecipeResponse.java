package com.ogulcanonder.chef_mind.dto.response;

import java.util.List;

public record DtoRecipeResponse(

        Long id,
        String name,
        List<DtoIngredientResponse> ingredients
) {
}
