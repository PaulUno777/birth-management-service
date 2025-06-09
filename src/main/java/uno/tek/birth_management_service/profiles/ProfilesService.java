package uno.tek.birth_management_service.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    Logger logger = LoggerFactory.getLogger(ProfileService.class);

    public void create(Profile profile) {
        logger.info("Creating new profile");
    }
}
