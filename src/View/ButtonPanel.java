package View;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton btn_users = new JButton();
    JButton btn_subs = new JButton();

    public ButtonPanel(){
        btn_users.setBackground(Color.cyan);
        btn_users.setPreferredSize(new Dimension(80,25));
        btn_users.setText("Users");
        this.add(btn_users);
        validate();
        repaint();
        btn_subs.setBackground(Color.cyan);
        btn_subs.setPreferredSize(new Dimension(80,25));
        btn_subs.setText("Subscriptions");
        this.add(btn_subs);
        validate();
        repaint();
    }

    public JButton getBtn_users() {
        return btn_users;
    }

    public JButton getBtn_subs() {
        return btn_subs;
    }
}
