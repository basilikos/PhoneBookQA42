package helpers;

import java.util.Random;

public class PasswordStringGenerator {

    public static void main(String[] args) {
        System.out.println("Result : " + generateRandomPassword());
    }

    /*метод generateRandomPassword() генерирует случайный пароль согласно требованиям. */
    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char charUpperCase = (char) ('A' + Math.random() * ('Z' - 'A') + 1);
                /*
                ('Z'-'A') - Это выражение вычисляет количество символов между 'A' и 'Z', что равно 25.
                Это нужно для создания диапазона возможных случайных чисел между 0 и 25, который будет
                использоваться для выбора случайной буквы.
                Math.random() - Это метод, который возвращает случайное число в диапазоне от 0.0 (включительно)
                 до 1.0 (исключительно).
                 Math.random()*('Z'-'A') -  генерирует случайное число в диапазоне от 0 до 25.
                 К символу 'A' прибавляется случайное число в диапазоне от 0 до 25,
                 что позволяет нам получить случайный символ в диапазоне от 'A' до 'Z'.
                 Коррекция "+1" учитывает включение верхней границы (буквы 'Z') в диапазон.
                 */
            password.append(charUpperCase);
        }
        for (int i = 0; i < 4; i++) {
            char charLowwerCase = (char) ('a' + Math.random() * ('z' - 'a') + 1);
            password.append(charLowwerCase);
        }

        /*Создается новый объект типа Random, который будет использоваться для генерации случайных чисел.
         * */
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10); // Вызывается метод nextInt(10), который генерирует случайное
            // целое число в диапазоне от 0 до 9 (включительно). Этот диапазон используется, так как мы генерируем цифры.
            password.append(digit);
        }
        String specialChar = "[@$#^&*!]";
        int specialCharecterCount = 2 + random.nextInt(3);
        for (int i = 0; i < specialCharecterCount; i++) {
            int index = random.nextInt(specialChar.length());
            char specChar = specialChar.charAt(index);
            password.append(specChar);
        }
        return password.toString();
    }


}
