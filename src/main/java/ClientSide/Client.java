package ClientSide;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {
    private final JTextArea textArea;
    private final JTextField ip;
    private final JTextField port;
    private final JTextField name;
    private final JButton send;
    private final JTextField input;

    public Client(JTextArea textArea, JTextField ip, JTextField port, JTextField name, JButton send, JTextField input) {
        this.textArea = textArea;
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.send = send;
        this.input = input;
    }
    public void start() {
            try {
                Socket socket = new Socket(ip.getText(), Integer.parseInt(port.getText()));
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                while (socket.isConnected()) {
                    send.addActionListener(l -> {
                        try {
                            if (!input.getText().equals("")) {
                                dataOutputStream.writeUTF(name.getText() + ": " + input.getText() + "\n");
                                textArea.append(name.getText() + ": " + input.getText() + "\n");
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
            } catch (Exception e) {
                textArea.setText("Connection is lost");
            }
        }
    }