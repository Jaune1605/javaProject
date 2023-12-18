package fr.unilasalle.flight.api;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.repositories.PassengerRepository;
import fr.unilasalle.flight.api.ressources.PassengerRessource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PassengerRessourceIT {

    static Passenger testPassenger() {
        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setSurname("DEPARDIEU");
        passenger.setFirstName("Gerard");
        passenger.setEmail("gerard.depardieu@gmail.com");
        return passenger;
    }

    @Test
    void testGetPassenger() {
        given()
                .when().get("/passengers/1")
                .then()
                .statusCode(200)
                .body("surname", equalTo("PADE"));
    }
    /*
    @Test
    void testCreatePassenger() {
        Passenger newPassenger = testPassenger();
        given()
                .contentType(ContentType.JSON)
                .body(newPassenger)
                .when().post("/passengers")
                .then()
                .statusCode(201)
                .body(notNullValue())
                .body("surname", equalTo("DEPARDIEU"));
    }
    /*@Test
    void testGetPassengerByEmailAddress() {
        given()
                .when().get("/john.pade@gmail.com")
                .then()
                .statusCode(200);
    }*/

    @Test
    void testUpdatePassenger() {
        Passenger newPassenger = testPassenger();
        given()
                .contentType(ContentType.JSON)
                .body(newPassenger)
                .when().put("/passengers/1")
                .then()
                .statusCode(200)
                .body("surname", equalTo("DEPARDIEU"));
    }

}
