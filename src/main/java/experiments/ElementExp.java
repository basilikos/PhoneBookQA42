package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ElementExp {
    public static void main(String[] args) throws InterruptedException {
        dragAndDropTest();
    }
    public static  void dragAndDropTest() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement elementA = driver.findElement(By.id("column-a"));
        WebElement elementB = driver.findElement(By.id("column-b"));
        // WebElement aElement = driver.findElement(By.xpath("//a"));
        Actions actions = new Actions(driver);
       // actions.dragAndDrop(elementA, elementB).release(elementB).build().perform();

        Action dragAndDrop = actions.clickAndHold(elementA)
                .moveToElement(elementB)
                .release(elementA)
                .build();
        dragAndDrop.perform();
        Thread.sleep(3000);

        driver.quit();

    }
}
