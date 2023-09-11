import Controller.Controller;
import Model.*;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import View.View;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {

//    method to create date from user input
    public static Calendar createDate(){
        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        scanner.nextLine();

        // Create a Calendar object and set the input date
        Calendar start_date = Calendar.getInstance();
        start_date.set(Calendar.YEAR, year);
        // Calendar months are zero-based (0 - January, 1 - February, ...)
        start_date.set(Calendar.MONTH, month - 1);
        start_date.set(Calendar.DAY_OF_MONTH, day);

        return start_date;
    }


    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view);
    }
}


