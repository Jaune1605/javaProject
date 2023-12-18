package fr.unilasalle.flight.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import fr.unilasalle.flight.api.beans.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class FlightRessourceIT {

    static Flight testFlight() {
        Flight flight = new Flight();
        flight.setNumber("A-12345678");
        flight.setDepartureDate(LocalDate.parse("Paris"));
        flight.setArrivalDate(LocalDate.parse("New York"));
        flight.setDepartureTime(LocalTime.from(LocalDateTime.of(2021, 1, 1, 12, 0)));
        flight.setArrivalTime(LocalTime.from(LocalDateTime.of(2021, 1, 1, 18, 0)));
        return flight;
    }
    @Test
    void testGetFlight() {
        given()
                .when().get("/flights/1") // Adjust the endpoint as per your API
                .then()
                .statusCode(200)
                .body("someField", equalTo("someValue")); // Replace 'someField' and 'someValue' with actual field and expected value
    }

    @Test
    void testCreateFlight() {
        Flight flight = testFlight();

        given()
                .contentType("application/json")
                .body(flight)
                .when().post("/flights")
                .then()
                .statusCode(201)
                .body("number", equalTo("A-12345678"));
    }
}
