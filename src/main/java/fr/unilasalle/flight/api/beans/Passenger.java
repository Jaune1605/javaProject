package fr.unilasalle.flight.api.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*INSERT INTO passengers(id, surname, firstname, email_address) VALUES (NEXTVAL('passenger_sequence_in_database'), 'PADE', 'John', 'john.pade@gmail.com');*/

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "passenger_sequence",
            sequenceName = "passenger_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passenger_sequence"
    )
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email_address", nullable = false, unique = true)
    private String email;

}
