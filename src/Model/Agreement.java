package Model;
import java.util.Calendar;
abstract class Agreement {
    private int agreementID;
    private String agreementName;
    private String agreementDescription;
    private int agreementPrice;
    private int agreementDurationMonths;
    private Calendar validFrom;
    private boolean isActive;
    public Agreement(int agreementID, String agreementName, String
            agreementDescription, int agreementPrice, int agreementDurationMonths,
                     Calendar validFrom, boolean isActive) {
        this.agreementID = agreementID;
        this.agreementName = agreementName;
        this.agreementDescription = agreementDescription;
        this.agreementPrice = agreementPrice;
        this.agreementDurationMonths = agreementDurationMonths;
        this.validFrom = validFrom;
        this.isActive = isActive;
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
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
        System.out.println("Active: " + isActive());
    }
    public abstract void cancel_subscription(Calendar endDate);
    public abstract boolean isPaymentOverdue(Calendar endDate);
}
