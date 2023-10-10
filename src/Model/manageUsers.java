package Model;
import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import com.fasterxml.jackson.databind.*;


/**
 * Identification comments:
 *   Name: Harsh Saindane
 *   Experiment No: 6
 *   Experiment Title:
 *   Experiment Date:
 *   @version 1.0
 *
 *
 * Beginning comments:
 * Filename: manageUsers.java
 * @author:  Harsh Saindane
 * /*  Overview: This class is created to store the attributes of Users which have different Subscription.
 *
 * Attribute comments:
 * users : An Arraylist which stores data of users
 * sdf : SimpleDateFormat object which converts string to desired date format
 * userdob : Calendar object to store dob of user
 * userRegDate : Calendar object to store registration date of user
 */

public class manageUsers extends FilehandlingUsers implements Displayable{
//    Variables
    ArrayList<Users> users = new ArrayList<Users>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar userdob = Calendar.getInstance();
    Calendar userRegDate = Calendar.getInstance();
    private int linesBeingDisplayed;
    private int firstLineIndex;
    int lastLineIndex;
    int highlightedLine;
    ArrayList<String> users_Date = new ArrayList<>();
    ArrayList<String> users_RegDate = new ArrayList<>();

    public manageUsers() {
        this.users = readJson("src/Model/users.json");
    }

    //    Reads json file to the users ArrayList
    public ArrayList<Users> readJson(String filepath){

        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode jsonread = mapper.readTree(new File(filepath));

            if(jsonread.isArray()){
                for (JsonNode node : jsonread) {

                    int id = node.get("userId").asInt();
                    String name = node.get("userName").asText();
                    long mobile_no = node.get("mobile_no").asLong();
                    String email = node.get("email").asText();
                    Date dob = sdf.parse(node.get("dob").asText());
                    String password = node.get("password").asText();
                    Date regDate = sdf.parse(node.get("regDate").asText());
                    String accStatus = node.get("accStatus").asText();
                    String profilePic = node.get("ProfilePic").asText();

                    userdob.setTime(dob);
                    userRegDate.setTime(regDate);
//                  Get the date and time as a String in the desired format
                    String dobString = sdf.format(userdob.getTime());
                    String regDateString = sdf.format(userRegDate.getTime());
                    users_Date.add(dobString);
                    users_RegDate.add(regDateString);

                    // Create new Calendar instances for dob and regDate
                    Calendar userdob = Calendar.getInstance();
                    Calendar userRegDate = Calendar.getInstance();
                    userdob.setTime(dob);
                    userRegDate.setTime(regDate);


                    Users usr = new Users(id, name, userdob, mobile_no, email, password, userRegDate, accStatus, profilePic);
                    users.add(usr);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }


    public ArrayList<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<String>();
        headers.add("userId");
        headers.add("userName");
        headers.add("email");
        headers.add("dob");
        headers.add("mobile_no");
        headers.add("regDate");
//        headers.add("password");
        headers.add("accStatus");
//        headers.add("ProfilePic");

        return headers;
    }


//    Displays JSON file
    public void displayJSON(ArrayList<Users> users){
        ObjectMapper mapper = new ObjectMapper();

        try {
            for (int i = 0; i < users.size(); i++) {
                String userStr = mapper.writeValueAsString(users.get(i));
                System.out.println(userStr);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<String> getLine(int line) {
        ArrayList<String> user_details = new ArrayList<String>();
        user_details.add(String.valueOf(users.get(line).getUserId()));
        user_details.add(users.get(line).getUserName());
        user_details.add(users.get(line).getEmail());
        user_details.add(users_Date.get(line));
        user_details.add(String.valueOf(users.get(line).getMobileNo()));
        user_details.add(users_RegDate.get(line));
        user_details.add(users.get(line).getAccStatus());
        user_details.add(users.get(line).getPassword());
        user_details.add(users.get(line).getProfilePic());

        return user_details;
    }

    public ArrayList<ArrayList<String>> getLines(int firstLine, int lastLine) {
        ArrayList<ArrayList<String>> users_subset = new ArrayList<ArrayList<String>>();

        for (int i = firstLine; i <= lastLine; i++) {
            users_subset.add(getLine(i));
        }
        return users_subset;
    }

    @Override
    public int getFirstLineToDisplay() {
        return firstLineIndex;
    }

    @Override
    public int getLineToHighlight() {
        return highlightedLine;
    }

    @Override
    public int getLastLineToDisplay() {
        setLastLineToDisplay(getFirstLineToDisplay() + getLinesBeingDisplayed() - 1);
        return lastLineIndex;
    }

    @Override
    public int getLinesBeingDisplayed() {
        return linesBeingDisplayed;
    }

    @Override
    public void setFirstLineToDisplay(int firstLine) {
        firstLineIndex = firstLine;
    }

    @Override
    public void setLineToHighlight(int highlightedLine) {
        highlightedLine = highlightedLine;
    }

    @Override
    public void setLastLineToDisplay(int lastLine) {
        lastLineIndex = lastLine;
    }

    @Override
    public void setLinesBeingDisplayed(int numberOfLines) {
        linesBeingDisplayed = numberOfLines;
    }

    public ArrayList getTable() {
        return users;
    }
        
}

