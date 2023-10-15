package View;

import Controller.Controller;
import Model.Model;
import Model.Subscriptions;
import Model.Users;
import Model.manageUsers;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateUserForm extends JFrame{
    private JTextField username;
    private JTextField dob;
    private JTextField contact;
    private JPasswordField passwordField1;

    private JTextField regDate;
    private JTextField email;
    private JTextField accStatus;
    private JButton OKButton;
    private JButton CLEARButton;
    private JPanel panelField;

    manageUsers mu = new manageUsers();
    ArrayList<Users> usersArrayList = mu.getTable();
//    Model model = new Model();
//    View view = new View();
//    Controller controller = new Controller(model, view);

    public CreateUserForm() throws ParseException, IOException {
        setContentPane(panelField);
        setTitle("User Creation Panel");
        setSize(550 ,700);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);
        System.out.println(mu.getTable().size());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("HEYY CLOSING SUCKER");
//                controller.centerUsers();
                System.out.println("AFTER CENTERING");
            }
        });

        OKButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addUsers();

                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        CLEARButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                dob.setText("");
                regDate.setText("");
                passwordField1.setText("");
                contact.setText("");
                email.setText("");
                accStatus.setText("");
            }
        });
    }



    public void addUsers() throws ParseException, IOException {
        String name = username.getText();
        String userDob = dob.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = dateFormat.parse(userDob);
        Calendar calDOB = Calendar.getInstance();
        calDOB.setTime(date);
        long contactinfo = Long.parseLong(contact.getText());
        String UserRegDate = regDate.getText();

        Date date2 = dateFormat.parse(userDob);
        Calendar calRegDate = Calendar.getInstance();
        calRegDate.setTime(date2);

        String UaccStatus = accStatus.getText();
        String userEmail = email.getText();

        char[] passwordChars = passwordField1.getPassword();
        String pass = new String(passwordChars);


        if(name.isEmpty()||userDob.isEmpty()||contact.getText().isEmpty()||UserRegDate.isEmpty()||UaccStatus.isEmpty()||userEmail.isEmpty()|| pass.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
            return ;
        }

//        ms.writeFromForm(name,price, valid_from, active, months, validFrom, desc);

        ObjectMapper mapper = new ObjectMapper();
        int id = usersArrayList.get(usersArrayList.size()-1).getUserId()+1;
        System.out.println(id);
//        String regDate_string,String dob_string,int userId, String userName, Calendar dob, long mobile_no, String email, String password, Calendar regDate, String accStatus, String profilePic
        Users usersArrayListObj = new Users(UserRegDate,  userDob, id, name,calDOB, contactinfo, userEmail, pass, calRegDate, UaccStatus, "SmilyPic" );
        usersArrayList.add(usersArrayListObj);
        mapper.writeValue(Paths.get("src/Model/users.json").toFile(), usersArrayList);
        JOptionPane.showConfirmDialog(this, "Created Successfully", "Created", JOptionPane.INFORMATION_MESSAGE);
//        controller.centerUsers();
        close();
    }

    public ArrayList<Users> getUsersArrayList() {
        return usersArrayList;
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

// Then when saving:

//    Users user = addUsers();



    public void centerUsers(Model model, View view){
        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
    }

    public static void main(String[] args) throws ParseException, IOException {
        CreateUserForm createUserForm = new CreateUserForm();
    }
}
