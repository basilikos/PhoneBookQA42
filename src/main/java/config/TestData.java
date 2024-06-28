package config;

import helpers.EmailGenerator;
import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {EmailGenerator.generateEmail(4, 5, 3), "fakePassword0"},
                {"fakeUser1", "fakePassword1"},
                {"fakeUser2", "fakePassword2"}
        };
    }

}
