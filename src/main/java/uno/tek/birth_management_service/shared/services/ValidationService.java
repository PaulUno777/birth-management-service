package uno.tek.birth_management_service.shared.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uno.tek.birth_management_service.profiles.ProfilesRepository;

@Component
@AllArgsConstructor
public class ValidationService {

    private final ProfilesRepository profilesRepository;

    public void validateUniqueEmail(String email) throws RuntimeException {
        if (profilesRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already in use");
        }
    }

    public void validateUniquePhone(String phone) throws RuntimeException{
        if (profilesRepository.existsByPhone(phone)) {
            throw new RuntimeException("Phone number is already in use");
        }
    }
}
