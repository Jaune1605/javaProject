package fr.unilasalle.flight.api;

import fr.unilasalle.flight.api.beans.Plane;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


@QuarkusTest
public class PlaneRessourceIT {

    static Plane testPlane() {
        Plane plane = new Plane();
        plane.setOperator("AirMidiLesZouzous");
        plane.setModel("A380");
        plane.setRegistration("F-ABCD");
        plane.setCapacity(20L);
        return plane;
    }

    @Test
    void get_all_planes() {
        given()
                .when().get("/planes")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("size()", is(2))
        ;
    }

    @Test
    void get_plane_with_existing_operator() {
        given().param("operator", "AirbusIndustrie")
                .when().get("/planes")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("size()", is(1))
        ;
    }

    @Test
    void get_plane_with_non_existing_operator() {
        given().param("operator", "CacaAIrLines")
                .when().get("/planes")
                .then()
                .statusCode(404)
        ;
    }

    @Test
    void create_plane() {
        Plane plane = testPlane();

        given()
                .contentType(ContentType.JSON)
                .body(plane)
                .when().post("/planes")
                .then()
                .statusCode(201)
                .body(notNullValue())
                .body("operator", is("AirMidiLesZouzous"))
        ;
    }
}
