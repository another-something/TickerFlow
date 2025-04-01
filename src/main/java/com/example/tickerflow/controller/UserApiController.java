package com.example.tickerflow.controller;

import com.example.tickerflow.controller.util.PasswordValidator;
import com.example.tickerflow.dto.ApiResponse;
import com.example.tickerflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    /* 아이디 유효성 검사, 중복 아이디 검사 */
    @PostMapping("/check-id")
    public ResponseEntity<ApiResponse> checkId(@RequestBody Map<String, Object> params) {
        String userId = (String) params.get("userId");
        if (!isValidId(userId)) {
            return ResponseEntity.ok(new ApiResponse(false,  "양식에 맞게 작성해주세요."));
        }
        boolean exists = userService.existsByUserId(userId);
        if (!exists){
            return ResponseEntity.ok(new ApiResponse(false,  "중복된 ID 입니다. 다른 ID를 입력해주세요."));
        }
        return ResponseEntity.ok(new ApiResponse(true, "사용 가능한 ID 입니다."));
    }

    public static boolean isValidId(String id) {
        return id != null && id.length() >= 6 && id.length() <= 16 && id.matches("[a-z0-9\\-_]+");
    }

    /* 패스워드 형식 유효성 검사 */
    @PostMapping("/check-pw")
    public ResponseEntity<ApiResponse> checkPw(@RequestBody Map<String, Object> params){
        String userPass = (String) params.get("userPass");
        if (PasswordValidator.isValid(userPass)) {
            return ResponseEntity.ok(new ApiResponse(true, "사용 가능한 비밀번호입니다."));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, "영문, 숫자, 특수문자를 포함한 10자리 이상이어야 합니다."));
        }
    }

    @PostMapping("/check-same-pw")
    public ResponseEntity<ApiResponse> checkSamePw(@RequestBody Map<String, Object> params){

        String userPass = (String) params.get("userPass");
        String userPassConfirm = (String) params.get("userPassConfirm");

        if (PasswordValidator.isSamePassword(userPass, userPassConfirm)) {
            return ResponseEntity.ok(new ApiResponse(true, "패스워드가 일치합니다."));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, "패스워드가 불일치합니다."));
        }
    }

    @PostMapping("/check-email")
    public ResponseEntity<ApiResponse> register(@RequestBody Map<String, Object> params) {
        String email = (String) params.get("email");
        if(email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
            return ResponseEntity.ok(new ApiResponse(true, "이메일 형식이 일치합니다."));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, "이메일 형식에 맞게 작성해주세요."));
        }
    }
}
