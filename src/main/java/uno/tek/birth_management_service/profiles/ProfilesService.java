package uno.tek.birth_management_service.profiles;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import uno.tek.birth_management_service.shared.services.AddressesService;
import uno.tek.birth_management_service.shared.services.ValidationService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ProfilesService {
    private final AddressesService addressesService;
    private final ProfilesRepository profilesRepository;
    private final ValidationService validationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfileMapper profileMapper;

    public Set<ProfileDTO> findAll() {
        log.info("Finding all profiles");
        List<Profile> profiles = profilesRepository.findAll();

        return profiles.stream().map(profileMapper::toDto).collect(Collectors.toSet());
    }

    public Profile findById(int id) {
        log.info("Finding a profile");
        Optional<Profile> profile = this.profilesRepository.findById(id);
        return profile.orElseThrow(
                () -> new EntityNotFoundException("Profile not found"));
    }

    public Profile update(int id, Profile profile) {
        Profile existing = this.findById(id);

        if (existing != null) {
            existing.setEmail(profile.getEmail());
            existing.setFirstName(profile.getFirstName());
            existing.setLastName(profile.getLastName());
            existing.setPhone(profile.getPhone());
            this.profilesRepository.save(existing);
        }

        return existing;
    }

    public void delete(int id) {
        Profile existing = this.findById(id);

        if (existing != null) {
            this.profilesRepository.delete(existing);
        }
    }
}
