package fr.unilasalle.flight.api;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;
import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.repositories.PassengerRepository;
import fr.unilasalle.flight.api.ressources.PassengerRessource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
class PassengerRessourceIT {

    @Test
    void testGetPassenger() {
        given()
                .when().get("/passengers/1")
                .then()
                .statusCode(200)
                .body("surname", equalTo("PADE"));
    }
}
