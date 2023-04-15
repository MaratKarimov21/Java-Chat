import ru.smak.net.Client;
import ru.smak.net.IOAdapter;
import ru.smak.net.MainWindow;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        var adapter = new IOAdapter();
        try {
            var wnd = new MainWindow(adapter);
            var client = new Client("localhost", 5003, adapter);

            adapter.setClient(client);
            adapter.setGui(wnd);

            client.startReceiving();
            wnd.setVisible(true);

        } catch (IOException e) {
            adapter.display("Ошибка: " + e.getMessage());
        }
    }
}
