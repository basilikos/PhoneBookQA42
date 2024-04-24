package helpers;

import io.qameta.allure.Allure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertHandler {
    private WebDriver driver;

    public AlertHandler(WebDriver driver) {
        this.driver = driver;
    }

    public static boolean handleAlert(Alert alert, String expectedText) {
        Allure.step("Handling a pop-up window.");
        if (alert != null) {
            String alertText = alert.getText();
            System.out.println("Alert text : " + alertText);
            alert.accept();
            return alertText.contains(expectedText);
        } else {
            System.out.println("There is no alert window....");
            return false;
        }

    }
}
