package uno.tek.birth_management_service.shared.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String code,
        String message,
        List<FieldError> errors
) {
    public record FieldError(String field, String error) {}
}
