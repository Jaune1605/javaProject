package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Reservation;
import fr.unilasalle.flight.api.repositories.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationRessource extends GenericRessource {

    @Inject
    ReservationRepository reservationRepository;

    @Inject
    Validator validator;

    @GET
    public List<Reservation> listAll() {
        return reservationRepository.listAll();
    }

    @GET
    public Response getReservations(@QueryParam("flightNumber") String flightNumber,
                                    @QueryParam("flightId") Long flightId,
                                    @QueryParam("count") @DefaultValue("false") boolean count) {
        if (flightNumber != null && count) {
            Long reservationCount = reservationRepository.countByFlightNumber(flightNumber);
            return Response.ok(reservationCount).build();
        } else if (flightNumber != null) {
            List<Reservation> reservations = reservationRepository.findByFlightNumber(flightNumber);
            return Response.ok(reservations).build();
        } else if (flightId != null) {
            List<Reservation> reservations = reservationRepository.findByFlightId(flightId);
            return Response.ok(reservations).build();
        } else {
            List<Reservation> allReservations = reservationRepository.listAll();
            return Response.ok(allReservations).build();
        }
    }

    @POST
    public Response create(Reservation reservation) {
        var violation = validator.validate(reservation);
        if (!violation.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violation)).build();
        }

        try {
            reservationRepository.persistAndFlush(reservation);
            return Response.ok(reservation).status(201).build();
        } catch (PersistenceException ex) {
            return Response.serverError().entity(new ErrorWrapper(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Reservation reservation = reservationRepository.findById(id);
        if (reservation == null) {
            return Response.status(404).build();
        }

        try {
            reservationRepository.deleteById(id);
            return Response.noContent().build();
        } catch (PersistenceException ex) {
            return Response.serverError().entity(new ErrorWrapper(ex.getMessage())).build();
        }
    }
}
