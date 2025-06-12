package uno.tek.birth_management_service.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uno.tek.birth_management_service.profiles.Profile;
import uno.tek.birth_management_service.profiles.ProfileDTO;
import uno.tek.birth_management_service.profiles.ProfileMapper;
import uno.tek.birth_management_service.profiles.ProfilesRepository;
import uno.tek.birth_management_service.security.validation.ActivationCode;
import uno.tek.birth_management_service.security.validation.ActivationCodesService;
import uno.tek.birth_management_service.security.validation.ActivationType;
import uno.tek.birth_management_service.shared.entities.Role;
import uno.tek.birth_management_service.shared.entities.RoleName;
import uno.tek.birth_management_service.shared.repositories.RolesRepository;
import uno.tek.birth_management_service.shared.services.ValidationService;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class AuthService {
    private final ActivationCodesService validationCodesService;
    private final ProfilesRepository profilesRepository;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final ProfileMapper profileMapper;


    public ProfileDTO signUp(ProfileDTO input) {
        log.info("Signing up profile with email: {}", input.email());

        validationService.validateUniqueEmail(input.email());
        validationService.validateUniquePhone(input.phone());

        String encodedPassword = passwordEncoder.encode(input.password());

        Profile profile = profileMapper.toEntity(input);
        profile.setPassword(encodedPassword);

        // Assign default user role
        Role role = rolesRepository.findByName(RoleName.ROLE_USER);
        if (role == null) {
            throw new IllegalStateException("Default user role (ROLE_USER) not configured in database");
        }
        profile.setRole(role);

        profile = this.profilesRepository.save(profile);
        validationCodesService.create(profile, ActivationType.EMAIL, null);

        return profileMapper.toDto(profile);
    }

    public  ProfileDTO activate(int userId, String code, ActivationType type){
        Optional<Profile> optionalProfile =  profilesRepository.findById(userId);

        Profile profile = optionalProfile
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile = validationCodesService.validate(profile, code, type);
        return profileMapper.toDto(profile);
    }

    public ActivationCode refreshActivation(int userId, ActivationType type) {
        Optional<Profile> optionalProfile =  profilesRepository.findById(userId);

        Profile profile = optionalProfile
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        return validationCodesService.create(profile, type, null);
    }
}
