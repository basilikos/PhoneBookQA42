package models;

import java.util.List;

public class ContactListModel {

    private List<Contact> contacts;

    public ContactListModel() {
    }

    public ContactListModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "ContactListModel{" + "contacts=" + contacts + '}';
    }
}
