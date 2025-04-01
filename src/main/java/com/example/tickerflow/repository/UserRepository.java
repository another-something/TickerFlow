package com.example.tickerflow.repository;

import com.example.tickerflow.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> readAll();

    User save(User user);

    Optional<User> findByUserId(String userId);

    void flush();

    int existsByUserId(String userId);

}
