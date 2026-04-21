package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DtoRecipeRequest {

    @NotBlank(message = "Recipe name cannot be empty")
    @Size(max = 255,message = "Recipe name cannot exceed 255 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
