package com.pm.mysuperstoreapp.models;

public class RoleModel {

    private boolean admin;



    private boolean user;

    public RoleModel(){
        admin = false;
        user = true;
    }



    public boolean isAdmin() {
        return admin;
    }

    protected void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
}
