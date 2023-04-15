package ru.smak.net;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private String _host;
    private int _port;
    private Socket s;
    public NetIO nio;
    public IOAdapter adapter;
    public Client(String host, int port, IOAdapter adapter) throws IOException {
        _host = host;
        _port = port;
        s = new Socket(_host, _port);
        nio = new NetIO(s);
        this.adapter = adapter;
    }

    public void startReceiving(){
        new Thread(()->{
            try {
                nio.startReceiving(this::parse);
            } catch (IOException e) {
                adapter.display("Ошибка: " + e.getMessage());
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
                    adapter.display(data[1]);
                } else {
                    adapter.display("Представьтесь, пожалуйста:");
                }
            }
            case MESSAGE -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    adapter.display(data[1]);
                }
            }
            case LOGGED_IN -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    adapter.display("Пользователь " + data[1] + " вошёл в чат");
                }
            }
            case LOGGED_OUT -> {
                if (data.length > 1 && data[1].trim().length() > 0) {
                    adapter.display("Пользователь " + data[1] + " покинул чат");
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
            adapter.display("Ошибка: " + e);
        }
    }
}
