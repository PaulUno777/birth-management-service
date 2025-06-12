package uno.tek.birth_management_service.profiles;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uno.tek.birth_management_service.shared.entities.Address;
import uno.tek.birth_management_service.shared.entities.BaseEntity;
import uno.tek.birth_management_service.shared.entities.Permission;
import uno.tek.birth_management_service.shared.entities.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity implements UserDetails {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Civility civility;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String phone;

    @Builder.Default
    @Column(nullable = false)
    private boolean phoneVerified = false;

    @Column( length = 100)
    private String password;

    @Builder.Default()
    private boolean isActive = false;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH })
    private Address address;

     @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.LAZY)
     @JoinColumn(name = "role_id")
     private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority((this.role.getName().toString())));
        for (Permission permission: this.role.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority((permission.getName().toString())));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
