package View;

import javax.swing.*;

public class InitialPanel extends JPanel {

    private CenterPanel cp;
    private ButtonPanel btn_p;

    public InitialPanel() {
        super();
        this.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        btn_p = new ButtonPanel();
        this.add(btn_p);
        validate();
        repaint();
        cp = new CenterPanel();
        this.add(cp);
        validate();
        repaint();
    }

    public CenterPanel getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(CenterPanel cp) {
        this.cp = cp;
    }

    public ButtonPanel getBp() {
        return btn_p;
    }

    /**
     * @param btn_p the cp to set
     */
    public void setBp(ButtonPanel btn_p) {
        this.btn_p = btn_p;
    }
}
