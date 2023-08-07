import Model.Subscriptions;
import Model.Users;
import Model.Subscriber;

import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;



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
        int currentUserCount = 0;
        int currentSubsCount = 0;
        int currentSubscriberCount = 0;

        Scanner scanner = new Scanner(System.in);

        Users[] user_arr = new Users[100];
        Subscriptions[] subs_arr = new Subscriptions[20];
        Subscriber[] subscriber_arr = new Subscriber[50];

        int choice;
        int choice1;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add a new User");
            System.out.println("2. Display a User");
            System.out.println("3. Add a new Subscription");
            System.out.println("4. Display a Subscription");
            System.out.println("5. Add a Subscriber");
            System.out.println("6. Display all Subscribers");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    if (currentUserCount < user_arr.length) {
                        System.out.print("Enter User Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter User Id: ");
                        int id = scanner.nextInt();

                        System.out.print("Enter Mobile No: ");
                        long mobile_no = scanner.nextLong();

                        System.out.print("Enter Email Id: ");
                        String email = scanner.next();

                        System.out.print("Enter Password: ");
                        String password = scanner.next();
                        scanner.nextLine();

                        System.out.print("Enter Date of birth: ");
                        Date dob = createDate().getTime();

                        System.out.println("Enter registration date: ");
                        Date regDate = createDate().getTime();

                        System.out.println("Enter Account status of user(active/inactive): ");
                        String accStatus = scanner.nextLine();

                        // Create the object dynamically
                        user_arr[currentUserCount] = new Users();
                        currentUserCount++;

                        user_arr[currentUserCount-1].setUserId(id);
                        user_arr[currentUserCount-1].setUserName(name);
                        user_arr[currentUserCount-1].setDOB(dob);
                        user_arr[currentUserCount-1].setMobileNo(mobile_no);
                        user_arr[currentUserCount-1].setEmail(email);
                        user_arr[currentUserCount-1].setPassword(password);
                        user_arr[currentUserCount-1].setAccStatus(accStatus);
                        user_arr[currentUserCount-1].setRegDate(regDate);

                        System.out.println("User created and added to the array.");
                    } else {
                        System.out.println("Array is full. Cannot create more Users.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Id of the User to Display: ");
                    int userID = scanner.nextInt();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("UserId : "+ user_arr[userID - 1].getUserId());
                    System.out.println("Name : "+ user_arr[userID - 1].getUserName());
                    System.out.println("DOB : "+ user_arr[userID - 1].getDOB());
                    System.out.println("MobileNo : "+ user_arr[userID - 1].getMobileNo());
                    System.out.println("Email : "+ user_arr[userID - 1].getEmail());
                    System.out.println("Account status : "+ user_arr[userID - 1].getAccStatus());
                    System.out.println("Registration Date : "+ user_arr[userID - 1].getRegDate());
                    System.out.println("--------------------------------------------------------");
                    break;
                case 3:
                    if (currentSubsCount < subs_arr.length) {
                        System.out.print("Enter Subscription Name: ");
                        String subsName = scanner.nextLine();

                        System.out.print("Enter Subscription ID: ");
                        int subsID = scanner.nextInt();

                        System.out.print("Enter Subscription Price: ");
                        int subsPrice = scanner.nextInt();

                        System.out.print("Enter Duration of Subscription in Months: ");
                        int subsDuration = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter Description of Subscription: ");
                        String subsDesc = scanner.nextLine();

                        System.out.println("Enter Active status (true or false): ");
                        boolean subsActive = scanner.nextBoolean();
                        scanner.nextLine();

                        System.out.println("Enter start date of Subscription (yyyy-mm-dd): ");
                        Date validDate = createDate().getTime();

                        // Create the object dynamically
                        subs_arr[currentSubsCount] = new Subscriptions();
                        currentSubsCount++;

                        subs_arr[currentSubsCount-1].setSubsID(subsID);
                        subs_arr[currentSubsCount-1].setSubsName(subsName);
                        subs_arr[currentSubsCount-1].setSubsPrice(subsPrice);
                        subs_arr[currentSubsCount-1].setSubsDuration(subsDuration);
                        subs_arr[currentSubsCount-1].setSubsDesc(subsDesc);
                        subs_arr[currentSubsCount-1].setIsActive(subsActive);
                        subs_arr[currentSubsCount-1].setValidFrom(validDate);

                        System.out.println("Subscription created and added to the array.");
                    } else {
                        System.out.println("Array is full. Cannot create more Subscriptions.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Id of the Subscription to Display: ");
                    int subsIDDisplay = scanner.nextInt();

                    System.out.println("--------------------------------------------------------");
                    System.out.println("Subscription ID : "+ subs_arr[subsIDDisplay - 1].getSubsID());
                    System.out.println("Subscription Name : "+ subs_arr[subsIDDisplay - 1].getSubsName());
                    System.out.println("Subscription Description : "+ subs_arr[subsIDDisplay - 1].getSubsDesc());
                    System.out.println("Subscription Price : "+ subs_arr[subsIDDisplay - 1].getSubsPrice());
                    System.out.println("Duration of Subscription in Months : "+ subs_arr[subsIDDisplay - 1].getSubsDuration());
                    System.out.println("--------------------------------------------------------");
                    break;
                case 5:
                    if (currentSubscriberCount < subscriber_arr.length) {
                        System.out.println("Enter ID of Subscriber: ");
                        int subscriber_id = scanner.nextInt();

                        System.out.print("Enter Id of the User: ");
                        int user_id = scanner.nextInt();

                        System.out.print("Enter Id of the Subscription: ");
                        int subscription_id = scanner.nextInt();

                        System.out.println("Enter the start date (yyyy mm dd) of Subscription:");
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

                        System.out.println("Enter active status of Subscriber(true/false): ");
                        Boolean active = scanner.nextBoolean();

                        subscriber_arr[currentSubscriberCount] = new Subscriber();
                        currentSubscriberCount++;

                        subscriber_arr[currentSubscriberCount-1].input(user_arr[user_id - 1], subs_arr[subscription_id - 1], start_date);
                        subscriber_arr[currentSubscriberCount-1].setStartDate(start_date);
                        subscriber_arr[currentSubscriberCount-1].setIsActive(active);
                        subscriber_arr[currentSubscriberCount-1].setUserSubsId(subscriber_id);

                        System.out.println("Subscriber created and added to the array.");
                    } else {
                        System.out.println("Array is full. Cannot create more Subscribers.");
                    }
                    break;
                case 6:
                    for(int i = 0; i < currentSubscriberCount; i++) {
                        subscriber_arr[i].display();
                        do {
                            System.out.println("------------------Subscriber Menu------------------");
                            System.out.println("1. Renew Subscription");
                            System.out.println("2. Change Subscription");
                            System.out.println("3. Exit");
                            choice1 = scanner.nextInt();
                            scanner.nextLine();
                            switch(choice1){
                                case 1: {
                                    // Handle subscription renewal here
                                    subscriber_arr[i].renew();
                                    break;
                                }
                                case 2: {
                                    // Handle subscription change here
                                    System.out.println("Enter Name of new subscription: ");
                                    String newSubs = scanner.nextLine();
                                    subscriber_arr[i].changeSubs(newSubs);
                                    System.out.println("Subscription changed.");
                                    break;
                                }
                                case 3:
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                                    break;
                            }
                        }while(choice1 != 3);

                    }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while (choice != 7);

        scanner.close();
    }
}

