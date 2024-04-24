package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesWriter {
    public static void main(String[] args) throws IOException {
        writeProperty("test1","result1", true);
    }

    private static final String PROPERTIES_FILE = "src/main/resources/resources.properties"; // Здесь определен путь к файлу свойств в виде строки.

    public static void writeProperty(String key, String value, boolean cleanFile) throws IOException {
        /*Этот метод принимает три параметра: key - ключ свойства, value - значение свойства, которое нужно записать,
         и cleanFile - флаг, указывающий, нужно ли очистить файл перед записью нового свойства.*/
        Properties properties = new Properties(); // Создается объект Properties, который будет хранить свойства.
        /*Класс Properties в Java используется для работы с файлами свойств (.properties),
         которые представляют собой текстовые файлы для хранения конфигурационных данных в формате пар ключ-значение. */
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            if (!Files.exists(Paths.get(PROPERTIES_FILE))) {
                Files.createFile(Paths.get(PROPERTIES_FILE));
            }
            fileInputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(fileInputStream);
            if (cleanFile) {
                properties.clear();
            }
            properties.setProperty(key, value);
            fileOutputStream = new FileOutputStream(PROPERTIES_FILE);
            properties.store(fileOutputStream, "");
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
    }

}
