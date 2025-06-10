package uno.tek.birth_management_service.shared.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {
    @Column(nullable = true, length = 30)
    private String zip;

    @Column(nullable = true, length = 30)
    private String tag;

    private String street;

    private String city;

    @Column(length = 100)
    private String country;
}
