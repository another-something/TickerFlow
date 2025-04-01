package com.example.tickerflow.controller;

import com.example.tickerflow.controller.util.SHA256Util;
import com.example.tickerflow.domain.User;
import com.example.tickerflow.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.tickerflow.controller.util.CommonUtil.isNullOrEmpty;

@Controller
public class LoginController {


    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> loginMember(@RequestBody User userInfo, Model model, HttpSession session){
        String userId = userInfo.getUsername();
        String userPass = userInfo.getPassword();

        Map<String, Object> resultMap = new HashMap<String ,Object>();

        System.out.println("로그인 처리 중..");

        System.out.printf("Request Parameter: userId=%s userPass=%s", userId, userPass);

        if (isNullOrEmpty(userId) || isNullOrEmpty(userPass)) {
            System.out.println("isNullOrEmpty(userId) || isNullOrEmpty(userPass)" + (isNullOrEmpty(userId) || isNullOrEmpty(userPass)));
            resultMap.put("code", 101);
            resultMap.put("message", "아이디와 패스워드를 입력해주세요.");
            return ResponseEntity.ok(resultMap);
        }

        Optional<User> optUser = userService.findByUserId(userId);
        if (optUser.isEmpty()) { /* 값이 없으면 검색된 아이디가 없음*/
            resultMap.put("code", 102);
            resultMap.put("message", "아이디와 패스워드를 확인해주세요.");
            return ResponseEntity.ok(resultMap);
        }

        User user = optUser.get();
        String pass = SHA256Util.encrypt(userPass, user.getSalt());

        if(pass.equals(user.getPassword())){
            resultMap.put("code", 200);
            resultMap.put("message", "로그인 성공!");
            session.setAttribute("userInfo", user);
        }
        else {
            resultMap.put("code", 103);
            resultMap.put("message", "아이디와 패스워드가 일치하지 않습니다.");
        }
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/logout")
    public String logoutMember(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model) throws Exception {
        User user = (User) session.getAttribute("userInfo");
        System.out.printf("user is null? %s", user == null);

        if (user != null) {
            /* 기존에 아래 방식으로 사용하였으나 기능은 정상작동 함. 다만, 해당 소스 gpt에게 보여준 후 보완부분 알려달라고 하였더니 지적 받아 전체 세션을 삭제하는 것으로 변경*/
            //session.removeAttribute("userInfo");
            session.invalidate();
            redirectAttributes.addFlashAttribute("success", "정상적으로 로그아웃 되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "로그인된 정보가 없습니다.");
        }

        return "redirect:/login";
    }

}
