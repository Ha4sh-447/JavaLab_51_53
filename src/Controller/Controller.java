package Controller;

import Model.*;
import View.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        addButtonClick();
        addButtonCreate();
        addButtonDeleteUsers();
        addButtonUpdate();
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

    private void addButtonDeleteUsers(){
        view.getMf().getIp().getBp().getDelete_users().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Delete Users pressed");
                deleteUsers del = new deleteUsers();
                view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
            }
        });
    }

    public void deleteSubsActionListener(){
        view.getMf().getIp().getBp().getDelete_subs().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Delete Subs pressed");
                deleteSubsForm del = new deleteSubsForm();
                centerSubs(model, view);
            }
        });

    }

    private void addButtonUpdate(){
        view.getMf().getIp().getBp().getUpdate_users().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Update Users pressed");
            }
        });

        view.getMf().getIp().getBp().getUpdate_subs().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Update Subs pressed");
                UpdataSubsForm updataSubsForm = new UpdataSubsForm();
            }
        });
    }


    private void addButtonCreate(){
        view.getMf().getIp().getBp().getCreate_users().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Create Users pressed");
                CreateUserForm userForm = null;
                try {
                    userForm = new CreateUserForm();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                userForm.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        centerUsers(); // call method to update view
                    }
                });
                ArrayList<Users> usersArrayList = model.getManageUserData().getTable();
                System.out.println(usersArrayList.size());
                System.out.println(usersArrayList.size()+"hello");
            }
        });

        view.getMf().getIp().getBp().getCreate_subs().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Create Subscription Pressed");
                SubscriptionCreateForm createForm = new SubscriptionCreateForm();
                ArrayList<Subscriptions> subs = createForm.createFormMethod();
                centerSubs(model, view);
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
