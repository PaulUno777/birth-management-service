package uno.tek.birth_management_service.security.validation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uno.tek.birth_management_service.profiles.Profile;
import uno.tek.birth_management_service.profiles.ProfilesRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Slf4j
@AllArgsConstructor
@Service
public class ActivationCodesService {
    private final ActivationCodesRepository validationCodesRepository;
    private final ProfilesRepository profilesRepository;

    public ActivationCode create(Profile profile, ActivationType type, String ipAddress){
        Optional<ActivationCode> existingCode = validationCodesRepository
                .findTopByProfileAndTypeAndIsUsedFalseOrderByCreatedAtDesc(profile, type);

        existingCode.ifPresent(code -> {
            code.setUsed(true);
            validationCodesRepository.save(code);
        });

        Random random = new Random();
        int code = 100_000 + random.nextInt(900_000);
        ActivationCode activationCode = ActivationCode.builder()
                .isUsed(false)
                .profile(profile)
                .code(""+code)
                .ipAddress(ipAddress)
                .expireAt(LocalDateTime.now().plusMinutes(5))
                .type(type)
                .build();

        return validationCodesRepository.save(activationCode);
    }

    public Profile validate(Profile profile, String code, ActivationType type) {
        ActivationCode activationCode = validationCodesRepository
                .findTopByProfileAndTypeOrderByCreatedAtDesc(profile, type)
                .orElseThrow(() -> new IllegalArgumentException("Validation code not found"));

        if (activationCode.isUsed()) {
            throw new IllegalStateException("Code has already been used");
        }

        if (activationCode.getExpireAt().isBefore(LocalDateTime.now())) {
            activationCode.setUsed(true);
            validationCodesRepository.save(activationCode);
            throw new IllegalStateException("Validation code has expired");
        }

        log.debug(activationCode.getCode());
        if (!activationCode.getCode().equals(code)) {
            throw new IllegalArgumentException("Invalid validation code");
        }

        activationCode.setUsed(true);
        validationCodesRepository.save(activationCode); // mark code as used

        if (activationCode.getType() == ActivationType.EMAIL) {
            profile.setActive(true);
        } else {
            profile.setPhoneVerified(true);
        }
        profilesRepository.save(profile);
        return profile;
    }
}
