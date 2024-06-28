package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContact implements TestHelper {

    String id;

    @BeforeTest
    public void createContactPrecondition() throws IOException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5, 5, 3),
                AddressGenerator.generateAddress(),
                "08052024_3"
        );
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.
                Builder().
                url(BASE_URL + ADD_CONTACT).
                addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH)).
                post(requestBody).
                build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("contactResponseModel : " + contactResponseModel.getMessage());
//        String id = IdExtractor.getId(contactResponseModel.getMessage());
        id = IdExtractor.getId(contactResponseModel.getMessage());
    }

    @Test
    public void createAndDeleteContactPositive() throws IOException {

        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5, 5, 3),
                AddressGenerator.generateAddress(),
                "08052024_2"
        );
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.
                Builder().
                url(BASE_URL + ADD_CONTACT).
                addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH)).
                post(requestBody).
                build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("contactResponseModel : " + contactResponseModel.getMessage());
//        String id = IdExtractor.getId(contactResponseModel.getMessage());
        id = IdExtractor.getId(contactResponseModel.getMessage());

        request = new Request
                .Builder()
                .url(BASE_URL + DELETE_CONTACT + id)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH))
                .delete()
                .build();

        response = CLIENT.newCall(request).execute();
        contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println(contactResponseModel.getMessage());
        Assert.assertTrue(response.isSuccessful());

    }

    @Test
    public void deleteContactByID() throws IOException {
//        createContactPrecondition();
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CONTACT + id)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH))
                .delete()
                .build();

        Response response = CLIENT.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void createAndDeleteContactByIdNegative() throws IOException {
//        createContactPrecondition();
        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CONTACT + id)
                .addHeader(AUTHORIZATION_HEADER, "asd.asd.asd")
                .delete()
                .build();

        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        System.out.println(errorModel.getStatus());
        Assert.assertEquals(errorModel.getStatus(), 401);
    }
}
