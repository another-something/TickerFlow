package com.example.tickerflow.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userinfo_seq")
    @SequenceGenerator(name = "userinfo_seq", sequenceName = "userinfo_seq", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE", nullable = true)
    private String phone;

    @Column(name = "EMAIL", nullable = true)
    private String email;

    @Column(name = "EMAIL_VERIFIED", nullable = true)
    private String email_verified;

    @Column(name = "CREATED_AT", nullable = true)
    private String created_at;

    @Column(name = "UPDATED_AT", nullable = true)
    private String updated_at;

    @Column(name = "SALT")
    private String salt;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "STATUS")
    private String status;

    public User(){
    }

    public User(String username, String password, String name, String email, String salt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.salt = salt;
    }

    public User(Long id, String username, String password, String name, String phone, String email, String email_verified, String created_at, String updated_at, String salt, String role, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.email_verified = email_verified;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.salt = salt;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
