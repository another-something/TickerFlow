package com.example.tickerflow.service;

import com.example.tickerflow.controller.util.SHA256Util;
import com.example.tickerflow.domain.User;
import com.example.tickerflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean addUser(String userId, String pass, String nameHan, String email) {
        String salt = SHA256Util.salt();/* 암호화된 랜덤값 가져오기 */
        System.out.println("#1 " + salt);
        String encrypted = SHA256Util.encrypt(pass, salt); /* 랜덤값과 사용자가 입력한 패스워드를 합친 후 암호화 진행 해시방식 */
        System.out.println("#2 " + salt);
        User user = new User(userId, encrypted, nameHan, email, salt);
        try {
            User saved = userRepository.save(user);
            userRepository.flush();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace(); // 로그 확인용
            return false;
        }
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean existsByUserId(String userId) {
        return 0 == userRepository.existsByUserId(userId);
    }
}
