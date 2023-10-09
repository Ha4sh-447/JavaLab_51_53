package Model;

public class Model
{
    protected manageUsers manage_users = new manageUsers();
    protected manageSubscription manage_subs = new manageSubscription();

    public Model()
    {
        manage_users.setLinesBeingDisplayed(25);
        manage_users.setFirstLineToDisplay(0);
        manage_subs.setLinesBeingDisplayed(25);
        manage_subs.setFirstLineToDisplay(0);
    }

    public manageUsers getManageUserData()
    {
        return manage_users;
    }
    public manageSubscription getManageSubsData()
    {
        return manage_subs;
    }

}