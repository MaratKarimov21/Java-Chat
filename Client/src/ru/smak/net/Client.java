package ru.smak.net;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private String _host;
    private int _port;
    private Socket s;
    private NetIO nio;
    private IOAdapter ioadapter;
    public Client(String host, int port) throws IOException {
        _host = host;
        _port = port;
        s = new Socket(_host, _port);
        nio = new NetIO(s);
        ioadapter = new IOAdapter();
    }

    public void startReceiving(){
        new Thread(()->{
            try {
                nio.startReceiving(this::parse);
            } catch (IOException e) {
                ioadapter.displayText("Ошибка: " + e.getMessage());
            }
        }).start();
    }

    public Void parse(String d){
        var data = d.split(":", 2);
        Command cmd = null;
        try {
            cmd = Command.valueOf(data[0]);
        } catch (Exception ignored){
        }
        switch (cmd) {
            case INTRODUCE -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    ioadapter.displayText(data[1]);
                } else {
//                    ioadapter.displayText("Представьтесь, пожалуйста:");
                    ioadapter.displayText("Представьтесь, пожалуйста:");
                }
            }
            case MESSAGE -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    ioadapter.displayText(data[1]);
                }
            }
            case LOGGED_IN -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    ioadapter.displayText("Пользователь " + data[1] + " вошёл в чат");
                }
            }
            case LOGGED_OUT -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    ioadapter.displayText("Пользователь " + data[1] + " покинул чат");
                }
            }
            case null -> {

            }
        }
        return null;
    }

    public void send(String userData) {
        try {
            nio.sendData(userData);
        } catch (IOException e) {
            ioadapter.displayText("Ошибка: " + e);
        }
    }
}
