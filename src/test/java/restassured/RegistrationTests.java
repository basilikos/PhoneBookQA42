package restassured;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import interfaces.TestHelper;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import models.NewUserModel;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

import static io.restassured.RestAssured.given;

public class RegistrationTests implements TestHelper {

    @Test
    public void registrationPositive() {
        NewUserModel newUserModel = new NewUserModel(
                EmailGenerator.generateEmail(5, 6, 3),
                PasswordStringGenerator.generateRandomPassword()
        );

        String newToken = given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + REGISTRATION_PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("token");
        System.out.println("newToken : " + newToken);

    }

    @Test()
    @Description("Registration negative test")
    public void registrationNegative() {

        NewUserModel newUserModel = new NewUserModel(
                "EmailGenerator.generateEmail(5, 6, 3)",
                PasswordStringGenerator.generateRandomPassword()
        );

        given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + REGISTRATION_PATH)
                .then()
                .statusCode(400)
                .assertThat()
                .body("message.username", containsString("email"))
        ;

    }

}
