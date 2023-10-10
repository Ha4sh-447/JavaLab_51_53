import Controller.*;
import Model.*;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import View.View;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {

    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view);
    }
}


