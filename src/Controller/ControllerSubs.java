package Controller;

import Model.Model;
import View.View;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControllerSubs {
    Model model;
    View view;

    public ControllerSubs(Model m, View v) {
        model = m;
        view = v;
        view.centerInitialSetup(model.getManageSubsData().getLinesBeingDisplayed(), model.getManageSubsData().getHeaders().size());
        view.centerUpdate(model.getManageSubsData().getLines(model.getManageSubsData().getFirstLineToDisplay(), model.getManageSubsData().getLastLineToDisplay()), model.getManageSubsData().getHeaders());
        addScrolling();
    }

    private void addScrolling()
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
