package uno.tek.birth_management_service.security.validation;

import org.springframework.data.jpa.repository.JpaRepository;
import uno.tek.birth_management_service.profiles.Profile;

import java.util.Optional;

public interface ActivationCodesRepository extends JpaRepository<ActivationCode, Integer> {

    Optional<ActivationCode> findTopByProfileAndTypeOrderByCreatedAtDesc(Profile profile, ActivationType type);

    Optional<ActivationCode> findTopByProfileAndTypeAndIsUsedFalseOrderByCreatedAtDesc(Profile profile, ActivationType type);
}
