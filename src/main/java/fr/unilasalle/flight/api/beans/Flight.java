package fr.unilasalle.flight.api.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
// INSERT INTO flights (id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, planeId)
@Table(name = "flights")
@Getter
@Entity
@Setter
@NoArgsConstructor
public class Flight extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "flights_sequence",
            sequenceName = "flights_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flights_sequence"
    )
    private Long id;

    @NotNull(message = "The number needs to be provided")
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @NotNull(message = "The origin needs to be provided")
    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull(message = "The destination needs to be provided")
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotNull(message = "The departure date needs to be provided")
    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @NotNull(message = "The departure time needs to be provided")
    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @NotNull(message = "The arrival date needs to be provided")
    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;

    @NotNull(message = "The arrival time needs to be provided")
    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "planeId", referencedColumnName = "id", nullable = false)
    private Plane plane;

    @JsonIgnore
    @OneToMany(targetEntity = Reservation.class, mappedBy = "flight")
    private List<Reservation> reservations = new ArrayList<>();

}
