package com.example.android.findem.models;

import java.util.ArrayList;

public class TempUsers {
    public static ArrayList<User> getTempUsers(){
        ArrayList<User> users = new ArrayList<>();
        User john = new User("John", "Doe",
                "username", "pass", "hello I am John.",
                "https://github.com/jsaya01/commMash/blob/master/images/man_test.jpg");

        User jane = new User("Jane", "Doe",
                "username", "pass", "hello I am Jane.",
                "https://github.com/jsaya01/commMash/blob/master/images/woman_test.jpg");

        users.add(john);
        users.add(jane);
        return users;
    }

}
