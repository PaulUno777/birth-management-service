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
@Table(name = "city_halls")
public class CityHall extends BaseEntity{
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH })
    @JoinColumn(name = "address_id")
    private Address address;
}
