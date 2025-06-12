package uno.tek.birth_management_service.auth;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uno.tek.birth_management_service.profiles.ProfileDTO;
import uno.tek.birth_management_service.security.validation.ActivationCode;
import uno.tek.birth_management_service.security.validation.ActivationType;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
@RestController
public class AuthController {
    private final AuthService  AuthService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public ProfileDTO create(@RequestBody ProfileDTO input) {
        return this.AuthService.signUp(input);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("{userId}/activation")
    public ProfileDTO confirmActivation(
            @PathVariable int userId,
            @Valid @RequestBody ActivationRequestDTO input
    ) {
         return this.AuthService.activate(userId, input.code(), input.type());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "{userId}/activation/refresh")
    public void resendActivationCode(
            @PathVariable int userId,
            @NonNull @RequestParam ActivationType type) {
        this.AuthService.refreshActivation(userId, type);
    }
}
