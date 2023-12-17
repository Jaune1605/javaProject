package fr.unilasalle.flight.api.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "plane")
public class Plane extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "plane_sequence",
            sequenceName = "plane_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plane_sequence"
    )
    private int id;

    @Column(name = "operator", nullable = false, columnDefinition = "TEXT")
    @NotNull
    @Size(min = 1, max = 50)
    private String operator;

    @Column(name = "model", nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String model;

    @Column(name = "registration", nullable = false, unique = true, length = 20)
    @NotNull
    @Size(min = 1, max = 20)
    private String registration;

    @Column(name = "capacity", nullable = false)
    @NotNull
    @Min(1)
    private int capacity;

    // Getters and setters
    // ...
}
