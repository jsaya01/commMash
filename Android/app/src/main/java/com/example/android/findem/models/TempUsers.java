package com.example.android.findem.models;

import java.util.ArrayList;
import java.util.List;

public class TempUsers {
    private List<User> users = new ArrayList<>();

    public TempUsers(){
        User john = new User("John", "Doe",
                "username", "pass", "hello I am John.",
                "https://github.com/jsaya01/commMash/blob/master/images/man_test.jpg");

        User jane = new User("Jane", "Doe",
                "username", "pass", "hello I am Jane.",
                "https://github.com/jsaya01/commMash/blob/master/images/woman_test.jpg");

        users.add(john);
        users.add(jane);
    }

    public List<User> getUsers(){
        return users;
    }
}
