package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Passenger;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PassengerRepository implements PanacheRepositoryBase<Passenger, Long> {

    public Passenger findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<Passenger> listAll() {
        return PanacheRepositoryBase.super.listAll();
    }

}
