package pages;

import helpers.TopMenuItem;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    /*
     * Этот фрагмент кода содержит определение класса BasePage, который служит базовым классом для всех страниц тестового приложения,
     * использующего библиотеку Selenium для автоматизации веб-тестирования.
     * <p>
     * public class BasePage {: Это объявление класса BasePage.
     * Ключевое слово public означает, что класс доступен из любого другого класса.
     * BasePage является базовым классом для страниц приложения.
     * <p>
     * protected static WebDriver driver;: Это объявление статического поля driver типа WebDriver.
     * Поле driver предназначено для хранения экземпляра веб-драйвера, который будет использоваться для управления браузером на страницах.
     * <p>
     * public static void setDriver(WebDriver webDriver){:
     * Это объявление статического метода setDriver, который принимает параметр webDriver типа WebDriver.
     * Метод устанавливает значение поля driver в переданный экземпляр веб-драйвера.
     * <p>
     * driver = webDriver;: Эта строка присваивает переданный экземпляр веб-драйвера переменной driver,
     * что позволяет другим классам иметь доступ к этому веб-драйверу через метод getDriver(), если они импортировали класс BasePage.
     * <p>
     * Этот класс BasePage предоставляет базовую функциональность для управления веб-драйвером в
     * тестовых страницах, такую как инициализация веб-драйвера и установка его для использования на страницах.
     */

    public static boolean isElementPersist(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException | NullPointerException e) {
            return false;
        }
    }

    public static <T extends BasePage> T openTopMenuItem(TopMenuItem topMenuItem) {
        Allure.step("Open top menu item.");
        WebElement menuItem =
                driver.findElement(By.xpath("//a[contains(text(), '" + topMenuItem + "')]"));
        menuItem.click();
        switch (topMenuItem.toString()) {
            case "HOME":
                return (T) new HomePage(driver);
            case "ABOUT":
                return (T) new AboutPage(driver);
            case "LOGIN":
                return (T) new LoginPage(driver);
            case "CONTACTS":
                return (T) new ContactsPage(driver);
            case "ADD":
                return (T) new AddPage(driver);
            default:
                throw new IllegalArgumentException("Invalid topMenuItem parameter..." + topMenuItem);
        }
    }

}
