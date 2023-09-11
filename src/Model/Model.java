package Model;

public class Model
{
    protected manageUsers manage_users = new manageUsers();

    public Model()
    {
        manage_users.setLinesBeingDisplayed(25);
        manage_users.setFirstLineToDisplay(0);
    }

    public manageUsers getManageUserData()
    {
        return manage_users;
    }
}