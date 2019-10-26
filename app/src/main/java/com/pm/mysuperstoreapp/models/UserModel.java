package com.pm.mysuperstoreapp.models;

import java.util.Calendar;

public class UserModel {

    private String uid;
    private String email;
    private String profilePictureUrl;
    private RoleModel role;
    //private String role;
    private String lastLogin;
    private String displayName;



    public UserModel() {

    }

   public UserModel(String uid, String email, String displayName) {
        this.uid = uid;

        this.displayName = displayName;
        this.email = email;
        this.role = new RoleModel();
        this.lastLogin = Calendar.getInstance().getTime().toString();

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public RoleModel getRole() {
        return this.role;
    }

    public String roleToString() {

        if (role.isAdmin())
            return "admin";

        return "user";
    }

    //public void setRole(String role) { this.role = role; }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }



}
