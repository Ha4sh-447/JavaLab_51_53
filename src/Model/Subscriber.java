package Model;
import java.time.Month;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Identification comments:
 *   Name: Harsh Saindane
 *   Experiment No: 2
 *   Experiment Title: Implementing Many-to-Many relationships using Arrays
 *   Experiment Date: 01-08-2023
 *   @version 1.0
 *
 *
 * Beginning comments:
 * Filename: Subscriber.java
 * @author:  Harsh Saindane
 *
 * Attribute comments:
 * Objuser : User class object
 * Objsubs : Subscription class object
 * userSubsId : Stores unique Id of both User and Subscription
 * startDate : Date of start of subscription
 * endDate : Date of end of subscription
 * isActive : Stores the active status of the user
 */
public class Subscriber {

    Users Objuser;
    Subscriptions Objsubs;
    Calendar cal1 = Calendar.getInstance();
    private int userSubsId;
    private Calendar startDate;
    private Calendar endDate;
    private boolean isActive;


//    Setters
    public void setUserSubsId(int userSubsId){
        this.userSubsId = userSubsId;
    }
    public void setStartDate(Calendar start_date){
        this.startDate = start_date;
        this.endDate = calculateEndDate();

    }
    public void setEndDate(Calendar endDate){
        this.endDate = endDate;
    }
    public void setIsActive(boolean isActive){this.isActive = isActive;}

//    Getters
    public int getUserSubsId(){return this.userSubsId;}
    public Calendar getStartDate(){

        return this.startDate;}
    public Calendar getEndDate(){return this.endDate;}
    public boolean getIsActive(){return this.isActive;}


//    Input data
    public void input(Users tempUsers, Subscriptions tempSubs, Calendar start_date){
        this.Objsubs = tempSubs;
        this.Objuser = tempUsers;
//        cal1 = start_date;
    }

//   Display information
    public void display(){
        System.out.println("ID: "+ getUserSubsId() + "\nUser: " + this.Objuser.getUserName() + "\nSubscription Name: " + this.Objsubs.getSubsName()
        + "\nCurrent status: " + (getIsActive() ? "Active": "Inactive") + "\nSubscription starts from: " + formatDate(getStartDate()) +"\nSubscription Ends on: " + formatDate(calculateEndDate()) );
    }

//    Method to renew subscription
    public void renew(){
        if(!getIsActive() || Objuser.getAccStatus().equals("inactive")){
            System.out.println("Enter duration for Subscription (3/6/12 Months): ");
            Scanner sc = new Scanner(System.in);
            int months = sc.nextInt();
            Objsubs.setSubsDuration(months);
            setIsActive(true);
            calculateEndDate();
            System.out.println("Subscription renewed for ."+ months + " months.");
        }else{
            System.out.println(Objsubs.getSubsName() + " is already active. Can't renew right now");
        }
    }

    public void renew(int newSubsDur){
        if(!getIsActive() || Objuser.getAccStatus().equals("inactive")){
            Objsubs.setSubsDuration(3);
            setIsActive(true);
            calculateEndDate();
            System.out.println("Subscription renewed for 3 months.");
        }else{
            System.out.println(Objsubs.getSubsName() + " is already active. Can't renew right now");
        }
    }
//    Change user subscription
    public void changeSubs(String newSub){
        Objsubs.setSubsName(newSub);
        System.out.println("Subscription changed to: " + Objsubs.getSubsName());
        Calendar cal = Calendar.getInstance();
        System.out.println("Subscription starts from: " + cal.getTime());
        setStartDate(cal);
        calculateEndDate();
    }

//    Calculate End Date
    public Calendar calculateEndDate(){
        int duration = Objsubs.getSubsDuration();

        if(getStartDate() != null){
            Calendar calculatedEndDate = (Calendar)getStartDate().clone();
            calculatedEndDate.add(Calendar.MONTH, duration);

            setEndDate(calculatedEndDate);
            return getEndDate();
        }else {
            return getStartDate();
        }
    }

//    formats Date to display only date and not time
//    Wed Nov 17 12:01:52 IST 2004 --> 17 Nov 2004
    public static String formatDate(Calendar obj){
        String[] st = obj.getTime().toString().split(" ");
        String newdate = st[2].concat(" ").concat(st[1]).concat(" ").concat(st[5]);
        return newdate;
    }

}
