package com.example.tickerflow.controller.util;

import com.example.tickerflow.dto.ApiResponse;
import com.example.tickerflow.service.UserService;

public class UserValidator {

    public static ApiResponse validatorUsername(String username, UserService userService) {

        if (username == null || username.length() < 6 || username.length() > 16 || !username.matches("[a-z0-9\\-_]+")) {
            return new ApiResponse(false, "아이디 양식에 맞게 작성해주세요.");
        }

        if (!userService.existsByUserId(username)) {
            return new ApiResponse(false, "중복된 ID 입니다. 다른 ID를 입력해주세요.");
        }

        return new ApiResponse(true, "사용 가능한 ID 입니다.");
    }

}
