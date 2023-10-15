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
 * getActive : Stores the getActive status of the user
 */
public class Subscriber {

    Users Objuser;
    Subscriptions Objsubs;
    Calendar cal1 = Calendar.getInstance();
    private int userSubsId;
    private Calendar startDate;
    private Calendar endDate;
    private boolean active;


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
    public void setactive(boolean active){this.active = active;}

//    Getters
    public int getUserSubsId(){return this.userSubsId;}
    public Calendar getStartDate(){

        return this.startDate;}
    public Calendar getEndDate(){return this.endDate;}
    public boolean getactive(){return this.active;}


//    Input data
    public void input(Users tempUsers, Subscriptions tempSubs){
        this.Objsubs = tempSubs;
        this.Objuser = tempUsers;
//        cal1 = start_date;
    }

    public void displayTemp(){
        System.out.println("ID: "+ getUserSubsId() + "\nUser: " + this.Objuser.getUserName()+ "\nSubscription Name: " + this.Objsubs.getAgreementName());
    }

//   Display information
    public void display(){
        System.out.println("ID: "+ getUserSubsId() + "\nUser: " + this.Objuser.getUserName() + "\nSubscription Name: " + this.Objsubs.getAgreementName()
        + "\nCurrent status: " + (getactive() ? "Active": "Inactive") + "\nSubscription starts from: " + formatDate(getStartDate()) +"\nSubscription Ends on: " + formatDate(calculateEndDate()) + "\nSubscription remaining for days: "+this.Objuser.calculateDays(calculateEndDate()) );
    }

//    Method to renew subscription
    public Subscriptions renew(int newSubsDur, Subscriptions Objsubs){
        if(!getactive()){
            Objsubs.setAgreementDurationMonths(newSubsDur);
            setactive(true);
//            calculateEndDate(Objsubs);
            return Objsubs;
//            System.out.println("Subscription renewed for ."+ newSubsDur + " months.");
        }else{
            System.out.println("Already active");
            return Objsubs;
        }
    }

    public String renew(){
        if(!getactive() || Objuser.getAccStatus().equals("inactive")){
            Objsubs.setAgreementDurationMonths(3);
            setactive(true);
            calculateEndDate();
//            System.out.println("Subscription renewed for 3 months.");
           return "Subscription renewed for 3 months.";
        }else{
            return (Objsubs.getAgreementName() + " is already getActive. Can't renew right now");
//            System.out.println(Objsubs.getAgreementName() + " is already getActive. Can't renew right now");
        }
    }
//    Change user subscription
    public void changeSubs(String newSub){
        Objsubs.setAgreementName(newSub);
        System.out.println("Subscription changed to: " + Objsubs.getAgreementName());
        Calendar cal = Calendar.getInstance();
        System.out.println("Subscription starts from: " + cal.getTime());
        setStartDate(cal);
        calculateEndDate();
    }

//    Calculate End Date
    public Calendar calculateEndDate(){
        int duration = Objsubs.getAgreementDurationMonths();

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

    public void displayUserSubscriptionInfo(Object[][] userSubscriptionInfo) {
        for (int i = 0; i < userSubscriptionInfo.length; i++) {
            Users user = (Users) userSubscriptionInfo[i][0];
            Subscriptions subscription = (Subscriptions) userSubscriptionInfo[i][1];

            System.out.println("UserID: "+ user.getUserId()+" User: " + user.getUserName()+ " Email: "+ user.getEmail());
            System.out.println("Subscription Id: "+subscription.getAgreementID()+" Subscription Name: " + subscription.getAgreementName()+" Price: "+subscription.getAgreementPrice());
            // Display other relevant information from Users and Subscriptions classes
            System.out.println();
        }
    }
}
