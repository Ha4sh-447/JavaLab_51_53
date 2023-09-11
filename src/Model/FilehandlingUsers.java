package Model;

import java.util.ArrayList;

abstract class FilehandlingUsers {

    abstract public ArrayList<Users> readJson(String json);

//    Write JSON
    abstract public void displayJSON(ArrayList<Users> users);
}
