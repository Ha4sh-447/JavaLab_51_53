package View;

import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UpdataSubsForm extends JFrame{
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JPanel paneField;
    private JTextField idField;
    private JLabel resultMessage;

    Model model = new Model();
    private Subscriber subscriber = new Subscriber();

    int[] renewList = {3, 6, 9, 12};

    public UpdataSubsForm(){
        setContentPane(paneField);
        setTitle("Update Subscription panel");
        setSize(500 ,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        resultMessage.setVisible(false);
        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    update();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void update() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Subscriptions> subscriptionsArrayList = model.getManageSubsData().getTable();
        int id = Integer.parseInt(idField.getText())-1;
        int renew = renewList[comboBox1.getSelectedIndex()];
//        Subscriptions updateSubs = subscriber.renew(renew, (Subscriptions) subscriptionsArrayList.get(id));
//        subscriptionsArrayList.remove(id);
//        mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subscriptionsArrayList);        // Create a new JFrame for displaying the renewStr
        if (id < 0 || id >= subscriptionsArrayList.size()) {
            // Handle the case where the ID is out of bounds
            JOptionPane.showMessageDialog(null, "Invalid ID");
            return;
        }
        Subscriptions subscriptionToUpdate = subscriptionsArrayList.get(id);

        // Update the subscription
        subscriptionToUpdate.setAgreementDurationMonths(renew);

        // Write the updated list back to the JSON file
        mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subscriptionsArrayList);
        String st = subscriptionToUpdate.getAgreementName()+" "+ " renewed for "+subscriptionToUpdate.getAgreementDurationMonths()+" months.";


        resultMessage.setText(st);
        resultMessage.setVisible(true);

    }

    public static void main(String[] args) {
        UpdataSubsForm updataSubsForm = new UpdataSubsForm();
    }
}
