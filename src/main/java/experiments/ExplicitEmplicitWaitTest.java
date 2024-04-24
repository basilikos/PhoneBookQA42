package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitEmplicitWaitTest {
    public static void main(String[] args) {
       // waitForAnElementExplicit(); // Явное
        waitForAnElementImplicit(); // Неявное
    }

    public static void waitForAnElementImplicit(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        WebElement button = driver.findElement(By.xpath("//button"));
        button.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement element2 = driver.findElement(By.xpath("//div[@id='finish']"));
        element2.click();
        driver.quit();

    }
    //*******************************************************
    public static void waitForAnElementExplicit() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        try {
            driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
            WebElement element = driver.findElement(By.xpath("//button"));
            element.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement divHeader = wait
                    .until(ExpectedConditions
                            .visibilityOfElementLocated(
                                    By.xpath("//div[@id='finish']")));

            divHeader.click();


        } catch (NoSuchElementException e) {
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }


    }

}
