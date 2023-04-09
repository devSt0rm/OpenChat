import ServerSide.Server;
import ClientSide.Client;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;

public class Frames {

    public void startMenu() { //start menu of application
        JFrame startMenu = new JFrame("Chat");
        JButton startServer = new JButton("Start server");
        JButton connectToServer = new JButton("Connect to server");
        StartMenuButtons buttons = new StartMenuButtons(startMenu, startServer, connectToServer);
        startMenu.setSize(280, 200);
        startMenu.add(buttons.connectToServer(), BorderLayout.EAST);
        startMenu.add(buttons.startServer(), BorderLayout.WEST);
        startMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startMenu.setResizable(false);
        startMenu.setVisible(true);
    }

    public void addressMenuConnectServer(JFrame startMenu) { //the menu for addressing of client part
        JFrame addressMenu = new JFrame("Address menu");
        JTextField ip = new JTextField("localhost");
        JTextField port = new JTextField();
        JTextField name = new JTextField();
        JButton cancel = new JButton("Cancel");
        JButton ok = new JButton("Ok");
        JLabel addressText = new JLabel("Address");
        JLabel portText = new JLabel("Port");
        JLabel nameText = new JLabel("Your name");
        AddressButtonsConnectServer addressButtons = new AddressButtonsConnectServer(addressMenu, startMenu, ok, cancel, ip, port, name);
        addressMenu.getContentPane().setBackground(Color.GRAY);
        addressMenu.setSize(549, 289);
        ip.setBounds(250, 38, 249, 40);
        port.setBounds(250, 86, 249, 40);
        name.setBounds(250, 137, 249, 40);
        cancel.setBounds(38, 200, 84, 26);
        ok.setBounds(391, 200, 51, 26);
        addressText.setBounds(23, 38, 99, 35);
        portText.setBounds(23, 87, 155, 23);
        nameText.setBounds(23, 134, 132, 38);
        addressMenu.add(ip);
        addressMenu.add(port);
        addressMenu.add(name);
        addressMenu.add(addressButtons.cancel());
        addressMenu.add(addressButtons.ok());
        addressMenu.add(addressText);
        addressMenu.add(portText);
        addressMenu.add(nameText);
        addressMenu.setResizable(false);
        addressMenu.setLayout(null);
        addressMenu.setVisible(true);
    }

    public void addressMenuCreateServer(JFrame startMenu) { //the menu for writing data to server side
        JFrame addressMenu = new JFrame("Address menu");
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        JTextField port = new JTextField();
        JTextField name = new JTextField();
        JLabel portText = new JLabel("Port");
        JLabel nameText = new JLabel("Your name");
        AddressButtonsCreateServer addressButtonsCreateServer = new AddressButtonsCreateServer(addressMenu, startMenu, ok, cancel, port, name);
        addressMenu.getContentPane().setBackground(Color.GRAY);
        addressMenu.setSize(369, 170);
        port.setBounds(164, 17, 188, 29);
        name.setBounds(164, 59, 188, 29);
        cancel.setBounds(28, 98,98, 35);
        ok.setBounds(258, 105, 84, 30);
        portText.setBounds(19, 17, 68, 29);
        nameText.setBounds(19, 59, 123, 29);
        addressMenu.add(addressButtonsCreateServer.ok());
        addressMenu.add(addressButtonsCreateServer.cancel());
        addressMenu.add(port);
        addressMenu.add(name);
        addressMenu.add(portText);
        addressMenu.add(nameText);
        addressMenu.setLayout(null);
        addressMenu.setResizable(false);
        addressMenu.setVisible(true);
    }

    public void chatMenuConnectServer(JTextField ip, JTextField port, JTextField name) { //the menu for writing data to client side
        JFrame chatMenu = new JFrame("Chat");
        JPanel screen = new JPanel();
        JPanel message = new JPanel();
        JButton send = new JButton("send");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        JTextField input = new JTextField(50);
        Client client = new Client(textArea, ip, port, name, send, input);
        textArea.setEditable(false);
        chatMenu.setSize(500, 700);
        screen.add(scrollPane);
        message.add(input);
        message.add(send);
        chatMenu.add(scrollPane, BorderLayout.CENTER);
        chatMenu.add(message, BorderLayout.SOUTH);
        chatMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chatMenu.setResizable(false);
        chatMenu.setVisible(true);
        Thread thread = new Thread(client::start);
        thread.start();
    }

    public void chatMenuCreateServer(JTextField port, JTextField name) { //the chat menu of server part
        JFrame chatMenu = new JFrame("Chat");
        JPanel screen = new JPanel();
        JPanel message = new JPanel();
        JButton send = new JButton("send");
        InetAddress ip;
        JLabel address;
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        JTextField input = new JTextField(50);
        Server server = new Server(textArea, port, name, send, input);
        textArea.setEditable(false);
        try {
            ip = InetAddress.getLocalHost();
            address = new JLabel(ip.toString());
            chatMenu.add(address, BorderLayout.NORTH);
        } catch (Exception ignored) {}
        chatMenu.setSize(500, 700);
        screen.add(scrollPane);
        message.add(input);
        message.add(send);
        chatMenu.add(scrollPane, BorderLayout.CENTER);
        chatMenu.add(message, BorderLayout.SOUTH);
        chatMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chatMenu.setResizable(false);
        chatMenu.setVisible(true);
        Thread thread = new Thread(server);
        thread.start();
    }
}