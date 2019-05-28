package com.example.android.findem.utils;

import java.util.ArrayList;

public class StringGenerator {
    private static String[] messages = {"Send your first message!",
            "Hello!",
            "What is up?",
            "You like long egg?",
            "Wanna carpool to Faleesi's pool party?"};

    public static String getRandomMessage() {
        int rand = (int) (Math.floor(Math.random()));

        return messages[rand % messages.length];
    }
}
