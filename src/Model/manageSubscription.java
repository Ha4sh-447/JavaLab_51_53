package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class manageSubscription {

    ArrayList<Subscriptions> subs = new ArrayList<Subscriptions>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Calendar validfrom = Calendar.getInstance();

    //    Reads json file to the users ArrayList
    public ArrayList<Subscriptions> readJsonSubs(String filepath){
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode jsonread = mapper.readTree(new File(filepath));

            if(jsonread.isArray()){
                for(JsonNode node: jsonread){
                    int subsId = node.get("subsId").asInt();
                    String subsName = node.get("subsName").asText();
                    int subsPrice = node.get("subsPrice").asInt();
                    int subsDurationMonths = node.get("subsDurationMonths").asInt();
                    Date validFrom = sdf.parse(node.get("validFrom").asText());
                    boolean isActive = node.get("isActive").asBoolean();
                    String subsDesc = node.get("subsDesc").asText();

                    validfrom.setTime(validFrom);

// int subsID, String subsName, int subsPrice, int subsDurationMonths, Calendar validFrom, boolean isActive, String subsDesc
                    Subscriptions subsObj = new Subscriptions(subsId, subsName, subsPrice, subsDurationMonths, validfrom, isActive, subsDesc);
                    this.subs.add(subsObj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return subs;
    }
    public void displayJSONSubs(ArrayList<Subscriptions> subs){
        ObjectMapper mapper = new ObjectMapper();

        try {
            for (int i = 0; i < subs.size(); i++) {
                String subsStr = mapper.writeValueAsString(subs.get(i));
                System.out.println(subsStr);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
