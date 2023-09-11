package View;

import javax.swing.*;

public class MainFrame extends JFrame {

    private InitialPanel ip;

    public MainFrame() {
        super("IST 242 - Table Viewer");
        setupLayoutForMacs();
        ip = new InitialPanel();
        add(ip, "Center");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(1600,800);
        setVisible(true);
    }

    private void setupLayoutForMacs() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the ip
     */
    public InitialPanel getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(InitialPanel ip) {
        this.ip = ip;
    }
}
