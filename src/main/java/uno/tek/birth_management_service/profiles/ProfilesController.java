package uno.tek.birth_management_service.profiles;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("profiles")
public class ProfilesController {

    private final ProfilesService profileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Profile profile) {
        this.profileService.create(profile);
    }

    @GetMapping
    public List<Profile> findAll() {
        return this.profileService.findAll();
    }

    @GetMapping(path = "{id}")
    public Profile findById(@PathVariable int id) {
        return this.profileService.findById(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public Profile findById(@PathVariable int id, @RequestBody Profile profile) {
        return this.profileService.update(id, profile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable int id) {
        this.profileService.delete(id);
    }
}
