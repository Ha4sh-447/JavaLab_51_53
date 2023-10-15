package Controller;

import Model.*;
import View.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Controller{
    Model model;
    View view;
    ObjectMapper mapper = new ObjectMapper();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        view.centerInitialSetup(model.getManageUserData().getLinesBeingDisplayed(), model.getManageUserData().getHeaders().size());
        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
        addScrolling();
        addButtonCreateSubs();
        addButtonClick();
        addButtonCreate();
        addButtonDeleteUsers();
        addButtonUpdateSubs();
        addButtonClickUpdateUser();
        deleteSubsActionListener();
    }

    public Controller(){

    }

    public void centerUsers(){
        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
    }

    public void centerSubs(Model m, View v){
//        view.centerInitialSetup(model.getManageSubsData().getLinesBeingDisplayed(), model.getManageSubsData().getHeaders().size());

        view.centerUpdate(model.getManageSubsData().getLines(model.getManageSubsData().getFirstLineToDisplay(), model.getManageSubsData().getLastLineToDisplay()), model.getManageSubsData().getHeaders());
//        addScrollingSubs();
    }

    private void addButtonClickUpdateUser() {
        view.getMf().getIp().getBp().getUpdate_users().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFrame frame =new JFrame("Update a Car");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 500);
                frame.setLayout(new FlowLayout());

                ObjectMapper mapper = new ObjectMapper();
                ArrayList<Users> usersArrayList =model.getManageUserData().getTable();

                frame.add(new JLabel("User ID: "));
                JTextField idInput = new JTextField(10);
                frame.add(idInput);
                JButton updateButton = new JButton("Update");
                frame.add(updateButton);

                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int carId = Integer.parseInt(idInput.getText());
                        String ask = JOptionPane.showInputDialog("1.UserName\n2.Contact\n3.Password\n4.DOB\n5.ProfilePic\n6. Email");
                        int choice = Integer.parseInt(ask);

                        switch (choice) {
                            case 1 -> {
                                String userName = JOptionPane.showInputDialog("Change to:");
                                usersArrayList.get(carId  - 1).setUserName(userName);
                            }
                            case 2 -> {
                                long contact = Long.parseLong(JOptionPane.showInputDialog("Change to:"));
                                usersArrayList.get(carId  - 1).setMobileNo(contact);
                            }
                            case 3 -> {
                                String password = JOptionPane.showInputDialog("Change to:");
                                usersArrayList.get(carId  - 1).setPassword(password);
                            }
                            case 4 -> {
                                String dob = JOptionPane.showInputDialog("Change to:");
                                usersArrayList.get(carId  - 1).setDob_string(dob);
                            }
                            case 5 -> {
                                String profilePic = JOptionPane.showInputDialog("Change to:");
                                usersArrayList.get(carId  - 1).setProfilePic(profilePic);
                            }
                            case 6 -> {
                                String email = JOptionPane.showInputDialog("Change to:");
                                usersArrayList.get(carId  - 1).setEmail(email);
                            }
                            default -> {
                                JOptionPane.showMessageDialog(null, "Error: Invalid Input");
                            }
                        }

                        try {
                            mapper.writeValue(Paths.get("src/Model/users.json").toFile(), usersArrayList);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        JOptionPane.showMessageDialog(null, "Update Completed.");
                        /* Start tryout */
                        model.getManageUserData().setLinesBeingDisplayed(25);
                        model.getManageUserData().setFirstLineToDisplay(0);
                        view.centerInitialSetup(model.getManageUserData().getLinesBeingDisplayed(), model.getManageUserData().getHeaders().size());
                        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
                        addScrolling();
                        /* End tryout */
                        frame.dispose();
                    }
                });
                frame.setVisible(true);
            }
        });
    }
    private void addButtonDeleteUsers(){
        view.getMf().getIp().getBp().getDelete_users().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("Delete Users pressed");
//                deleteUsers del = new deleteUsers();
//                view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
                JFrame frame = new JFrame("Delete User");
                frame.setSize(300, 150);
                frame.setLayout(new FlowLayout());

                JLabel idLabel = new JLabel("Enter User ID to Delete:");
                JTextField idInput = new JTextField(10);

                JButton deleteButton = new JButton("Delete");

                frame.add(idLabel);
                frame.add(idInput);
                frame.add(deleteButton);

                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int userToDelete = Integer.parseInt(idInput.getText());

                            ArrayList<Users> users = model.getManageUserData().getTable();

                            if (userToDelete >= 1 && userToDelete <= users.size()) {
                                String userName = users.get(userToDelete - 1).getUserName();
                                users.remove(userToDelete - 1);

                                ObjectMapper mapper = new ObjectMapper();
                                try {
                                    mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                    JOptionPane.showMessageDialog(frame, "User Deleted: " + userName);
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, "Invalid User ID");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                        }
                        frame.dispose();
                    }

                });

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        frame.dispose();
                    }
                });

                frame.setVisible(true);

            }
        });
    }

    public void deleteSubsActionListener(){
        view.getMf().getIp().getBp().getDelete_subs().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Delete Subs pressed");
                JFrame frame = new JFrame("Delete Subscription");
                frame.setSize(300, 150);
                frame.setLayout(new FlowLayout());

                JLabel idLabel = new JLabel("Enter Subscription ID to Delete:");
                JTextField idInput = new JTextField(10);

                JButton deleteButton = new JButton("Delete");

                frame.add(idLabel);
                frame.add(idInput);
                frame.add(deleteButton);

                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int subsToDelete = Integer.parseInt(idInput.getText());

                            ArrayList<Subscriptions> subscriptionsArrayList = model.getManageSubsData().getTable();

                            if (subsToDelete >= 1 && subsToDelete <= subscriptionsArrayList.size()) {
                                String subsName = subscriptionsArrayList.get(subsToDelete - 1).getAgreementName();
                                subscriptionsArrayList.remove(subsToDelete - 1);

                                ObjectMapper mapper = new ObjectMapper();
                                try {
                                    mapper.writeValue(Paths.get("src/Model/subscriptionsArrayList.json").toFile(), subscriptionsArrayList);
                                    JOptionPane.showMessageDialog(frame, "Subscription Deleted: " + subsName);
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, "Invalid Subscription ID");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                        }
                        frame.dispose();
                    }

                });

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        frame.dispose();
                    }
                });

                frame.setVisible(true);

            }
        });

    }

    private void addButtonUpdateSubs() {
        view.getMf().getIp().getBp().getUpdate_subs().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Update Subs pressed");
                JFrame frame = new JFrame("Update a Subscription");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 500);
                frame.setLayout(new FlowLayout());

                ObjectMapper mapper = new ObjectMapper();
                ArrayList<Subscriptions> subscriptionsArrayList = model.getManageSubsData().getTable();

                frame.add(new JLabel("User ID: "));
                JTextField idInput = new JTextField(10);
                frame.add(idInput);
                JButton updateButton = new JButton("Update");
                frame.add(updateButton);

                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int carId = Integer.parseInt(idInput.getText());
                        String ask = JOptionPane.showInputDialog("1.Subscription Name\n2.Price\n3.Description\n4.Start Date\n5.Duration\n6. Active Status");
                        int choice = Integer.parseInt(ask);

                        switch (choice) {
                            case 1 -> {
                                String subsName = JOptionPane.showInputDialog("Change to:");
                                subscriptionsArrayList.get(carId - 1).setAgreementName(subsName);
                            }
                            case 2 -> {
                                long price = Long.parseLong(JOptionPane.showInputDialog("Change to:"));
                                subscriptionsArrayList.get(carId - 1).setAgreementPrice((int) price);
                            }
                            case 3 -> {
                                String description = JOptionPane.showInputDialog("Change to:");
                                subscriptionsArrayList.get(carId - 1).setAgreementDescription(description);
                            }
                            case 4 -> {
                                String startDate = JOptionPane.showInputDialog("Change to:");
                                subscriptionsArrayList.get(carId - 1).setValid_from(startDate);
                            }
                            case 5 -> {
                                String duration = JOptionPane.showInputDialog("Change to:");
                                subscriptionsArrayList.get(carId - 1).setAgreementDurationMonths(Integer.parseInt(duration));
                            }
                            case 6 -> {
                                String activeStatus = JOptionPane.showInputDialog("Change to:");
                                subscriptionsArrayList.get(carId - 1).setActive(Boolean.parseBoolean(activeStatus));
                            }
                            default -> {
                                JOptionPane.showMessageDialog(null, "Error: Invalid Input");
                            }
                        }

                        try {
                            mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subscriptionsArrayList);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        JOptionPane.showMessageDialog(null, "Update Completed.");
                        /* Start tryout */
                        model.getManageSubsData().setLinesBeingDisplayed(25);
                        model.getManageSubsData().setFirstLineToDisplay(0);
                        view.centerInitialSetup(model.getManageSubsData().getLinesBeingDisplayed(), model.getManageSubsData().getHeaders().size());
                        view.centerUpdate(model.getManageSubsData().getLines(model.getManageSubsData().getFirstLineToDisplay(), model.getManageSubsData().getLastLineToDisplay()), model.getManageSubsData().getHeaders());
                        addScrollingSubs();
                        /* End tryout */
                        frame.dispose();
                    }
                });
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        frame.dispose();
                    }
                });

                frame.setVisible(true);
            }
        });
    }



    private void addButtonCreate() {
        view.getMf().getIp().getBp().getCreate_users().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                ObjectMapper mapper = new ObjectMapper();
                ArrayList<Users> usersArrayList;
                usersArrayList = model.getManageUserData().getTable();

                int carId = usersArrayList.size() + 1;

                Frame frame = new Frame("Add a User");
                frame.setSize(450, 500);

                Label usernameLabel = new Label("Username:");
                TextField usernameInput = new TextField(20);
                Label dobLabel = new Label("DOB(YYYY-MM-DD):");
                TextField dobInput = new TextField(20);
                Label contactLabel = new Label("Contact:");
                TextField contactInput = new TextField(20);
                Label passLabel = new Label("Password:");
                TextField passwordInput = new TextField(20);
                Label regLabel = new Label("Registration Date(YYYY-MM-DD):");
                TextField registerInput = new TextField(20);
                Label emailLabel = new Label("Email:");
                TextField emailInput = new TextField(20);
                Label accStatusLabel = new Label("Email:");
                TextField accStatusInput = new TextField(20);

                Button confirmButton = new Button("Confirm");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = usernameInput.getText();
                        String dob = dobInput.getText();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        Date date = null;
                        try {
                            date = dateFormat.parse(dob);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        Calendar calDOB = Calendar.getInstance();
                        calDOB.setTime(date);

                        long contactInputText = Long.parseLong(contactInput.getText());
                        String password = passwordInput.getText();
                        String reg = registerInput.getText();

                        Date date2 = null;
                        try {
                            date2 = dateFormat.parse(reg);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        Calendar calRegDate = Calendar.getInstance();
                        calRegDate.setTime(date2);

                        String emailInputText = emailInput.getText();
                        String acc = accStatusInput.getText();

                        int id = usersArrayList.get(usersArrayList.size() - 1).getUserId() + 1;

                        Users usersArrayListObj = new Users(reg, dob, id, username, calDOB, contactInputText, emailInputText, password, calRegDate, acc, "SmilyPic");
                        usersArrayList.add(usersArrayListObj);

                        try {
                            mapper.writeValue(Paths.get("src/Model/users.json").toFile(), usersArrayList);
                        } catch (IOException ex) {
                            throw  new RuntimeException(ex);
                        }

                        JOptionPane.showMessageDialog(null, "User successfully added (id: " + id + ")");
                        /* Start tryout */
                        model.getManageUserData().setLinesBeingDisplayed(25);
                        model.getManageUserData().setFirstLineToDisplay(0);
                        view.centerInitialSetup(model.getManageUserData().getLinesBeingDisplayed(), model.getManageUserData().getHeaders().size());
                        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
                        addScrolling();
                        /* End tryout */
                        frame.dispose();

                    }
                });

                frame.setLayout(new GridLayout(8, 2));
                frame.add(usernameLabel);
                frame.add(usernameInput);
                frame.add(dobLabel);
                frame.add(dobInput);
                frame.add(contactLabel);
                frame.add(contactInput);
                frame.add(passLabel);
                frame.add(passwordInput);
                frame.add(regLabel);
                frame.add(registerInput);
                frame.add(emailLabel);
                frame.add(emailInput);
                frame.add(accStatusLabel);
                frame.add(accStatusInput);
                frame.add(confirmButton);

                frame.setVisible(true);

            }
        });
    }

    private void addButtonCreateSubs(){
        view.getMf().getIp().getBp().getCreate_subs().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Create Subscription Pressed");
//                SubscriptionCreateForm createForm = new SubscriptionCreateForm();
//                ArrayList<Subscriptions> subs = createForm.createFormMethod();
//                centerSubs(model, view);

                ObjectMapper mapper = new ObjectMapper();
                ArrayList<Subscriptions> subsArrayList;
                subsArrayList = model.getManageSubsData().getTable();

                int carId = subsArrayList.size() + 1;

                Frame frame = new Frame("Add a Subscription");
                frame.setSize(450, 500);

                Label subsNameLabel = new Label("Subscription Name:");
                TextField subsNameInput = new TextField(20);
                Label descLabel = new Label("Description:");
                TextField descInput = new TextField(20);
                Label durationLabel = new Label("Duration (Months):");
                TextField durationInput = new TextField(20);
                Label priceLabel = new Label("Price:");
                TextField priceInput = new TextField(20);
                Label activeLabel = new Label("Active Status:");
                TextField activeInput = new TextField(20);
                Label startLabel = new Label("Start Date (YYYY-MM-DD):");
                TextField startInput = new TextField(20);


                Button confirmButton = new Button("Confirm");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String subsName = subsNameInput.getText();
                        String desc = descInput.getText();
                        String startdate = startInput.getText();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        Date date = null;
                        try {
                            date = dateFormat.parse(startdate);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        Calendar calStart = Calendar.getInstance();
                        calStart.setTime(date);

                        int duration = Integer.parseInt(durationInput.getText());
                        int price = Integer.parseInt(priceInput.getText());
                        boolean activeInputText = Boolean.parseBoolean(activeInput.getText());



                        int id = subsArrayList.get(subsArrayList.size() - 1).getAgreementID() + 1;

                        Subscriptions subsObj = new Subscriptions(startdate,id, subsName, price, duration, calStart, activeInputText, desc);
                        subsArrayList.add(subsObj);

                        try {
                            mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subsArrayList);
                        } catch (IOException ex) {
                            throw  new RuntimeException(ex);
                        }

                        JOptionPane.showMessageDialog(null, "User successfully added (id: " + id + ")");
                        /* Start tryout */
                        model.getManageUserData().setLinesBeingDisplayed(25);
                        model.getManageUserData().setFirstLineToDisplay(0);
                        view.centerInitialSetup(model.getManageUserData().getLinesBeingDisplayed(), model.getManageUserData().getHeaders().size());
                        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
                        addScrolling();
                        /* End tryout */
                        frame.dispose();
                    }
                });

                frame.setLayout(new GridLayout(7, 2));
                frame.add(subsNameLabel);
                frame.add(subsNameInput);
                frame.add(descLabel);
                frame.add(descInput);
                frame.add(durationLabel);
                frame.add(durationInput);
                frame.add(priceLabel);
                frame.add(priceInput);
                frame.add(activeLabel);
                frame.add(activeInput);
                frame.add(startLabel);
                frame.add(startInput);
                frame.add(confirmButton);

                frame.setVisible(true);

            }
        });
    }


    private void addButtonClick() {
        view.getMf().getIp().getBp().getBtn_users().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Users Pressed");
                addScrolling();
                centerUsers();
                view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
            }
        });

        view.getMf().getIp().getBp().getBtn_subs().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Subscription Pressed");
                addScrollingSubs();
                centerSubs(model, view);
            }
        });
    }

    private void addScrolling()
    {
        view.getMf().getIp().getCp().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int units = e.getUnitsToScroll();
                System.out.println(units);
                int current_first_line = model.getManageUserData().getFirstLineToDisplay();
                int current_last_line = model.getManageUserData().getLastLineToDisplay();
                int no_of_players = model.getManageUserData().getTable().size();
                int no_of_display_lines = model.getManageUserData().getLinesBeingDisplayed();
                if(units <= 0 && current_first_line == 0)
                {
                    model.getManageUserData().setFirstLineToDisplay(0);
                }
                else if(units <= 0 && current_first_line > 0)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line <= 0)
                    {
                        model.getManageUserData().setFirstLineToDisplay(0);
                    }
                    else
                    {
                        model.getManageUserData().setFirstLineToDisplay(new_first_line);
                    }
                }
                else if(units > 0 && current_last_line == no_of_players-1)
                {
                    model.getManageUserData().setFirstLineToDisplay(current_first_line);
                }
                else if (units > 0 && current_last_line < no_of_players-1)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line > no_of_players - no_of_display_lines)
                    {
                        new_first_line = no_of_players-no_of_display_lines;
                        model.getManageUserData().setFirstLineToDisplay(new_first_line);
                    }
                    else
                    {
                        model.getManageUserData().setFirstLineToDisplay(new_first_line);
                    }
                }

                view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
            }
        });
    }

    private void addScrollingSubs()
    {
        view.getMf().getIp().getCp().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int units = e.getUnitsToScroll();
                System.out.println(units);
                int current_first_line = model.getManageSubsData().getFirstLineToDisplay();
                int current_last_line = model.getManageSubsData().getLastLineToDisplay();
                int no_of_players = model.getManageSubsData().getTable().size();
                int no_of_display_lines = model.getManageSubsData().getLinesBeingDisplayed();
                if(units <= 0 && current_first_line == 0)
                {
                    model.getManageSubsData().setFirstLineToDisplay(0);
                }
                else if(units <= 0 && current_first_line > 0)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line <= 0)
                    {
                        model.getManageSubsData().setFirstLineToDisplay(0);
                    }
                    else
                    {
                        model.getManageSubsData().setFirstLineToDisplay(new_first_line);
                    }
                }
                else if(units > 0 && current_last_line == no_of_players-1)
                {
                    model.getManageSubsData().setFirstLineToDisplay(current_first_line);
                }
                else if (units > 0 && current_last_line < no_of_players-1)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line > no_of_players - no_of_display_lines)
                    {
                        new_first_line = no_of_players-no_of_display_lines;
                        model.getManageSubsData().setFirstLineToDisplay(new_first_line);
                    }
                    else
                    {
                        model.getManageSubsData().setFirstLineToDisplay(new_first_line);
                    }
                }

                view.centerUpdate(model.getManageSubsData().getLines(model.getManageSubsData().getFirstLineToDisplay(), model.getManageSubsData().getLastLineToDisplay()), model.getManageSubsData().getHeaders());
            }
        });
    }

    private Calendar createDate(){
        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        scanner.nextLine();

        // Create a Calendar object and set the input date
        Calendar start_date = Calendar.getInstance();
        start_date.set(Calendar.YEAR, year);
        // Calendar months are zero-based (0 - January, 1 - February, ...)
        start_date.set(Calendar.MONTH, month - 1);
        start_date.set(Calendar.DAY_OF_MONTH, day);

        return start_date;
    }

    private Users createUser(){

        Users users = null;
        System.out.print("Enter User Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.print("Enter User Id: ");
        int id = scanner.nextInt();

        System.out.print("Enter Mobile No: ");
        long mobile_no = scanner.nextLong();

        boolean chEmail = true;
        String email = "";
        while (chEmail) {
            System.out.print("Enter Email Id: ");
            email = scanner.next();
            if (!email.contains("@")) {
                System.out.println("Enter valid email address.");
            } else chEmail = false;
            break;
        }

        System.out.print("Enter Password: ");
        String password = scanner.next();
        scanner.nextLine();

        System.out.print("Enter Date of birth: ");
        Calendar dob = createDate();
//        Date dob = sdf.parse(node.get("dob").asText());
        String dobString = sdf.format(dob.getTime());
        System.out.println(dobString);

        System.out.println("Enter registration date: ");
        Calendar regDate = createDate();
        String regString = sdf.format(regDate.getTime());
        System.out.println(regString);

        System.out.println("Enter Account status of user(getActive/inactive): ");
        String accStatus = scanner.nextLine();

        System.out.println("Do you want to set profile picture? (Y/N): ");
        String ch = scanner.nextLine();

        if (ch.equals("Y")) {
            System.out.println("Enter profile picture: ");
            String userPic = scanner.nextLine();
//                        int userId, String userName, Date dob, long mobile_no, String email, String password, Date regDate, String accStatus, String profilePic
            users= new Users(regString,dobString,id, name, dob, mobile_no, email, password, regDate, accStatus, userPic);

//                                mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);

        } else if (ch.equals("N")) {
            users= new Users(regString, dobString, id, name, dob, mobile_no, email, password, regDate, accStatus);
        }
       return users;
    }

    private Subscriptions createSubs(ArrayList<Subscriptions> subs){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Subscription Name: ");
        String subsName = scanner.nextLine();

        System.out.print("Enter Subscription ID: ");
//        int subsID = scanner.nextInt();
        int subsID = subs.size()+1;
        System.out.println(subsID);

        System.out.print("Enter Subscription Price: ");
        int agreementPrice = scanner.nextInt();

        System.out.print("Enter Duration of Subscription in Months: ");
        int subsDuration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Active status (true or false): ");
        boolean subsActive = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter start date of Subscription (yyyy-mm-dd): ");
        Calendar validDate = createDate();
        String validdateString = sdf.format(validDate.getTime());

        System.out.print("Enter Description of Subscription: ");
        String subsDesc = scanner.nextLine();
        return new Subscriptions(validdateString, subsID, subsName, agreementPrice, subsDuration, validDate, subsActive, subsDesc);
    }

}
