package uno.tek.birth_management_service.profiles;

import jakarta.validation.constraints.*;

public record ProfileDTO(
        int id,

        @NotNull(message = "Civility is required")
        Civility civility,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Pattern(regexp = "^\\+?[0-9]{7,20}$", message = "Phone number is invalid")
        String phone,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
