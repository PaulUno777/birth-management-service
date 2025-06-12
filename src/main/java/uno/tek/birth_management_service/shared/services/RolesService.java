package uno.tek.birth_management_service.shared.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uno.tek.birth_management_service.shared.entities.Role;
import uno.tek.birth_management_service.shared.repositories.RolesRepository;

@Slf4j
@AllArgsConstructor
@Service
public class RolesService {
    private final RolesRepository rolesRepository;

    public Role create(Role role) {
        log.info("Creating new role");

        return this.rolesRepository.save(role);
    }

}
