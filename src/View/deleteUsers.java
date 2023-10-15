package View;

import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class deleteUsers extends JFrame {
    private JTextField idField;
    private JButton confirmButton;
    private JPanel delpanelField;

    Model model = new Model();
    manageUsers manageUsers = new manageUsers();

    public deleteUsers(){
        setContentPane(delpanelField);
        setTitle("Delete User panel");
        setSize(400 ,400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteFromUsers();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void deleteFromUsers() throws IOException {
        ArrayList<Users> usersArrayList = manageUsers.getTable();
        int id = Integer.parseInt(idField.getText())-1;
        if(idField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(usersArrayList.get(id).getUserId()+ " "+ usersArrayList.get(id).getUserName());
        manageUsers.delete(id);
        JOptionPane.showConfirmDialog(this, "Deleted Successfully", "Deleted", JOptionPane.INFORMATION_MESSAGE);
    }



    public static void main(String[] args) {

        deleteUsers del  = new deleteUsers();
    }
}
