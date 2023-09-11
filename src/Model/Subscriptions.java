package Model;
import java.util.Calendar;
import java.util.Date;
/**
 * Identification comments:
 *   Name: Sarang
 *   Experiment No: 2
 *   Experiment Title: Implementing Many-to-Many relationships using Arrays
 *   Experiment Date: 01-08-2023
 *   @version 1.0
 *
 *
 * Beginning comments:
 * Filename: Subscription.java
 * @author:  Harsh Saindane
 * /*  Overview: This class is created to store the attributes of Subscription.
 *
 * Attribute comments:
 * subsID : Stores subscription Id
 * subsName : Stores name of subscription
 * subsDesc : Stores description of subscription
 * subsPrice : Stores price of subscription
 * subsDurationMonths : Duration of subscription
 * isActive : Stores active status of user
 * validFrom : Stores start date of subscription
 */


public class  Subscriptions extends Agreement implements SubscriptionManager{
//    /* Subscriptions attributes */
//    private int subsID;
//    private String subsName;
//    private String subsDesc;
//    private int subsPrice;
//    private int subsDurationMonths;
//    private Calendar validFrom;
//    private boolean isActive;

//    Constructors
    public  Subscriptions(int subsID, String subsName, int subsPrice, int subsDurationMonths, Calendar validFrom, boolean isActive, String subsDesc){
        super(subsID, subsName, subsDesc, subsPrice, subsDurationMonths, validFrom, isActive);
    }



    public String getPaymentMethod(){
        // Implement the logic to retrieve the payment method used for the subscription
        String[] paymentOptions = {"Credit Card", "PayPal", "Bank Transfer", "UPI"};

        if (isActive())
        {
            // Simulate choosing a random payment option from the list
            int randomIndex = (int) (Math.random() * paymentOptions.length);
            return paymentOptions[randomIndex];
        }
        else
        {
            return "No payment method available. Subscription is not active.";
        }
    }

    public void extendSubscription(int months){
        if (isActive()) {
            System.out.println("Extending subscription...");

            // Implement the logic for extending the subscription
            Calendar newValidFrom = (Calendar) getValidFrom().clone();
            newValidFrom.add(Calendar.MONTH, months);

            setValidFrom(newValidFrom);

            System.out.println("Subscription extended by " + months + " months. New valid from: " + (newValidFrom.getTime()));
        } else {
            System.out.println("Subscription is not active and cannot be extended.");
        }
    }

    public void subsDisplay(){
        System.out.println("Subscription ID: " + getAgreementID());
        System.out.println("Name: " + getAgreementName());
        System.out.println("Description: " + getAgreementDescription());
        System.out.println("Price: " + getAgreementPrice());
        System.out.println("Duration (months): " + getAgreementDurationMonths());
    }

    public void cancel_subscription(Calendar endDate) {
        if (isActive()) {
            System.out.println("Cancelling subscription...");
            Calendar currentDate = Calendar.getInstance();
            Calendar subscriptionEndDate = endDate;
            if (currentDate.compareTo(subscriptionEndDate) > 0) {
                setActive(false);
                System.out.println("Subscription has been cancelled and is now inactive.");
            } else {
                System.out.println("Subscription cannot be cancelled as it's still active.");
            }
        } else {
            System.out.println("Subscription is not active and cannot be cancelled.");
        }
    }

    public void cancel_subscription() {
        if (isActive()) {
            System.out.println("Cancelling subscription...");
            setActive(false);
            System.out.println("Subscription cancelled.");
        } else {
            System.out.println("Subscription is not active.");
        }
    }

    public boolean isPaymentOverdue(Calendar endDate) {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();
        // Check if the current date is after the end date of the subscription
        if (currentDate.compareTo(endDate) > 0) {
            // Payment is overdue
            return true;
        } else {
            // Payment is not overdue
            return false;
        }
    }
}
