package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.repositories.PassengerRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassengerRessource extends GenericRessource {

    @Inject
    PassengerRepository passengerRepository;

    @Inject
    Validator validator;

    @GET
    public List<Passenger> listAll() {
        return passengerRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getSingle(@PathParam("id") Long id) {
        Passenger passenger = passengerRepository.findById(id);
        return getOr404(passenger);
    }
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Passenger passenger) {
        Passenger entity = passengerRepository.findById(id);
        if (entity == null) {
            return Response.status(404).entity("Passenger with id of " + id + " does not exist.").build();
        }

        // Mettre à jour l'entité existante avec les nouvelles valeurs
        entity.setFirstName(passenger.getFirstName());
        entity.setSurname(passenger.getSurname());
        entity.setEmail(passenger.getEmail());

        // Validation
        var violation = validator.validate(entity);
        if (!violation.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violation)).build();
        }

        try {
            passengerRepository.persistAndFlush(entity);
            return Response.ok(entity).build();
        } catch (PersistenceException ex) {
            return Response.serverError().entity(new ErrorWrapper(ex.getMessage())).build();
        }
    }

}
