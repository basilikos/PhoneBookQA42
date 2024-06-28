package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactListModel;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts implements TestHelper {

    @Test
    public void getAllContactsPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_ALL_CONTACTS)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .build();

        Response response = CLIENT.newCall(request).execute();
        String responseBody = response.body().string();

        ContactListModel contacts = GSON.fromJson(responseBody, ContactListModel.class);

        for (Contact contact : contacts.getContacts()) {
            System.out.println(contact.getName());
            System.out.println(contact.getLastName());
            System.out.println(contact.getEmail());
            System.out.println("***");
        }

        Assert.assertTrue(response.isSuccessful());

    }
}
