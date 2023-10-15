package View;

import Model.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class deleteSubsForm extends JFrame{

    private JTextField idField;
    private JButton confirmButton;
    private JPanel delpanelField;

    Model model = new Model();
    manageSubscription manageSubscription = new manageSubscription();

    public deleteSubsForm(){
        setContentPane(delpanelField);
        setTitle("Delete Subs panel");
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
        ArrayList<Subscriptions> subscriptionsArrayList = manageSubscription.getTable();
        int id = Integer.parseInt(idField.getText())-1;
        System.out.println(subscriptionsArrayList.get(id).getAgreementID()+ " "+ subscriptionsArrayList.get(id).getAgreementName());
        manageSubscription.delete(id);
    }



    public static void main(String[] args) {

        deleteSubsForm del  = new deleteSubsForm();
    }
}

