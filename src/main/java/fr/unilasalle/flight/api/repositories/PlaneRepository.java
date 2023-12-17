package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Plane;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;

public class PlaneRepository implements PanacheRepositoryBase<Plane, Long> {

    public Plane findByRegistration(String registration) {
        return find("registration", registration).firstResultOptional().orElse(null);
    }

    public List<Plane> findByOperator(String operator) {
        return find("operator", operator).stream().toList();
    }

}
