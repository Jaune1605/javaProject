package fr.unilasalle.flight.api.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// INSERT INTO planes(id, operator, model, registration, capacity) VALUES(NEXTVAL('planes_sequence'), 'AirbusIndustrie', 'AIRBUS A380','F-ABCD', 10);
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "planes")
public class Plane extends PanacheEntityBase {

        @Id
        @SequenceGenerator(
                name = "planes_sequence",
                sequenceName = "planes_sequence",
                allocationSize = 1
                )
        @GeneratedValue(strategy = GenerationType.SEQUENCE,
                generator = "planes_sequence")
        private Long id;

        @NotBlank(message = "Plane Operator can not be empty")
        @Column(name= "operator", nullable = false)
        private String operator;

        @NotBlank(message = "Plane Model can not be empty")
        @Column(name= "model", nullable = false)
        private String model;

        @NotBlank(message = "Plane Registration can not be empty")
        @Column(unique = true, nullable = false, name = "registration")
        private String registration;

        @NotNull(message = "Plane Capacity must be provided")
        @Min(value = 5, message = "Plane Capacity must be at least 5")
        @Column(name="capacity", nullable = false)
        private Long capacity;
}
