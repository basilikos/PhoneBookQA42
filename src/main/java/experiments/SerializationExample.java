package experiments;

import java.io.*;

public class SerializationExample {

    public static void main(String[] args) {
        Person person = new Person("1234", 30);

        try {
            FileOutputStream fileOut = new FileOutputStream("person.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(person);
            out.close();
            fileOut.close();
            System.out.println("Serialized!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileIn = new FileInputStream("person.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Person newPerson = (Person) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("getName " + newPerson.getName());
            System.out.println("getAge" + newPerson.getAge());
        } catch (ClassNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
