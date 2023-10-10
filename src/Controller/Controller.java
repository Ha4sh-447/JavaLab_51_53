package Controller;

import Model.Model;
import View.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller {
    Model model;
    View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        view.centerInitialSetup(model.getManageUserData().getLinesBeingDisplayed(), model.getManageUserData().getHeaders().size());
        view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
        addScrolling();
        addButtonClick();
    }

    public void centerSubs(Model m, View v){
//        view.centerInitialSetup(model.getManageSubsData().getLinesBeingDisplayed(), model.getManageSubsData().getHeaders().size());

        view.centerUpdate(model.getManageSubsData().getLines(model.getManageSubsData().getFirstLineToDisplay(), model.getManageSubsData().getLastLineToDisplay()), model.getManageSubsData().getHeaders());
        addScrollingSubs();
    }
    private void addButtonClick() {
        view.getMf().getIp().getBp().getBtn_users().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Users Pressed");

                view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
            }
        });

        view.getMf().getIp().getBp().getBtn_subs().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                System.out.println("Subscription Pressed");
                centerSubs(model, view);
            }
        });
    }

    private void addScrolling()
    {
        view.getMf().getIp().getCp().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int units = e.getUnitsToScroll();
                System.out.println(units);
                int current_first_line = model.getManageUserData().getFirstLineToDisplay();
                int current_last_line = model.getManageUserData().getLastLineToDisplay();
                int no_of_players = model.getManageUserData().getTable().size();
                int no_of_display_lines = model.getManageUserData().getLinesBeingDisplayed();
                if(units <= 0 && current_first_line == 0)
                {
                    model.getManageUserData().setFirstLineToDisplay(0);
                }
                else if(units <= 0 && current_first_line > 0)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line <= 0)
                    {
                        model.getManageUserData().setFirstLineToDisplay(0);
                    }
                    else
                    {
                        model.getManageUserData().setFirstLineToDisplay(new_first_line);
                    }
                }
                else if(units > 0 && current_last_line == no_of_players-1)
                {
                    model.getManageUserData().setFirstLineToDisplay(current_first_line);
                }
                else if (units > 0 && current_last_line < no_of_players-1)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line > no_of_players - no_of_display_lines)
                    {
                        new_first_line = no_of_players-no_of_display_lines;
                        model.getManageUserData().setFirstLineToDisplay(new_first_line);
                    }
                    else
                    {
                        model.getManageUserData().setFirstLineToDisplay(new_first_line);
                    }
                }

                view.centerUpdate(model.getManageUserData().getLines(model.getManageUserData().getFirstLineToDisplay(), model.getManageUserData().getLastLineToDisplay()), model.getManageUserData().getHeaders());
            }
        });
    }

    private void addScrollingSubs()
    {
        view.getMf().getIp().getCp().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int units = e.getUnitsToScroll();
                System.out.println(units);
                int current_first_line = model.getManageSubsData().getFirstLineToDisplay();
                int current_last_line = model.getManageSubsData().getLastLineToDisplay();
                int no_of_players = model.getManageSubsData().getTable().size();
                int no_of_display_lines = model.getManageSubsData().getLinesBeingDisplayed();
                if(units <= 0 && current_first_line == 0)
                {
                    model.getManageSubsData().setFirstLineToDisplay(0);
                }
                else if(units <= 0 && current_first_line > 0)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line <= 0)
                    {
                        model.getManageSubsData().setFirstLineToDisplay(0);
                    }
                    else
                    {
                        model.getManageSubsData().setFirstLineToDisplay(new_first_line);
                    }
                }
                else if(units > 0 && current_last_line == no_of_players-1)
                {
                    model.getManageSubsData().setFirstLineToDisplay(current_first_line);
                }
                else if (units > 0 && current_last_line < no_of_players-1)
                {
                    int new_first_line = current_first_line + units;
                    if(new_first_line > no_of_players - no_of_display_lines)
                    {
                        new_first_line = no_of_players-no_of_display_lines;
                        model.getManageSubsData().setFirstLineToDisplay(new_first_line);
                    }
                    else
                    {
                        model.getManageSubsData().setFirstLineToDisplay(new_first_line);
                    }
                }

                view.centerUpdate(model.getManageSubsData().getLines(model.getManageSubsData().getFirstLineToDisplay(), model.getManageSubsData().getLastLineToDisplay()), model.getManageSubsData().getHeaders());
            }
        });
    }
}
