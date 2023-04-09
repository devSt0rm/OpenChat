import javax.swing.*;

public class AddressButtonsCreateServer {
    private final JFrame addressMenu;
    private final JFrame startMenu;
    private final JButton ok;
    private final JButton cancel;
    private final JTextField port;
    private final JTextField name;


    public AddressButtonsCreateServer(JFrame addressMenu, JFrame startMenu, JButton ok, JButton cancel, JTextField port, JTextField name) {
        this.addressMenu = addressMenu;
        this.startMenu = startMenu;
        this.ok = ok;
        this.cancel = cancel;
        this.port = port;
        this.name = name;
    }

    public JButton ok() {
        ok.addActionListener(l -> {
            startMenu.dispose();
            addressMenu.dispose();
            new Frames().chatMenuCreateServer(port, name);
        });
        return ok;
    }

    public  JButton cancel() {
        cancel.addActionListener(l -> addressMenu.dispose());
        return cancel;
    }
}
