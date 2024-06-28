package restassured;

import helpers.Logger;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactListModel;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class GetAllContactsTest implements TestHelper {

    @Test
    public void getAllContactsPositive() throws IOException {

        Logger.setupLogger("src/logs/testResult2.log");

//        File logFile = new File("src/logs/testResult.log");
//        if (!logFile.exists()) {
//            logFile.getParentFile().mkdirs();
//            logFile.createNewFile();
//        }
//        PrintStream printStream = new PrintStream(new FileOutputStream(logFile));
//        System.setOut(printStream);
//        System.setErr(printStream);

        ContactListModel contactList = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .when()
                .get(BASE_URL + GET_ALL_CONTACTS)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(ContactListModel.class);

        for (Contact contact : contactList.getContacts()) {
            System.out.println(contact.getName());
            System.out.println(contact.getEmail());
            System.out.println("*** *** ***");
        }

        Logger.closeLogger();
//        printStream.close();

    }

}
