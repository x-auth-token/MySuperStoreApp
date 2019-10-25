package com.pm.mysuperstoreapp.models;

public class UserModel {

    private double uid;
    private String email;
    private String profilePictureUrl;



    private String role;
    private String lastLogin;



    private String firstname;
    private String lastname;

    public UserModel() {

    }

   public UserModel(double uid, String email, String firstname, String lastname) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public double getId() {
        return uid;
    }

    public void setId(double id) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
