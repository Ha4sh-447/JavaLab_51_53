package Model;

import java.util.Calendar;
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
 * Filename: Users.java
 * @author:  Harsh Saindane
 * /*  Overview: This class is created to store the attributes of Users which have different Subscription.
 *
 * Attribute comments: 
 * Userid : Its User id
 * UserName : It is the users name
 * dob : User's date of birth
 * mobile_no : Mobile number of user
 * email : email id of user
 * password : password of user
 * regDate : Date when user registered
 * accStatus : Status of user's account
 */

public class Users {

    /* User attributes */
    private int userId;
    private String userName;
    private Date dob;
    private long mobile_no;
    private String email;
    private String password;
    private Date regDate;
    private String accStatus;


//    Setters
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setDOB(Date dob)
    {
        this.dob = dob;
    }
    public void setMobileNo(long mobile_no)
    {
        this.mobile_no = mobile_no;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setRegDate(Date regDate)
    {
        this.regDate = regDate;
    }
    public void setAccStatus(String accStatus)
    {
        this.accStatus = accStatus;
    }


    // Getters
    public int getUserId()
    {
        return this.userId;
    }
    public String getUserName()
    {
        return this.userName;
    }

    public Date getDOB()
    {
        return this.dob;
    }

    public long getMobileNo()
    {
        return this.mobile_no;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public Date getRegDate()
    {
        return this.regDate;
    }

    public String getAccStatus()
    {
        return this.accStatus;
    }


}
