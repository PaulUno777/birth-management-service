package uno.tek.birth_management_service.shared.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uno.tek.birth_management_service.profiles.ProfilesRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {
    @Mock
    private ProfilesRepository profilesRepository;
    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService(profilesRepository);
    }

    @Test
    public void shouldPass_whenEmailIsUnique() {
        // Given
        String email = "test@example.com";
        when(profilesRepository.existsByEmail(email)).thenReturn(false);

        // When / Then
        assertDoesNotThrow(() -> validationService.validateUniqueEmail(email));
    }

    @Test
    public void shouldThrow_whenEmailExists() {
        // Given
        String email = "duplicate@example.com";
        when(profilesRepository.existsByEmail(email)).thenReturn(true);

        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validationService.validateUniqueEmail(email);
        });
        assertEquals("Email is already in use", exception.getMessage());
    }

    @Test
    public void shouldPass_whenPhoneIsUnique() {
        // Given
        String phone = "+1234567890";
        when(profilesRepository.existsByPhone(phone)).thenReturn(false);

        // When / Then
        assertDoesNotThrow(() -> validationService.validateUniquePhone(phone));
    }

    @Test
    public void shouldThrow_whenPhoneExists() {
        // Given
        String phone = "+0987654321";
        when(profilesRepository.existsByPhone(phone)).thenReturn(true);

        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validationService.validateUniquePhone(phone);
        });
        assertEquals("Phone number is already in use", exception.getMessage());
    }
}