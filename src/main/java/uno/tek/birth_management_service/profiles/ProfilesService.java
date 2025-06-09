package uno.tek.birth_management_service.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ProfilesService {
    private final ProfilesRepository profilesRepository;

    public void create(Profile profile) {
        log.info("Creating new profile");

        this.profilesRepository.save(profile);
    }
}
