package com.example.tickerflow.dto;

public class JoinRequestDto {

    private String userId;
    private String userPass;
    private String userPassConfirm;
    private String userEmail;

    public JoinRequestDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserPassConfirm() {
        return userPassConfirm;
    }

    public void setUserPassConfirm(String userPassConfirm) {
        this.userPassConfirm = userPassConfirm;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
