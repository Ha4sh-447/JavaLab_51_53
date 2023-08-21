package Model;

import java.util.Calendar;

public class Manager {
    private String userName;
    private Calendar dob;
    private long mobile_no;
    private String email;
    private Calendar regDate;

    public Manager(String userName, Calendar dob, long mobile_no, String email, Calendar regDate){
        setUserName(userName);
        setDOB(dob);
        setMobileNo(mobile_no);
        setEmail(email);
        setRegDate(regDate);
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
    public void setRegDate(Calendar regDate)
    {
        this.regDate = regDate;
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
    public Calendar getRegDate()
    {
        return this.regDate;
    }

}
