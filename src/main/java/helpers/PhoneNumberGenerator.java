package helpers;

import java.util.Random;

public class PhoneNumberGenerator {

    public static void main(String[] args) {
        System.out.println("PhoneNumber : "+generatePhoneNumber());
    }
    private static final int MIN_LENGTH = 10;
    private static final int MAX_LENGTH = 15;


    public static String generatePhoneNumber(){
        Random random = new Random();
        int length = random.nextInt(MAX_LENGTH-MIN_LENGTH-1)+MIN_LENGTH;
        StringBuilder phoneNumber  = new StringBuilder();
        for(int i = 0; i< length; i++){
            if(i==0){
                phoneNumber.append(random.nextInt(9)+1);
            }
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }













}
