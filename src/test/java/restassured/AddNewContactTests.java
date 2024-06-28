package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContactTests implements TestHelper {

    private Contact createContact() {
        return new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(4, 5, 3),
                AddressGenerator.generateAddress(),
                "26052024"
        );
    }

    private Contact createIncorrectContact() {
        return new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(4, 5, 3),
                AddressGenerator.generateAddress(),
                "26052024"
        );
    }

    @Test
    public void addNewContact() {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(4, 5, 3),
                AddressGenerator.generateAddress(),
                "22052024"
        );

        given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + ADD_CONTACT)
                .then()
                .assertThat()
                .statusCode(200)
        ;

    }

}
