package uno.tek.birth_management_service.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uno.tek.birth_management_service.shared.entities.Role;
import uno.tek.birth_management_service.shared.entities.RoleName;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleName name);
}
