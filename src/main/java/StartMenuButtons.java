import javax.swing.*;

public class StartMenuButtons {
    private final JFrame startMenu;
    private final JButton startServer;
    private final JButton connectToServer;

    StartMenuButtons(JFrame startMenu, JButton startServer, JButton connectToServer) {
        this.startMenu = startMenu;
        this.startServer = startServer;
        this.connectToServer = connectToServer;
    }


    public JButton startServer() {
        startServer.addActionListener(l -> new Frames().addressMenuCreateServer(startMenu));
        return startServer;
    }

    public JButton connectToServer() {
        connectToServer.addActionListener(l -> new Frames().addressMenuConnectServer(startMenu));
        return connectToServer;
    }



}