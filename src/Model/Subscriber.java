package Model;
import java.time.Month;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Identification comments:
 *   Name: Harsh Saindane
 *   Experiment No: 1
 *   Experiment Title: Implementing Many-to-Many Relationships using Classes and Objects
 *   Experiment Date: 25-07-2023
 *   @version 1.0
 *
 *
 * Beginning comments:
 * Filename: UsersSubscription.java
 * @author:  Harsh Saindane
 *
 * Attribute comments:
 * Objuser : User class object
 * Objsubs : Subscription class object
 * userSubsId : Stores unique Id of both User and Subscription
 * startDate : Date of start of subscription
 * endDate : Date of end of subscription
 * isActive : Stores the active status of the user
 *
 *
 * Changes Made on 2/8/2023
 * added calculateEndDate() method to calculate end date
 * modified data type of all the dates to Calendar
 * updated renew() and changeSubs() method
 * updated display() method
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
    public void setUserSubsId(){
        this.userSubsId = (int)Math.random()*50 + 1;
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
        + "\nCurrent status: " + (getIsActive() ? "Active": "Inactive") + "\nSubscription starts from: " + getStartDate().getTime() +"\nSubscription Ends on: " + calculateEndDate().getTime() );
    }

//    Method to renew subscription
    public void renew(){
        if(!getIsActive() && Objuser.getAccStatus().equals("inactive")){
            System.out.println("Enter duration for Subscription (3/6/12 Months): ");
            Scanner sc = new Scanner(System.in);
            int months = sc.nextInt();
            Objsubs.setSubsDuration(months);
            setIsActive(true);
            calculateEndDate();
            System.out.println("Subscription renewed.");
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
}