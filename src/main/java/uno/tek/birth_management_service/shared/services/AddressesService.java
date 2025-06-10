package uno.tek.birth_management_service.shared.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uno.tek.birth_management_service.shared.entities.Address;
import uno.tek.birth_management_service.shared.repositories.AddressesRepository;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddressesService {
    private final AddressesRepository addressesRepository;

    public Address create(Address address) {
        log.info("Creating new address");

        return this.addressesRepository.save(address);
    }

}
