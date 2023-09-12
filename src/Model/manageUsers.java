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
public class manageUsers {
//    Variables
    ArrayList<Users> users = new ArrayList<Users>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
    Calendar userdob = Calendar.getInstance();
    Calendar userRegDate = Calendar.getInstance();

//    Reads json file to the users ArrayList
    public ArrayList<Users> readjson(String filepath){
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

}
