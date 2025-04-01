package com.example.tickerflow.controller.util;

public class PasswordValidator {

    public static boolean isValid(String password) {
        return password != null
            && password.length() >= 10
            && password.matches(".*[a-zA-Z].*")
            && password.matches(".*[0-9].*")
            && password.matches(".*[^a-zA-Z0-9].*");
    }

    public static boolean isSamePassword(String password, String confirmPassword) {
        return password != null
            && password.equals(confirmPassword);
    }

}
