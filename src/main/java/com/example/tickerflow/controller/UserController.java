package com.example.tickerflow.controller;

import com.example.tickerflow.dto.JoinRequestDto;
import com.example.tickerflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(@RequestBody JoinRequestDto dto) {
        String username = dto.getUserId();



        try {
            //boolean result = userService.addUser(userId, pass, name_han, email);
//            if (result) {
//                return "login";
//            }
//            else {
//                return "redirect:/membership";
//            }
        }
        catch (UnexpectedRollbackException e) {
            //model.addAttribute("error", "회원가입 진행 중 오류가 발생하였습니다.");
            return "redirect:/membership";
        }
        return "";
    }

}
