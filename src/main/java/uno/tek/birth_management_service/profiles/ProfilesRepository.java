package uno.tek.birth_management_service.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Profile> findByEmail(String email);

    Optional<Profile> findByPhone(String phone);
}
