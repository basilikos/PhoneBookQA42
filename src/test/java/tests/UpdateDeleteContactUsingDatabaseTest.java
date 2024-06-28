package tests;

import db.DatabaseConnection;
import helpers.ContactGenerator;
import helpers.IdExtractor;
import helpers.PropertiesReader;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.annotations.BeforeMethod;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class UpdateDeleteContactUsingDatabaseTest implements TestHelper {

    Contact contact;
    String id;

    @BeforeMethod
    public void precondition() throws SQLException {
        RestAssured.baseURI = BASE_URL + ADD_CONTACT;
        contact = ContactGenerator.createCorrectContact();

        String message = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .post()
                .then()
                .assertThat()
                .statusCode(200).extract().path("message");
        System.out.println("message : " + message);

        id = IdExtractor.getId(message);

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.contactDatabaseRecorder(id, contact);

    }

}
