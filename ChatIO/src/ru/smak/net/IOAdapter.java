package ru.smak.net;

import java.io.InputStream;

public class IOAdapter {
    public void displayText(String message) {
        displayText(message, Command.MESSAGE);
    }

    public void displayText(String message, Command command) {
        System.out.println(message);
    }

    public InputStream enterValue() {
        return System.in;
    }
}
