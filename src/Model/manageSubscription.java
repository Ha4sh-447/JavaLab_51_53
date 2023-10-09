package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class manageSubscription extends filehandlingSubscription implements Displayable{

    ArrayList<Subscriptions> subs = new ArrayList<Subscriptions>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar validfrom = Calendar.getInstance();
    private int linesBeingDisplayed;
    private int firstLineIndex;
    int lastLineIndex;
    int highlightedLine;
    ArrayList<String> subs_valid_from = new ArrayList<>();

    public manageSubscription() {
        this.subs = readJsonSubs("src/Model/subscription.json");
    }

    //    Reads json file to the users ArrayList
    public ArrayList<Subscriptions> readJsonSubs(String filepath){
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode jsonread = mapper.readTree(new File(filepath));

            if(jsonread.isArray()){

                for (JsonNode node : jsonread) {
                    int subsId = node.get("subsId").asInt();
                    String subsName = node.get("subsName").asText();
                    int subsPrice = node.get("subsPrice").asInt();
                    int subsDurationMonths = node.get("subsDurationMonths").asInt();
                    Date validFrom = sdf.parse(node.get("validFrom").asText());
                    boolean isActive = node.get("isActive").asBoolean();
                    String subsDesc = node.get("subsDesc").asText();

                    validfrom.setTime(validFrom);

                    String valid_from = sdf.format(validfrom.getTime());
                    subs_valid_from.add(valid_from);

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

    public ArrayList<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<String>();
        headers.add("subsId");
        headers.add("subsName");
        headers.add("subsPrice");
        headers.add("subsDurationMonths");
        headers.add("validFrom");
        headers.add("isActive");
        headers.add("subsDesc");
        return headers;
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

    public ArrayList<String> getLine(int line) {
        ArrayList<String> subs_details = new ArrayList<String>();
        subs_details.add(String.valueOf(subs.get(line).getAgreementID()));
        subs_details.add(subs.get(line).getAgreementName());
        subs_details.add(String.valueOf(subs.get(line).getAgreementPrice()));
        subs_details.add(String.valueOf(subs.get(line).getAgreementDurationMonths()));
        subs_details.add(subs_valid_from.get(line));
        subs_details.add(String.valueOf(subs.get(line).isActive()));
        subs_details.add(subs.get(line).getAgreementDescription());
        return subs_details;
    }

    public ArrayList<ArrayList<String>> getLines(int firstLine, int lastLine) {
        ArrayList<ArrayList<String>> subs_subset = new ArrayList<ArrayList<String>>();

        for (int i = firstLine; i <= lastLine; i++) {
            subs_subset.add(getLine(i));
        }
        return subs_subset;
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
        return subs;
    }
}
