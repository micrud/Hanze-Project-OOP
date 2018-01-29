package main;

import controller.Controller;
import model.Model;
import view.View;

/**
 * Main class to boot the whole application
 */
public class Main
{
    /**
     * Main method to boot the whole application
     */
    public Main()
    {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        view.setController(controller);
        view.startView();

        controller.manualTick(0);
        //////
        // TO-DO: If time allows it it's probably better to refactor to:
        //////
        // model = new Model();
        // controller = new Controller(model);
        // view = new View(controller);
        //
        // controller.setView(view)
        //////
        // this way we can initiate all views from the Views constructor.
        //////
    }
}
