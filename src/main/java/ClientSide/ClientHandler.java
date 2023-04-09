package ClientSide;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final JTextArea textArea;
    private final JButton send;
    private final JTextField input;
    private final JTextField name;
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ClientHandler(JTextArea textArea, JButton send, JTextField input, JTextField name, Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.textArea = textArea;
        this.send = send;
        this.input = input;
        this.name = name;
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                send.addActionListener(l -> {
                    try {
                        if (!input.getText().equals("")) {
                            dataOutputStream.writeUTF(name.getText() + ": " + input.getText() + "\n");
                            input.setText("");
                        }
                    } catch (IOException ignored) {
                    }
                });
                String data = dataInputStream.readUTF();
                textArea.append(data);
                dataOutputStream.flush();
            }
            socket.close();
        } catch (Exception ignored) {}
    }
}