package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Flight;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Model;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Model
@ApplicationScoped
public class FlightRepository implements PanacheRepositoryBase<Flight, Long> {
    public List<Flight> findByDestination(String destination) {
        return find("destination", destination).stream().toList();
    }
}
