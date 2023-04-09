import javax.swing.*;

public class AddressButtonsConnectServer {
    private final JFrame addressMenu;
    private final JFrame startMenu;
    private final JButton ok;
    private final JButton cancel;
    private final JTextField ip;
    private final JTextField port;
    private final JTextField name;


    public AddressButtonsConnectServer(JFrame addressMenu, JFrame startMenu, JButton ok, JButton cancel, JTextField ip, JTextField port, JTextField name) {
        this.addressMenu = addressMenu;
        this.startMenu = startMenu;
        this.ok = ok;
        this.cancel = cancel;
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    public JButton ok() {
        ok.addActionListener(l -> {
            startMenu.dispose();
            addressMenu.dispose();
            new Frames().chatMenuConnectServer(ip, port, name);
        });
        return ok;
    }

    public  JButton cancel() {
        cancel.addActionListener(l -> addressMenu.dispose());
        return cancel;
    }
}
