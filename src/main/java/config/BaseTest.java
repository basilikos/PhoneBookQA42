package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

/*
    Метод getDriver() используется для получения экземпляра объекта WebDriver из других классов.
    В данном конкретном случае, этот метод используется в классе BaseTest,
    чтобы предоставить доступ к объекту WebDriver из других частей кода.

    Зачем: Когда вы пишете автоматизированные тесты, часто требуется доступ к объекту WebDriver
    из различных частей вашего кода, таких как страницы, тесты и т. д.
    Этот метод позволяет другим частям кода получить доступ к объекту WebDriver, созданному в классе BaseTest.

    Как: Другие классы могут вызвать метод getDriver() для получения ссылки на объект WebDriver.
    Например, если у вас есть класс страницы (например, LoginPage, HomePage, и т. д.),
    который нуждается в доступе к WebDriver для выполнения действий на веб-странице,
    этот класс может вызвать getDriver() из экземпляра BaseTest, чтобы получить доступ к WebDriver.

    Таким образом, этот метод обеспечивает механизм доступа к WebDriver из различных частей вашего кода,
    что позволяет легко и эффективно организовывать вашу автоматизированную тестовую инфраструктуру.
*/

    @BeforeMethod
    @Parameters("browser")

    public void setUp(@Optional("firefox") String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "en");
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Invalid browser value : " + browser);
        }

        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver1 = getDriver();
        if (driver1 != null) {
            driver1.quit();
        }
    }

}