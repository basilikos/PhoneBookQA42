package experiments;

import helpers.NameAndLastNameGenerator;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SqlInsertIntoStatement {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getBirthDate(int YYYY, int MM, int DD) {
        int year = YYYY - getRandomInt(1, 50);
        int month = MM - getRandomInt(1, 11);
        int day = DD - getRandomInt(1, 25);

        if (month == 2 && day > 28) {
            day -= day;
        }
        return "'" + year + "-" + month + "-" + day + "'";
    }

    public static String getGender(int number) {
        if (number % 2 == 0) {
            return "M";
        } else {
            return "F";
        }
    }

    public static int getId() {
        UUID uuid = UUID.randomUUID();
        if (uuid.hashCode() > 0) {
            return uuid.hashCode();
        } else {
            return -uuid.hashCode();
        }
    }

    public static void getValues(int number) {
        String insertInto = "INSERT INTO";
        String tableName = "qa42.persons";
        String columns = "(id, first_name, last_name, birth_date, gender)";
        for (int i = 1; i <= number; i++) {
            int id = getId();
            String firstName = NameAndLastNameGenerator.generateName();
            String lastName = NameAndLastNameGenerator.generateLastName();
            String birth_date = getBirthDate(2020, 12, 30);
            String gender = getGender(i);
            System.out.println(insertInto + " " + tableName + " " + columns);
            System.out.println(
                    "VALUES ("
                            + id + ", "
                            + "'" + firstName + "', "
                            + "'" + lastName + "', "
                            + birth_date + ", "
                            + "'" + gender + "');"
            );
        }
    }

    public static void main(String[] args) {
        getValues(100);
    }

}
