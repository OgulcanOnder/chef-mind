package com.ogulcanonder.chef_mind.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record DtoRegisterUserRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 50, message = "Name must be 3-50 characters long")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        @Size(min = 3, max = 50, message = "Surname must be 3-50 characters long")
        String surname,

        @NotBlank(message = "Username cannot be blank")
        @Size(min = 3, max = 50, message = "Username must be 3-50 characters long")
        String username,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Enter a valid email address")
        @Size(min = 5, max = 254, message = "Email must be 5-254 characters long")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 255, message = "Password must be least 8 characters long")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*]).*$",
                message = "Password must contain at least one digit, lowercase, uppercase, and special character")
        String password
) {
}
