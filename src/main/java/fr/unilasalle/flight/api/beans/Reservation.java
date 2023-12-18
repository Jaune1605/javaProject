package fr.unilasalle.flight.api.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* INSERT INTO reservations (id, flightId, passengerId)
VALUES(NEXTVAL('reservation_sequence_in_database'), 2, 3); */

@Entity
@Table(name = "reservations")
@NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;

    @NotNull (message = "The flight can not be null")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "flightId", referencedColumnName = "id", nullable = false)
    private Flight flight;

    @NotNull (message = "The passenger can not be null")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "passengerId", referencedColumnName = "id", nullable = false)
    private Passenger passenger;

}
