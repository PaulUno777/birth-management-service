package uno.tek.birth_management_service.profiles;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("profiles")
public class ProfilesController {

    private final ProfilesService profileService;

    @PostMapping
    public void create(@RequestBody Profile profile) {
        this.profileService.create(profile);
    }
}
