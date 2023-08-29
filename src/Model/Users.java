package Model;

import java.util.Calendar;
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

public class Users extends Manager  implements DetailsManagement{

    /* User attributes */
    private int userId;
    private String password;
    private String accStatus;
    private String profilePic;

//    Constructors
    public Users(int userId, String userName, Calendar dob, long mobile_no, String email, String password, Calendar regDate, String accStatus, String profilePic){
        super(userName,  dob,  mobile_no, email, regDate);
        setUserId(userId);
        setPassword(password);
        setAccStatus(accStatus);
        setProfilePic(profilePic);
    }
    public Users(int userId, String userName, Calendar dob, long mobile_no, String email, String password, Calendar regDate, String accStatus){
        super( userName,  dob,  mobile_no,  email,  regDate);
        setUserId(userId);
        setPassword(password);
        setAccStatus(accStatus);
        setProfilePic("Smiley");
    }


//    Setters
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public void setPassword(String password)
    {
        this.password = password;
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
    public String getPassword()
    {
        return this.password;
    }
    public String getAccStatus()
    {
        return this.accStatus;
    }
    public String getProfilePic(){return this.profilePic;}

    public void displayInfo(){
        System.out.println("User Profile Picture: "+getProfilePic());
        System.out.println("User ID: " + getUserId());
        System.out.println("Username: "+getUserName());
        System.out.println("Date of Birth: "+ formatDate(getDOB()));
        System.out.println("Mobile number: "+getMobileNo());
        System.out.println("Email address: "+ getEmail());
        System.out.println("Account status: "+ getAccStatus());
        System.out.println("Registration Date: "+ formatDate(getRegDate()));
    }

    public void changePassword(String newPass){
            setPassword(newPass);
            System.out.println("Password changed successfully!");
    }

    public String formatDate(Calendar obj){
        String[] st = obj.getTime().toString().split(" ");
        String newdate = st[2].concat(" ").concat(st[1]).concat(" ").concat(st[5]);
        return newdate;
    }

    @Override
    public int calculateDays(Calendar endDate) {
        Calendar currDate = Calendar.getInstance();

        long currTime = currDate.getTimeInMillis();
        long endTime = endDate.getTimeInMillis();

        long diffInMillis = Math.abs(endTime - currTime);
        int secondsInADay = 60 * 60 * 24;  // Number of seconds in a day

        int days = (int) (diffInMillis / (1000 * secondsInADay));
        return days+1;
    }

    public void changeProfilePic(String pic){
        setProfilePic(pic);
        System.out.println("Profile Picture successfully updated.");
    }
}
