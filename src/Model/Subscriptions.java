package Model;
import java.util.Date;

/**
 * Identification comments:
 *   Name: Harsh Saindane
 *   Experiment No: 2
 *   Experiment Title:
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


public class  Subscriptions {
    /* Subscriptions attributes */
    private int subsID;
    private String subsName;
    private String subsDesc;
    private int subsPrice;
    private int subsDurationMonths;
    private Date validFrom;
    private boolean isActive;

//    Setters
    public void setSubsID(int subsID)
    {
        this.subsID = subsID;
    }
    public void setSubsName(String subsName)
    {
        this.subsName = subsName;
    }

    public void setSubsDesc(String subsDesc)
    {
        this.subsDesc = subsDesc;
    }

    public void setSubsPrice(int subsPrice)
    {
        this.subsPrice = subsPrice;
    }
    public void setSubsDuration(int subsDurationMonth)
    {
        this.subsDurationMonths = subsDurationMonth;
    }

    public void setValidFrom(Date validFrom){
        this.validFrom = validFrom;
    }
    public void setIsActive(boolean isActive){this.isActive = isActive;}


//    Getters
    public int getSubsID()
    {
        return this.subsID;
    }

    public String getSubsName()
    {
        return this.subsName;
    }

    public String getSubsDesc()
    {
        return this.subsDesc;
    }

    public int getSubsPrice()
    {
        return this.subsPrice;
    }
    public int getSubsDuration()
    {
        return this.subsDurationMonths;
    }

    public Date getValidFrom(){return this.validFrom;}
    public boolean getIsActive(){return this.isActive;}

}
