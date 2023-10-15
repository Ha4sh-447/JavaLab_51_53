package View;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton btn_users = new JButton();
    JButton btn_subs = new JButton();
    JButton create_users = new JButton();
    JButton create_subs = new JButton();
    JButton delete_users = new JButton();
    JButton delete_subs = new JButton();
    JButton update_users = new JButton();
    JButton update_subs = new JButton();


    public ButtonPanel(){
        btn_users.setBackground(Color.cyan);
        btn_users.setPreferredSize(new Dimension(80,25));
        btn_users.setText("Users");
        this.add(btn_users);
        validate();
        repaint();
        btn_subs.setBackground(Color.cyan);
        btn_subs.setPreferredSize(new Dimension(80,25));
        btn_subs.setText("Subs");
        this.add(btn_subs);
        validate();
        repaint();
        create_users.setBackground(Color.cyan);
        create_users.setPreferredSize(new Dimension(150,25));
        create_users.setText("Create users");
        this.add(create_users);
        validate();
        repaint();
        create_subs.setBackground(Color.cyan);
        create_subs.setPreferredSize(new Dimension(150,25));
        create_subs.setText("Create Subs");
        this.add(create_subs);
        validate();
        repaint();
        delete_users.setBackground(Color.cyan);
        delete_users.setPreferredSize(new Dimension(150,25));
        delete_users.setText("Delete Users");
        this.add(delete_users);
        validate();
        repaint();
        delete_subs.setBackground(Color.cyan);
        delete_subs.setPreferredSize(new Dimension(150,25));
        delete_subs.setText("Delete Subs");
        this.add(delete_subs);
        validate();
        repaint();
        update_users.setBackground(Color.cyan);
        update_users.setPreferredSize(new Dimension(150,25));
        update_users.setText("Update Users");
        this.add(update_users);
        validate();
        repaint();
        update_subs.setBackground(Color.cyan);
        update_subs.setPreferredSize(new Dimension(150,25));
        update_subs.setText("Update Subs");
        this.add(update_subs);
        validate();
        repaint();
    }

    public JButton getBtn_users() {
        return btn_users;
    }

    public JButton getBtn_subs() {
        return btn_subs;
    }

    public JButton getCreate_users(){return create_users;}

    public JButton getCreate_subs(){return create_subs;}

    public JButton getDelete_users(){
        return delete_users;
    }

    public JButton getDelete_subs() {
        return delete_subs;
    }

    public JButton getUpdate_users() {
        return update_users;
    }

    public JButton getUpdate_subs() {
        return update_subs;
    }

}
