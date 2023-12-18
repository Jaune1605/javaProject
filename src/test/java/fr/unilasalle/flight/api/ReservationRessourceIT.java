package fr.unilasalle.flight.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.Test;

class ReservationRessourceIT {

    @Test
    void testGetReservation() {
        given()
                .when().get("/reservations/1") // Adjust the endpoint as per your API
                .then()
                .statusCode(200)
                .body("someField", equalTo("someValue")); // Replace 'someField' and 'someValue' with actual field and expected value
    }

}
