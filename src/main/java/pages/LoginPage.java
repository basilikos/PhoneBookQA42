package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
Этот код представляет собой класс LoginPage, который представляет веб-страницу для ввода логина и пароля.
*/
public class LoginPage extends BasePage {
    //  Аннотации @FindBy используются для идентификации веб-элементов на странице с помощью локаторов.
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordField;
    @FindBy(xpath = "//button[@name='login']")
    WebElement loginButton;
    @FindBy(xpath = "//button[@name='registration']")
    WebElement registrationButton;

    /*
    Это конструктор класса, который принимает объект WebDriver в качестве параметра.
    Он инициализирует объект страницы, устанавливая драйвер и инициализируя элементы страницы с помощью PageFactory.initElements.
    */

    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    /*Это методы для ввода текста в поля электронной почты и пароля соответственно.
     Они возвращают экземпляр LoginPage, чтобы можно было выполнять цепочку вызовов методов*/
    public LoginPage fillEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public Alert clickByRegistrationButton() { // Этот метод кликает по кнопке регистрации на веб-странице.
        Allure.step("Wait and click on the 'Registration' button.");
        // Он вызывает метод click() для registrationButton.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement regButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='registration']")));
        regButton.click();
        return getAlertIfPresent(); // Затем он также возвращает объект LoginPage, чтобы этот метод также можно было использовать в цепочке вызовов.
    }

    private Alert getAlertIfPresent() {
        short time = 5;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println("There is no alert...");
            return null;
        }
    }

    public BasePage clickByLoginButton() {
        Allure.step("Wait and click on the 'Login' button.");
        loginButton.click();
        Alert alert = getAlertIfPresent();
        if (alert != null) {
            alert.accept();
            return new LoginPage(driver);
        } else {
            return new ContactsPage(driver);
        }
    }

}
