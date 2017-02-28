package com.example.james.science;

/**
 * Created by James on 14/12/2016.
 */

public class UserDetails {

    private String name;
    private String username;

    public UserDetails(){
        name = "";
        username = "";
    }

    public String getUsername() {
        return username;
    }

    public void setName(String input) {
        name = input;
    }

    public void setUsername(String input) {
        username = input;
    }
}
