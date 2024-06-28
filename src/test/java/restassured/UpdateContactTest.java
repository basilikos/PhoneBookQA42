package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactTest implements TestHelper {
    String id;
    Contact contact;

    @Test
    public void updateContactPositive() {

        RestAssured.baseURI = BASE_URL + UPDATE_CONTACTS;

        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(4, 5, 3),
                AddressGenerator.generateAddress(),
                "22052024_updateContactPositive"
        );

        String message = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .post()
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("message");

        id = IdExtractor.getId(message);
        System.out.println("id : " + id);

        contact.setId(id);
        contact.setEmail(EmailGenerator.generateEmail(3, 3, 3));

        given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .put()
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .body("message", containsString("updated"));

    }

}
