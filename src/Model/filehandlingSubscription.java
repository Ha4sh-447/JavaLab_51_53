package Model;

import java.util.ArrayList;

abstract public class filehandlingSubscription {
    abstract public ArrayList<Subscriptions> readJsonSubs(String json);

    //    Write JSON
    abstract public void displayJSONSubs(ArrayList<Subscriptions> users);
}
