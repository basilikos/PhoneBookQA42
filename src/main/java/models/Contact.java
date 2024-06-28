package models;

import java.io.*;
import java.util.Objects;

public class Contact implements Serializable {
    private String id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;

    public Contact() {
    }

    public Contact(String name, String lastName, String phone, String email, String address, String description) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }

    public Contact(String id, String name, String lastName, String phone, String email, String address, String description) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }

    public static void serializationContact(Contact contact, String fileName) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(contact);
    }

    public static Contact deSerializationContact(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Contact) inputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Error during contact deSerialization...");
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return Objects.equals(getName(), contact.getName()) && Objects.equals(getLastName(), contact.getLastName()) && Objects.equals(getPhone(), contact.getPhone()) && Objects.equals(getEmail(), contact.getEmail()) && Objects.equals(getAddress(), contact.getAddress()) && Objects.equals(getDescription(), contact.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getPhone(), getEmail(), getAddress(), getDescription());
    }

    @Override
    public String toString() {
        return "Contact {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
