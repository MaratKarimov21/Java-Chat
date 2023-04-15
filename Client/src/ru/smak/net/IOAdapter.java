package ru.smak.net;

import java.io.*;

public class IOAdapter {
    private MainWindow gui;
    private Client client;
    public void setClient(Client client) {
        this.client = client;
    }
    public void setGui(MainWindow gui) {
        this.gui = gui;
    }
    public void display(String message) {
        display(message, Command.MESSAGE);
    }
    public void display(String message, Command command) {
        System.out.println(message);
        gui.outputTarget.append(message + "\n");
    }

    public void enter(String message) {
        client.send(message);
    }

    public void closeClient(){
        client.nio.stopReceiving();
    }
}
