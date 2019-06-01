package com.example.android.findem.utils;

public class StringGenerator {
    private static String[] messages = {"Send your first message!",
            "Hello!",
            "What is up?",
            "You like long egg?",
            "Wanna carpool to Faleesi's pool party?"};

    public static String getRandomMessage() {
        int rand = (int) (Math.floor(Math.random() * 50 + 1));

        return messages[rand % messages.length];
    }
}
