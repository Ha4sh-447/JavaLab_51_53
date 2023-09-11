import Model.*;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {

//    method to create date from user input
    public static Calendar createDate(){
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


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        manageUsers mu = new manageUsers();
        manageSubscription ms = new manageSubscription();
        Subscriber subscriber = new Subscriber();
        ArrayList<Subscriptions> subs_useable = ms.readJsonSubs("src/Model/subscription.json");
        ArrayList<Users> user_useable =  mu.readjson("src/Model/users.json");
        ArrayList<Users> users = user_useable;
        ArrayList<Subscriptions> subs = subs_useable;


        int choice;
        int choice1 = 0;
        while(choice1 != 3) {
            System.out.println("Choose menu: \n1. Users\n2. Subscriptions");
            System.out.println("Enter choice: ");
            choice1 = scanner.nextInt();
            scanner.nextLine();
            if (choice1 == 1) {
                try {
                    do {
                        System.out.println(" User's menu:");
                        System.out.println("1. Create a User");
                        System.out.println("2. Update a Users");
                        System.out.println("3. Display a Users");
                        System.out.println("4. Display All Users");
                        System.out.println("5. Delete a User");
                        System.out.println("6. Exit");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (choice) {
                            case 1:

                                System.out.print("Enter User Name: ");
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

                                System.out.println("Enter registration date: ");
                                Calendar regDate = createDate();

                                System.out.println("Enter Account status of user(active/inactive): ");
                                String accStatus = scanner.nextLine();

                                System.out.println("Do you want to set profile picture? (Y/N): ");
                                String ch = scanner.nextLine();

                                if (ch.equals("Y")) {
                                    System.out.println("Enter profile picture: ");
                                    String userPic = scanner.nextLine();
//                        int userId, String userName, Date dob, long mobile_no, String email, String password, Date regDate, String accStatus, String profilePic
                                    Users usr = new Users(id, name, dob, mobile_no, email, password, regDate, accStatus, userPic);
                                    users.add(usr);
//                                mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);

                                } else if (ch.equals("N")) {
                                    Users usr = new Users(id, name, dob, mobile_no, email, password, regDate, accStatus);
                                    users.add(usr);
//                                mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                }

                                System.out.println("User created and added to the array.");

                                System.out.println("------------------------------------------------------------");
                                break;
                            case 2: //Update user
                                System.out.println("Enter ID of user to be updated: ");
                                int user_id = scanner.nextInt();
                                System.out.println("User: " + users.get(user_id - 1).getUserName());
                                scanner.nextLine();
                                int choice_change;
                                do {
                                    System.out.println("1. Change Name");
                                    System.out.println("2. Change Mobile No");
                                    System.out.println("3. Change Email Id");
                                    System.out.println("4. Change Password");
                                    System.out.println("5. Change Account Status");
                                    System.out.println("6. Exit");
                                    System.out.print("Enter your choice: ");
                                    choice_change = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choice_change) {
                                        case 1:

                                            System.out.println("Enter User name: ");
                                            String user_name = scanner.nextLine();
                                            users.get(user_id - 1).setUserName(user_name);
                                            mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                            break;

                                        case 2:
                                            System.out.println("Enter Mobile Number: ");
                                            long mobileNo_updated = scanner.nextLong();
                                            users.get(user_id - 1).setMobileNo(mobileNo_updated);
                                            mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                            break;

                                        case 3:
                                            System.out.println("Enter Email Id: ");
                                            String email_updated = scanner.nextLine();
                                            users.get(user_id - 1).setEmail(email_updated);
                                            mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                            break;

                                        case 4:
                                            System.out.print("Enter your email: ");
                                            String email_ = scanner.nextLine();
                                            if (email_.equals(users.get(user_id - 1).getEmail())) {
                                                System.out.println("Email verified.");
                                                System.out.print("Enter new password: ");
                                                String pass = scanner.nextLine();
                                                users.get(user_id - 1).changePassword(pass);
                                                mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                            } else {
                                                System.out.println("Wrong Email id.");
                                            }
                                            break;
                                        case 5:
                                            System.out.println("Current Account status: " + users.get(user_id - 1).getAccStatus());
                                            System.out.println("Enter Account status of User: ");
                                            String accStatus_updated = scanner.nextLine();
                                            users.get(user_id - 1).setAccStatus(accStatus_updated);
                                            mapper.writeValue(Paths.get("src/Model/users.json").toFile(), users);
                                        case 6:
                                            break;
                                        default:
                                            System.out.println("Invalid choice.");
                                            break;
                                    }
                                } while (choice_change != 6);


                                break;
                            case 3:
//                    Display User
//                        users = mu.readjson("src/Model/users.json");
                                System.out.println("Enter Id of user to display: ");
                                int userid = scanner.nextInt();
                                users.get(userid - 1).displayInfo();
                                break;
                            case 4:
//                    Display all users
                                mu.displayJSON(users);
                                break;
                            case 5:
//                   Delete a User
                                System.out.println("Enter Id of user to be deleted: ");
                                int user_id_del = scanner.nextInt();
                                System.out.println("Deleted User name: " + users.get(user_id_del - 1).getUserName());
                                users.remove(user_id_del - 1);
                                mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), users);
                                break;
                            case 6:
                                break;
                            default:
                                System.err.println("Invalid Choice.");
                                break;
                        }
                    } while (choice != 6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (choice1 == 2) {
                try {
                    do {
                        System.out.println(" Subscription's menu:");
                        System.out.println("1. Create a Subscription");
                        System.out.println("2. Update a Subscription");
                        System.out.println("3. Display a Subscription");
                        System.out.println("4. Display All Subscription");
                        System.out.println("5. Delete a Subscription");
                        System.out.println("6. Exit");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (choice) {
                            case 1:

                                System.out.print("Enter Subscription Name: ");
                                String subsName = scanner.nextLine();

                                System.out.print("Enter Subscription ID: ");
                                int subsID = scanner.nextInt();

                                System.out.print("Enter Subscription Price: ");
                                int subsPrice = scanner.nextInt();

                                System.out.print("Enter Duration of Subscription in Months: ");
                                int subsDuration = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter Active status (true or false): ");
                                boolean subsActive = scanner.nextBoolean();
                                scanner.nextLine();

                                System.out.print("Enter start date of Subscription (yyyy-mm-dd): ");
                                Calendar validDate = createDate();

                                System.out.print("Enter Description of Subscription: ");
                                String subsDesc = scanner.nextLine();
                                Subscriptions subsOBJ = new Subscriptions(subsID, subsName, subsPrice, subsDuration, validDate, subsActive, subsDesc);
                                subs.add(subsOBJ);
                                System.out.println("Successfully created Subscription!");
                                System.out.println("------------------------------------------------------------");
                                break;
                            case 2: //Update Subscription
                                System.out.println("Enter ID of Subscription to be updated: ");
                                int subs_id = scanner.nextInt();
                                System.out.println("User: " + subs.get(subs_id - 1).getAgreementName());
                                scanner.nextLine();
                                int choice_change;
                                do {
                                    System.out.println("1. Change Subscription");
                                    System.out.println("2. Cancel Subscription");
                                    System.out.println("3. Renew Subscription");
                                    System.out.println("4. Exit");
                                    System.out.print("Enter your choice: ");
                                    choice_change = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choice_change) {
                                        case 1:
//                                         Change subs
                                            System.out.println("Enter User name: ");
                                            String subs_name = scanner.nextLine();
                                            subs.get(subs_id - 1).setAgreementName(subs_name);
                                            mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subs);
                                            System.out.println("Subscription changed to: " + subs_name);
                                            break;

                                        case 2:
//                                        Cancel subs
                                            System.out.println("Are you sure you want to cancel your subscription(Y/N): ");
                                            String ch = scanner.nextLine();
                                            if (ch.equals("Y")) {
                                                subs.get(subs_id - 1).cancel_subscription();
                                            }
                                            break;

                                        case 3:
//                                        Renew subs/Extend subs
//                                            System.out.println("Enter extension duration: ");
//                                            int subs_month = scanner.nextInt();
//                                            scanner.nextLine();
//                                            subs.get(subs_id - 1).extendSubscription(subs_month);
                                            subscriber.input(users.get(subs_id-1), subs.get((subs_id-1)));
                                            System.out.println("Do you want to continue with existing subscription details?(Y/N): ");
                                            String ch1 = scanner.nextLine();

                                            if (ch1.equals("Y")) {
                                                subscriber.renew();
                                            } else if (ch1.equals("N")) {
                                                System.out.println("Enter Duration: ");
                                                int dur = scanner.nextInt();
                                                scanner.nextLine();
                                                subscriber.renew(dur);
                                            }
                                            break;

                                        case 4:
                                            break;
                                        default:
                                            System.out.println("Invalid choice.");
                                            break;
                                    }
                                } while (choice_change != 4);


                                break;
                            case 3:
//                    Display User
//                        users = mu.readjson("src/Model/users.json");
                                System.out.println("Enter Id of user to display: ");
                                int userid = scanner.nextInt();
                                subs.get(userid - 1).subsDisplay();
                                break;
                            case 4:
//                    Display all users
                                ms.displayJSONSubs(subs);
                                break;
                            case 5:
//                   Delete a User
                                System.out.println("Enter Id of user to be deleted: ");
                                int user_id_del = scanner.nextInt();
                                System.out.println("Deleted User name: " + subs.get(user_id_del - 1).getAgreementName());
                                subs.remove(user_id_del - 1);
                                mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), users);
                                break;
                            case 6:
                                break;
                            default:
                                System.err.println("Invalid Choice.");
                                break;
                        }
                    } while (choice != 6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        scanner.close();
    }
}


