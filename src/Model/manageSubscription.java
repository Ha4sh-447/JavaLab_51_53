package Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class manageSubscription extends filehandlingSubscription implements Displayable{

    ArrayList<Subscriptions> subs = new ArrayList<Subscriptions>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar validfrom = Calendar.getInstance();
    private int linesBeingDisplayed;
    private int firstLineIndex;
    int lastLineIndex;
    int highlightedLine;
    ArrayList<String> subs_valid_from = new ArrayList<>();
    String subsPath = "src/Model/subscription.json";

    public manageSubscription() {
        this.subs = readJsonSubs(subsPath);
    }

    //    Reads json file to the users ArrayList
    public ArrayList<Subscriptions> readJsonSubs(String filepath){
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode jsonread = mapper.readTree(new File(filepath));

            if(jsonread.isArray()){

                for (JsonNode node : jsonread) {
                    int subsId = node.get("agreementID").asInt();
                    String subsName = node.get("agreementName").asText();
                    int agreementPrice = node.get("agreementPrice").asInt();
                    int subsDurationMonths = node.get("agreementDurationMonths").asInt();
//                    Date validFrom = sdf.parse(node.get("validFrom").asText());

                    Calendar validfrom = Calendar.getInstance();
                    validfrom.setTimeInMillis(node.get("valid_from").asLong());

                    String valid_from = node.get("valid_from").asText();
//                    System.out.println(validFrom);
//                    Calendar validDate = Calendar.getInstance();
//                    validDate.setTimeInMillis(node.get("validFrom").asLong());
                    String subsDesc = node.get("agreementDescription").asText();
                    boolean getActive = node.get("active").asBoolean();
//                    boolean active = true;

//                    validfrom.setTime(validFrom);

//                    String valid_from = sdf.format(validDate.getTime());
//                    subs_valid_from.add(valid_from);
//                    System.out.println(valid_from);
//                    System.out.println(validDate.getTime());

// int subsID, String subsName, int agreementPrice, int subsDurationMonths, Calendar validFrom, boolean getActive, String subsDesc
//                    Subscriptions subsObj = new Subscriptions(valid_from,subsId, subsName, agreementPrice, subsDurationMonths, validDate, getActive, subsDesc);
                    Subscriptions subsObj = new Subscriptions(valid_from,subsId, subsName, agreementPrice, subsDurationMonths, validfrom, getActive, subsDesc);
                    this.subs.add(subsObj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return subs;
    }

    public void writeFromForm(String name, int price, String valid_from, boolean active, int months, Calendar validFrom, String desc) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        int id = subs.size()+1;
        System.out.println(id);
        Subscriptions subsObj = new Subscriptions(valid_from,id, name, price, months, validFrom, active, desc);
        subs.add(subsObj);
        mapper.writeValue(Paths.get(subsPath).toFile(), subs);
    }

    public ArrayList<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<String>();
        headers.add("ID");
        headers.add("Name");
        headers.add("Price");
        headers.add("Duration");
        headers.add("Start Date");
        headers.add("Active");
        headers.add("Description");
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
        subs_details.add(subs.get(line).getValid_from());
        subs_details.add(String.valueOf(subs.get(line).getActive()));
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

    public void delete(int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        subs.remove(id);
        mapper.writeValue(Paths.get("src/Model/subscription.json").toFile(), subs);
    }
}
