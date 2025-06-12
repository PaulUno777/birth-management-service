package uno.tek.birth_management_service.profiles;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import uno.tek.birth_management_service.shared.services.AddressesService;
import uno.tek.birth_management_service.shared.services.ValidationService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfilesServiceTest {
    private ProfilesRepository profilesRepository;
    private AddressesService addressesService;
    private ValidationService validationService;
    private ProfilesService profilesService;
    private BCryptPasswordEncoder passwordEncoder;
    private ProfileMapper profileMapper;

    @BeforeEach
    public void setUp() {
        profilesRepository = mock(ProfilesRepository.class);
        addressesService = mock(AddressesService.class);
        validationService = mock(ValidationService.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        profileMapper = mock(ProfileMapper.class);

        profilesService = new ProfilesService(addressesService, profilesRepository, validationService, passwordEncoder,
                profileMapper);
    }

    @Test
    public void findAll_shouldReturnListOfProfiles() {
        List<Profile> profiles = List.of(new Profile(), new Profile());
        when(profilesRepository.findAll()).thenReturn(profiles);

        Set<ProfileDTO> result = profilesService.findAll();

        assertEquals(2, result.size());
        verify(profilesRepository).findAll();
    }

    @Test
    public void findById_shouldReturnProfileWhenExists() {
        Profile profile = new Profile();
        when(profilesRepository.findById(1)).thenReturn(Optional.of(profile));

        Profile result = profilesService.findById(1);

        assertEquals(profile, result);
    }

    @Test
    public void findById_shouldThrowWhenNotFound() {
        when(profilesRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> profilesService.findById(99));
    }

    @Test
    public void update_shouldModifyAndSaveProfile() {
        Profile existing = new Profile();
        existing.setEmail("old@example.com");

        Profile update = new Profile();
        update.setEmail("new@example.com");
        update.setFirstName("John");
        update.setLastName("Doe");
        update.setPhone("+111111");

        when(profilesRepository.findById(1)).thenReturn(Optional.of(existing));

        Profile result = profilesService.update(1, update);

        assertEquals("new@example.com", result.getEmail());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("+111111", result.getPhone());
        verify(profilesRepository).save(existing);
    }

    @Test
    public void delete_shouldRemoveProfile() {
        Profile existing = new Profile();
        when(profilesRepository.findById(1)).thenReturn(Optional.of(existing));

        profilesService.delete(1);

        verify(profilesRepository).delete(existing);
    }

    @Test
    public void delete_shouldThrowWhenProfileNotFound() {
        when(profilesRepository.findById(404)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> profilesService.delete(404));
    }
}