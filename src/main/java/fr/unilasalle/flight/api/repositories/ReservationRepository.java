package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Reservation;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ReservationRepository implements PanacheRepositoryBase<Reservation, Long> {

    public List<Reservation> findByFlightNumber(String number) {
        return find("flight.number", number).firstResult();
    }

    public List<Reservation> findByFlightId(Long id) {
        return find("flight.id", id).list();
    }

    public Long countByFlightNumber(String number) { return count("flight.number", number);
    }

}
