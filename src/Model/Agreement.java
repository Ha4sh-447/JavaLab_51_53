package Model;
import java.util.Calendar;
abstract class Agreement {
    private int agreementID;
    private String agreementName;
    private String agreementDescription;
    private int agreementPrice;
    private int agreementDurationMonths;
    private Calendar validFrom;
    private boolean active;
    public Agreement(int agreementID, String agreementName, String
            agreementDescription, int agreementPrice, int agreementDurationMonths,
                     Calendar validFrom, boolean active) {
        this.agreementID = agreementID;
        this.agreementName = agreementName;
        this.agreementDescription = agreementDescription;
        this.agreementPrice = agreementPrice;
        this.agreementDurationMonths = agreementDurationMonths;
        this.validFrom = validFrom;
        this.active = active;
    }
    public int getAgreementID() {
        return agreementID;
    }
    public void setAgreementID(int agreementID) {
        this.agreementID = agreementID;
    }
    public String getAgreementName() {
        return agreementName;
    }
    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }
    public String getAgreementDescription() {
        return agreementDescription;
    }
    public void setAgreementDescription(String agreementDescription) {
        this.agreementDescription = agreementDescription;
    }
    public int getAgreementPrice() {
        return agreementPrice;
    }
    public void setAgreementPrice(int agreementPrice) {
        this.agreementPrice = agreementPrice;
    }
    public int getAgreementDurationMonths() {
        return agreementDurationMonths;
    }
    public void setAgreementDurationMonths(int
                                                   agreementDurationMonths) {
        this.agreementDurationMonths = agreementDurationMonths;
    }
    public Calendar getValidFrom() {
        return validFrom;
    }
    public void setValidFrom(Calendar validFrom) {
        this.validFrom = validFrom;
    }
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void displayAgreementInfo() {
        System.out.println("Agreement ID: " + getAgreementID());
        System.out.println("Name: " + getAgreementName());
        System.out.println("Description: " +
                getAgreementDescription());
        System.out.println("Price: " + getAgreementPrice());
        System.out.println("Duration (months): " +
                getAgreementDurationMonths());
        System.out.println("Valid From: " + getValidFrom().getTime());
        System.out.println("Active: " + getActive());
    }
    public abstract void cancel_subscription(Calendar endDate);
    public abstract boolean isPaymentOverdue(Calendar endDate);
}
