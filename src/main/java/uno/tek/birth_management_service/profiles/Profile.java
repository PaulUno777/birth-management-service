package uno.tek.birth_management_service.profiles;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import uno.tek.birth_management_service.shared.entities.Address;
import uno.tek.birth_management_service.shared.entities.BaseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Civility civility;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String lastName;

    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone number is invalid")
    @Column(nullable = false, unique = true, length = 30)
    private String phone;

    @NotBlank
    @Column( length = 100)
    private String password;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH })
    private Address address;

    // @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH })
    // @JoinColumn(name = "role_id")
    // private Role role;
}
