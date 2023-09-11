package View;

import javax.swing.*;

public class InitialPanel extends JPanel {

    private CenterPanel cp;

    public InitialPanel() {
        super();
        cp = new CenterPanel();
        add(cp);
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


}
