package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel {

    ArrayList<JButton> header_buttons = new ArrayList<>();
    ArrayList<JButton> student_buttons = new ArrayList<>();

    public CenterPanel()
    {
        super();
    }

    public void createDisplay(int rows, int cols)
    {
        this.setLayout(new GridLayout(rows+1,cols));
        for (int i = 1; i <= cols; i++)
        {
            JButton b = new JButton();
            b.setBackground(Color.cyan);
            b.setPreferredSize(new Dimension(80,25));
            header_buttons.add(b);
            this.add(b);
            validate();
            repaint();
        }
        for (int i = 1; i <= rows * cols; i++)
        {
            JButton b = new JButton();
            b.setBackground(Color.cyan);
            b.setPreferredSize(new Dimension(120,25));
            student_buttons.add(b);
            this.add(b);
            validate();
            repaint();
        }

    }

    public void updateDisplay(ArrayList<ArrayList<String>> lines, ArrayList<String> headers) {
        for (int i = 0; i < headers.size(); i++)
        {
            setHeaderButtonText(i,headers.get(i));
        }

        for (int player_no = 0; player_no < lines.size(); player_no++)
        {
            for (int player_col_no = 0; player_col_no < headers.size(); player_col_no++)
            {
                int button_no = player_no * headers.size() + player_col_no;
                String button_txt = lines.get(player_no).get(player_col_no);
                setPlayerButtonText(button_no,button_txt);
            }
        }
    }

    public void setHeaderButtonText(int button_no, String button_text) {
        header_buttons.get(button_no).setText(button_text);
    }
    public void setPlayerButtonText(int button_no, String button_text) {
        student_buttons.get(button_no).setText(button_text);
    }

    public ArrayList<JButton> getHeaderButtons()
    {
        return header_buttons;
    }

    public ArrayList<JButton> getPlayerButtons()
    {
        return student_buttons;
    }

}
