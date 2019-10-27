/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.models;

// Represents role schema in Firebase Database
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
