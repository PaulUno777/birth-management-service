package uno.tek.birth_management_service.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("profiles")
public class ProfileController {
    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @PostMapping
    public void create(@RequestBody Profile profile){
        logger.trace("Creating new profile" + profile.getEmail());
    }
}
