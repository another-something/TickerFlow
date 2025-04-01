package com.example.tickerflow.controller.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class SHA256Util {

    public static String  encrypt (String value, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String text = value + salt;
            byte[] hash = messageDigest.digest(text.getBytes());

            StringBuilder hex = new StringBuilder();

            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();
        }
        catch (Exception e) {
            throw new RuntimeException("SHA-256 암호화 실패", e);
        }
    }

    public static String salt () {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

}
