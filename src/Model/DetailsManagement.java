package Model;

import java.util.Calendar;

public interface DetailsManagement {
    void displayInfo();
    void changePassword( String newPass);
    String formatDate(Calendar obj);
}
