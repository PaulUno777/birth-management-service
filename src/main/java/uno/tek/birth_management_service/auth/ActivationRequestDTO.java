package uno.tek.birth_management_service.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import uno.tek.birth_management_service.security.validation.ActivationType;

public record ActivationRequestDTO(
        @NotBlank(message = "Activation code is required")
        @Size(min = 4, max = 10, message = "Activation code must be between 4 and 10 characters")
        String code,

        ActivationType type
) {
}
