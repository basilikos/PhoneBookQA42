package pages;

import config.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class MainPage extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'HOME')]")
    WebElement homeTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'ABOUT')]")
    WebElement aboutTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'LOGIN')]")
    WebElement loginTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'CONTACTS')]")
    WebElement contactsTopMenuItem;
    @FindBy(xpath = "//a[contains(text(),'ADD')]")
    WebElement addTopMenuItem;

    // public MainPage(WebDriver driver) - конструктор класса MainPage, который принимает объект WebDriver в качестве параметра.
public MainPage(WebDriver driver){
    setDriver(driver);
    driver.get("https://telranedu.web.app/home"); // Переходим на указанный URL-адрес веб-страницы с помощью метода get объекта WebDriver.
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    /*
   PageFactory.initElements - Инициализирует элементы страницы с помощью PageFactory.
        Она ищет все аннотированные элементы @FindBy в классе MainPage и связывает их с реальными элементами веб-страницы.
        Используется AjaxElementLocatorFactory для ожидания элементов до их появления на странице в течение указанного времени (в данном случае, 20 секунд).*/
}

public LoginPage clickLoginButton(){/* Метод возвращает страницу LoginPage так как при нажатии на меню LOGIN в верхнем меню открывается новая страница */
    loginTopMenuItem.click();
    return new LoginPage(driver);
}


}
