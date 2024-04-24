package tests;

import config.BaseTest;
import helpers.*;
import io.qameta.allure.Allure;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class PhoneBookTests extends BaseTest {

    @Test
    public void loginWithoutPasswordTestPositive() throws InterruptedException {
        Allure.description("Login without password. Positive test.");
        Allure.step("Main page creation ... ");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Open Login page ");

        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);

        Alert alert = loginPage.fillEmailField("kjdgljf@mail.com").clickByRegistrationButton();
        String expectedAlertText = "Wrong";

        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedAlertText);
        TakeScreen.takeScreenShot(getDriver(), "LoginWithoutPassword");
        Assert.assertTrue(isAlertHandled);
    }

    @Test
    public void registrationOfAnAlreadyRegisteredUser() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        String expectedAlertText = "User already exist";
        Alert alert = loginPage
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickByRegistrationButton();
        boolean result = AlertHandler.handleAlert(alert, expectedAlertText);
        Assert.assertTrue(result);

    }

    @Test
    public void successfulLogin() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReader.getProperty("myuser"))
                .fillPasswordField(PropertiesReader.getProperty("mypassword"))
                .clickByLoginButton();
        boolean res = ContactsPage
                .isElementPersist(getDriver().findElement(By.xpath("//button")));
        Assert.assertTrue(res);
    }

    @Test
    public void loginOfAnExistingUserAddContact() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReader.getProperty("myuser"))
                .fillPasswordField(PropertiesReader.getProperty("mypassword"))
                .clickByLoginButton();
        AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5, 5, 3),
                AddressGenerator.generateAddress(), "desc");
        addPage.fillContactFormAndSave(contact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(contact));

    }

    @Test
    public void deleteContactWithSerialization() throws IOException {
        String fileName = "contactDataFile.ser";
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReader.getProperty("myuser"))
                .fillPasswordField(PropertiesReader.getProperty("mypassword"))
                .clickByLoginButton();
        AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5, 5, 3),
                AddressGenerator.generateAddress(), "desc");
        addPage.fillContactFormAndSave(contact);
        Contact.serializationContact(contact, fileName);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Contact deserializedContact = Contact.deSerializationContact(fileName);

        Assert.assertNotEquals(contactsPage.deleteContactByPhoneNumber(deserializedContact.getPhone()),
                contactsPage.getContactListSize());
    }

    @Test
    public void successfulRegistration() {
        Allure.description("Successful registration test.");
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage.fillEmailField(EmailGenerator.generateEmail(5, 5, 3))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();
        if (alert == null) {
            ContactsPage contactsPage = new ContactsPage(getDriver());
            Assert.assertTrue(contactsPage.isElementPersist(getDriver()
                    .findElement(By.xpath("//button[contains(text(),'Sign Out')]"))));
        } else {
            TakeScreen.takeScreenShot(getDriver(), "Successful Registration");
        }

    }
}
