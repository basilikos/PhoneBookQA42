package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateContactTest implements TestHelper {
    String id;

    @Test
    public void updateContactPositive() throws IOException {

        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5, 6, 3),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                "15052024"
        );

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();

        ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
        id = IdExtractor.getId(contactResponseModel.getMessage());
        System.out.println("IdExtractor.getId : " + id);

        Contact newContact = new Contact(
                id,
                contact.getName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getAddress(),
                contact.getDescription()
        );

        RequestBody updatedRequestBody = RequestBody.create(GSON.toJson(newContact), JSON);
        Request updateRequest = new Request.Builder()
                .url(BASE_URL + UPDATE_CONTACTS)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .put(updatedRequestBody)
                .build();
        Response updateResponse = CLIENT.newCall(updateRequest).execute();
        ContactResponseModel contactResponseModel1 = GSON.fromJson(
                updateResponse.body().string(), ContactResponseModel.class
        );
        Assert.assertTrue(updateResponse.isSuccessful());
    }
}
