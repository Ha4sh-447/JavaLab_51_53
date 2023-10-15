package Model;
import java.util.Calendar;

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
 * agreementPrice : Stores price of subscription
 * subsDurationMonths : Duration of subscription
 * getActive : Stores getActive status of user
 * validFrom : Stores start date of subscription
 */


public class  Subscriptions extends Agreement implements SubscriptionManager{
//    /* Subscriptions attributes */
//    private int subsID;
//    private String subsName;
//    private String subsDesc;
//    private int agreementPrice;
//    private int subsDurationMonths;
//    private Calendar validFrom;
//    private boolean getActive;

    private String valid_from;

    public String getValid_from() {
        return valid_from;
    }

    public void setValid_from(String valid_from) {
        this.valid_from = valid_from;
    }

    //    Constructors
    public  Subscriptions(String valid_from,int subsID, String subsName, int agreementPrice, int subsDurationMonths, Calendar validFrom, boolean active, String subsDesc){
        super(subsID, subsName, subsDesc, agreementPrice, subsDurationMonths, validFrom, active);
        setValid_from(valid_from);
    }



    public String getPaymentMethod(){
        // Implement the logic to retrieve the payment method used for the subscription
        String[] paymentOptions = {"Credit Card", "PayPal", "Bank Transfer", "UPI"};

        if (getActive())
        {
            // Simulate choosing a random payment option from the list
            int randomIndex = (int) (Math.random() * paymentOptions.length);
            return paymentOptions[randomIndex];
        }
        else
        {
            return "No payment method available. Subscription is not getActive.";
        }
    }

    public void extendSubscription(int months){
        if (getActive()) {
            System.out.println("Extending subscription...");

            // Implement the logic for extending the subscription
            Calendar newValidFrom = (Calendar) getValidFrom().clone();
            newValidFrom.add(Calendar.MONTH, months);

            setValidFrom(newValidFrom);

            System.out.println("Subscription extended by " + months + " months. New valid from: " + (newValidFrom.getTime()));
        } else {
            System.out.println("Subscription is not getActive and cannot be extended.");
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
        if (getActive()) {
            System.out.println("Cancelling subscription...");
            Calendar currentDate = Calendar.getInstance();
            Calendar subscriptionEndDate = endDate;
            if (currentDate.compareTo(subscriptionEndDate) > 0) {
                setActive(false);
                System.out.println("Subscription has been cancelled and is now inactive.");
            } else {
                System.out.println("Subscription cannot be cancelled as it's still getActive.");
            }
        } else {
            System.out.println("Subscription is not getActive and cannot be cancelled.");
        }
    }

    public void cancel_subscription() {
        if (getActive()) {
            System.out.println("Cancelling subscription...");
            setActive(false);
            System.out.println("Subscription cancelled.");
        } else {
            System.out.println("Subscription is not getActive.");
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
