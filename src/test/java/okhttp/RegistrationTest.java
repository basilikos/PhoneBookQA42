package okhttp;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import interfaces.TestHelper;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest implements TestHelper {
    @Test
    public void registrationTest() throws IOException {
        NewUserModel newUserModel = new NewUserModel(
                EmailGenerator.generateEmail(5, 5, 3),
                PasswordStringGenerator.generateRandomPassword()
        );
        RequestBody requestBody = RequestBody.create(GSON.toJson(newUserModel), JSON);
        Request request = new Request
                .Builder()
                .url(BASE_URL + REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        String res = response.body().string();
//        System.out.println(response.isSuccessful());
        if (response.isSuccessful()) {
            AuthenticationResponseModel responseModel = GSON.fromJson(res, AuthenticationResponseModel.class);
            String resultToken = responseModel.getToken();
            Assert.assertTrue(response.isSuccessful());
        } else {
            ErrorModel errorModel = GSON.fromJson(res, ErrorModel.class);
            System.out.println(response.code());
        }
    }

    @Test
    public void wrongPasswordRegistration() throws IOException {
        NewUserModel newUserModel = new NewUserModel(
                EmailGenerator.generateEmail(5, 5, 3),
                "asASD"
        );

        RequestBody requestBody = RequestBody.create(GSON.toJson(newUserModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);

        if (response.isSuccessful()) {
            System.out.println("response.isSuccessful()...");
        } else {
            ErrorModel errorModel = GSON.fromJson(result, ErrorModel.class);
            Assert.assertEquals(response.code(), 400);
        }
    }

    @Test
    public void duplicateUserRegistration() throws IOException {
        NewUserModel newUserModel = new NewUserModel(
                EmailGenerator.generateEmail(5, 5, 3),
                PasswordStringGenerator.generateRandomPassword()
        );

        Response response = null;
        String res = null;

        for (int i = 0; i < 2; i++) {
            RequestBody requestBody = RequestBody.create(GSON.toJson(newUserModel), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + REGISTRATION_PATH)
                    .post(requestBody)
                    .build();
            response = CLIENT.newCall(request).execute();
            res = response.body().string();
            System.out.println("res : " + res);
        }

        if (response.isSuccessful()) {
            AuthenticationResponseModel authenticationResponseModel = GSON.fromJson(res, AuthenticationResponseModel.class);
            System.out.println("getToken : " + authenticationResponseModel.getToken());
        } else {
            ErrorModel errorModel = GSON.fromJson(res, ErrorModel.class);
            System.out.println("errorModel.getStatus : " + errorModel.getStatus());
            System.out.println("errorModel.getMessage : " + errorModel.getMessage());
            Assert.assertEquals(response.code(), 409);
        }

    }

}
