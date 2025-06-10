package uno.tek.birth_management_service.profiles;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uno.tek.birth_management_service.shared.entities.Address;
import uno.tek.birth_management_service.shared.services.AddressesService;
import uno.tek.birth_management_service.shared.services.ValidationService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ProfilesService {
    private final AddressesService addressesService;
    private final ProfilesRepository profilesRepository;
    private final ValidationService validationService;

    public void create(Profile profile) {
        log.info("Creating new profile");

        validationService.validateUniqueEmail(profile.getEmail());
        validationService.validateUniquePhone(profile.getPhone());

        if (profile.getAddress() != null) {
            Address address = addressesService.create(profile.getAddress());
            profile.setAddress(address);
        }

        this.profilesRepository.save(profile);
    }

    public List<Profile> findAll() {
        log.info("Finding all profiles");
        return this.profilesRepository.findAll();
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
