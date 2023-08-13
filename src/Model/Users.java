package Model;

import java.util.Calendar;
import java.util.Date;

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
    private Calendar dob;
    private long mobile_no;
    private String email;
    private String password;
    private Calendar regDate;
    private String accStatus;

    private String profilePic;

//    Constructors
    public Users(int userId, String userName, Calendar dob, long mobile_no, String email, String password, Calendar regDate, String accStatus, String profilePic){
        setUserId(userId);
        setUserName(userName);
        setDOB(dob);
        setMobileNo(mobile_no);
        setEmail(email);
        setPassword(password);
        setRegDate(regDate);
        setAccStatus(accStatus);
        setProfilePic(profilePic);
    }
    public Users(int userId, String userName, Calendar dob, long mobile_no, String email, String password, Calendar regDate, String accStatus){
        setUserId(userId);
        setUserName(userName);
        setDOB(dob);
        setMobileNo(mobile_no);
        setEmail(email);
        setPassword(password);
        setRegDate(regDate);
        setAccStatus(accStatus);
        setProfilePic("Smiley");
    }


//    Setters
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setDOB(Calendar dob)
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
    public void setRegDate(Calendar regDate)
    {
        this.regDate = regDate;
    }
    public void setAccStatus(String accStatus)
    {
        this.accStatus = accStatus;
    }
    public  void setProfilePic(String profilePic){this.profilePic = profilePic;}


    // Getters
    public int getUserId()
    {
        return this.userId;
    }
    public String getUserName()
    {
        return this.userName;
    }

    public Calendar getDOB()
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
    public Calendar getRegDate()
    {
        return this.regDate;
    }

    public String getAccStatus()
    {
        return this.accStatus;
    }
    public String getProfilePic(){return this.profilePic;}


}
