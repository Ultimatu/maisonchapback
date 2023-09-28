package com.tonde.maisonchapback.services.mail;

import com.tonde.maisonchapback.exceptions.CustomLogger;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {

    private static final int KEY_LENGTH = 32;
    private static final int CODE_LENGTH = 6;

    public static String generateUniqueKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[KEY_LENGTH];
        random.nextBytes(keyBytes);


        return Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
    }

    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }


        return code.toString();
    }

    public static void main(String[] args) {
        String uniqueKey = generateUniqueKey();
        CustomLogger.log("INFO", "Unique key: " + uniqueKey);
        String code = generateRandomCode();
        CustomLogger.log("INFO", "Code: " + code);
    }
}
