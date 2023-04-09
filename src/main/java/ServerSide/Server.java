package ServerSide;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final JTextArea textArea;
    private final JTextField port;
    private final JTextField name;
    private final JButton send;
    private final JTextField input;

    public Server(JTextArea textArea, JTextField port, JTextField name, JButton send, JTextField input) {
        this.textArea = textArea;
        this.port = port;
        this.name = name;
        this.send = send;
        this.input = input;
    }
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(Integer.parseInt(port.getText()));
            textArea.append("Server's working\n");
            while (true) {
                Socket socket = ss.accept();
                textArea.append("User is connected\n");
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (socket.isConnected()) {
                    send.addActionListener(l -> {
                        try {
                            if (!input.getText().equals("")) {
                                dataOutputStream.writeUTF(name.getText() + ": " + input.getText() + "\n");
                                textArea.append(name.getText() + ": " + input.getText() + "\n");
                                input.setText("");
                            }
                        } catch (IOException ignored) {}
                    });
                    String data = dataInputStream.readUTF();
                    textArea.append(data);
                    dataOutputStream.flush();
                }
                socket.close();

            }
        } catch (Exception ignored) {}
    }

}
