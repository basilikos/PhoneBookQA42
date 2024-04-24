package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static void main(String[] args) {
        System.out.println("RESULT : "+getProperty("myuser"));
    }
    private static final String PROPERTIES_FILE = "src/main/resources/resources.properties";

    public static String getProperty(String key){
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(PROPERTIES_FILE)){
            properties.load(fis);
            return properties.getProperty(key);
        }catch (IOException exception){
            exception.printStackTrace();
            return null;
        }


    }
}
