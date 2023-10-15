package View;

import Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

public class SubscriptionCreateForm extends JFrame{
    private JLabel subsName;
    private JTextField subsNameField;
    private JLabel subsDescLabel;
    private JTextField description;
    private JTextField duration;
    private JTextField priceField;
    private JTextField startDateField;
    private JTextField activeStatusField;
    private JButton OKButton;
    private JButton clearButton;
    private JPanel panelField;
    private JLabel validFrom;
    private JLabel active;
    private JLabel price;
    private JLabel heading;
    private ButtonGroup buttonGroup;

    private manageSubscription ms = new manageSubscription();
    Model model = new Model();
    ArrayList<Subscriptions> subs = model.getManageSubsData().getTable();

    public ArrayList<Subscriptions> createFormMethod(){
        setContentPane(panelField);
        setTitle("Subscription Creation Panel");
        setSize(550 ,700);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        System.out.println(ms.getTable().size());

        OKButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addSubsToSubscription();
                    System.out.println("Subs created");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subsNameField.setText("");
                description.setText("");
                priceField.setText("");
                startDateField.setText("");
                activeStatusField.setText("");
                duration.setText("");

            }
        });
        return subs;
    }

    private void addSubsToSubscription() throws IOException {

        String name = subsNameField.getText();
        String desc = description.getText();
        int price = Integer.parseInt(priceField.getText());
        String valid_from = startDateField.getText();
        boolean active= Boolean.parseBoolean(activeStatusField.getText());
        int months= Integer.parseInt(duration.getText());
        Calendar validFrom = Calendar.getInstance();

        if(name.isEmpty()||desc.isEmpty()||priceField.getText().isEmpty()||valid_from.isEmpty()||duration.getText().isEmpty()||activeStatusField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
            return ;
        }

//        ms.writeFromForm(name,price, valid_from, active, months, validFrom, desc);

        ObjectMapper mapper = new ObjectMapper();
        int id = subs.get(subs.size()-1).getAgreementID()+1;
        System.out.println(id);
        Subscriptions subsObj = new Subscriptions(valid_from,id, name, price, months, validFrom, active, desc);
        subs.add(subsObj);
        mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subs);
        JOptionPane.showConfirmDialog(this, "Created Successfully", "Created", JOptionPane.INFORMATION_MESSAGE);
    }

    public ArrayList<Subscriptions> getSubs() {
        return subs;
    }

    public static void main(String[] args) {
        SubscriptionCreateForm subscriptionCreateForm = new SubscriptionCreateForm();
        subscriptionCreateForm.createFormMethod();
    }
}
