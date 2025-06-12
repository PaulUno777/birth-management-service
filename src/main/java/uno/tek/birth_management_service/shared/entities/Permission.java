package uno.tek.birth_management_service.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity{
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
}
