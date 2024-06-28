package okhttp;

import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts implements TestHelper {
    @Test
    public void clearContacts() throws IOException {
        Request request = new Request
                .Builder()
                .url(BASE_URL + DELETE_ALL_CONTACTS)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH))
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        System.out.println("response : " + response.code());
        ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println(contactResponseModel.getMessage());
    }
}
