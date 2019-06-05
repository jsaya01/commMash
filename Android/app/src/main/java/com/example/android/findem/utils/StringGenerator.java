package com.example.android.findem.utils;

import java.util.Random;

public class StringGenerator {
    private StringGenerator() {
        throw new IllegalStateException("Utility class");
    }

    private static String[] messages = {"Send your first message!",
            "Hello!",
            "What is up?",
            "You like long egg?",
            "Wanna carpool to Faleesi's pool party?"};

    public static String getRandomMessage() {
        int rand = Math.abs(new Random().nextInt() * 50 + 1);

        return messages[rand % messages.length];
    }
}
