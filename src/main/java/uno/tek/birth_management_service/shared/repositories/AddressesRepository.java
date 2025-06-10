package uno.tek.birth_management_service.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uno.tek.birth_management_service.shared.entities.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, Long> {
}
