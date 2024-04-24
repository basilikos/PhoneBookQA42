package pages;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean getDataFromContactList(Contact contact) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement nameInContact = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[contains(text(),'" + contact.getName().toString() + "')]")));
        nameInContact.click();
        WebElement editButton = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
        editButton.click();

        WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        String elementNameValue = elementName.getAttribute("value");
        WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        String elementLastNameValue = elementLastName.getAttribute("value");

        WebElement elementPhone = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
        String elementPhoneValue = elementPhone.getAttribute("value");

        WebElement elementEmail = driver.findElement(By.xpath("//input[@placeholder='email']"));
        String elementEmailValue = elementEmail.getAttribute("value");

        WebElement elementAddress = driver.findElement(By.xpath("//input[@placeholder='Address']"));
        String elementAddressValue = elementAddress.getAttribute("value");

        WebElement elementDescription = driver.findElement(By.xpath("//input[@placeholder='desc']"));
        String elementDescriptionValue = elementDescription.getAttribute("value");

        Contact listContact = new Contact();
        listContact.setName(elementNameValue);
        listContact.setLastName(elementLastNameValue);
        listContact.setPhone(elementPhoneValue);
        listContact.setEmail(elementEmailValue);
        listContact.setAddress(elementAddressValue);
        listContact.setDescription(elementDescriptionValue);
        boolean result = listContact.equals(contact);
        return result;

    }
    public int getContactListSize(){
        return getContactsList().size();
    }
    protected List<WebElement> getContactsList(){
        return driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
    }
    public void clickRemoveButton(){
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();
    }
    public int deleteContactByPhoneNumber(String phoneNumberOrName){
        List<WebElement> contactList = getContactsList();
        int initSize = contactList.size();
        try {
            for (WebElement contact : contactList){
                WebElement phoneNumberData = contact
                        .findElement(By.xpath("//h2[text()='"+phoneNumberOrName+"'] | //h3[text()='"+phoneNumberOrName+"']"));
                if(phoneNumberData.isDisplayed()){
                    phoneNumberData.click();
                    clickRemoveButton();
                    break;
                }
            }
        }catch (NoSuchElementException exception){exception.fillInStackTrace();
            System.out.println("The item "+ phoneNumberOrName+" was not found...");}
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions
                    .numberOfElementsToBe(By.xpath("//div[@class='contact-item_card__2SOIM']"), initSize-1));
            return contactList.size();
    }

}
