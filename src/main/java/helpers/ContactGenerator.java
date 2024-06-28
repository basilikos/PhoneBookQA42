package helpers;

import enums.ContactField;
import models.Contact;

public class ContactGenerator {

    private static Contact createContact(boolean incorrect, String incorrectValue, ContactField fieldName) {

        String name = NameAndLastNameGenerator.generateName();
        String lastName = NameAndLastNameGenerator.generateLastName();
        String email = EmailGenerator.generateEmail(5, 5, 3);
        String phoneNumber = PhoneNumberGenerator.generatePhoneNumber();
        String address = AddressGenerator.generateAddress();
        String description = "Test Contact";

        if (incorrect && fieldName != null) {
            switch (fieldName) {
                case NAME:
                    name = incorrectValue;
                    break;
                case LASTNAME:
                    lastName = incorrectValue;
                    break;
                case EMAIL:
                    email = incorrectValue;
                    break;
                case PHONE:
                    phoneNumber = incorrectValue;
                    break;
                case ADDRESS:
                    address = incorrectValue;
                    break;
                case DESCRIPTION:
                    description = incorrectValue;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name : " + fieldName);
            }
        }

        return new Contact(name, lastName, phoneNumber, email, address, description);
    }

    public static Contact createCorrectContact() {
        return createContact(false, null, null);
    }

    public static Contact createIncorrectContact(ContactField fieldName, String incorrectValue) {
        return createContact(true, incorrectValue, fieldName);
    }

    public static void main(String[] args) {
        Contact contact = createCorrectContact();
        System.out.println("createCorrectContact : " + contact.toString());
        System.out.println("***");
        Contact incorrectContact = createIncorrectContact(ContactField.EMAIL, "111");
        System.out.println("createIncorrectContact : " + incorrectContact.toString());
    }

}
