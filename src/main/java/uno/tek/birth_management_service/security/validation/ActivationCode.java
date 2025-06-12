package uno.tek.birth_management_service.security.validation;

import jakarta.persistence.*;
import lombok.*;
import uno.tek.birth_management_service.profiles.Profile;
import uno.tek.birth_management_service.shared.entities.BaseEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "activation_codes")
public class ActivationCode extends BaseEntity {
    @Column(nullable = false, length = 10)
    private String code;

    @Column(name = "ip_address")
    private String ipAddress;

    @Builder.Default
    @Column(name = "is_used", nullable = false)
    private boolean isUsed = false;;

    @Column(name = "expire_at", nullable = false)
    private LocalDateTime expireAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ActivationType type;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
}
