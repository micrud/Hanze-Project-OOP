package controller;

import model.Car;
import model.Location;
import model.Model;
import view.View;

import java.awt.*;

public class Controller extends AbstractController implements Runnable
{
    // run (Boolean) is used by Runnable, while true the application runs
    private Boolean run;
    // tickPause (int) is used by updateView(), it dictates the pause between ticks
    // or in other words it dictates the speed at which the simulation is run.
    // JSlider manipulates the value to do just that.
    private int tickPause = 100;

    /**
     * Constructor for the Controller
     *
     * @param model Needed for the AbstractController
     * @param view Needed for the AbstractController
     */
    public Controller(Model model, View view)
    {
        super(model, view);
    }

    /**
     * Method is used to calculate multiple ticks after each other and will
     * draw the latest state once all calculations are made.
     * This method is also used to initiate a empty parking lot by entering zero (0) ticks
     *
     * @param ticks The amount of ticks the simulator needs to preform
     */
    public void manualTick(int ticks)
    {
        for(int i = 0; i < ticks; i++)
        {
            model.tick();
            model.tickCar();
            model.tock();
        }
        // Method updateView is purposely placed outside of the loop
        updateView();
    }

    //////
    // Getters
    //////

    /**
     * Method to get the colour from a specific car
     *
     * @param floor The floor on which the car is placed
     * @param row   The row on which the car is placed
     * @param place The place on which the car is placed
     * @return The colour of the specified car
     */
    public Color getCarColor(int floor, int row, int place)
    {
        Location location = new Location(floor, row, place);
        Car car = model.getCarAt(location);
        return car == null ? Color.white : car.getColor();
    }

    //////
    // Setters
    //////

    /**
     * Method to set the tickPause
     * @param ticks The new tick value
     */
    public void setTickPause(int ticks)
    {
        tickPause = ticks;
    }

    /**
     * Method is used to tell the view it needs to update
     */
    private void updateView()
    {
        int floors = model.getNumberOfFloors();
        int rows = model.getNumberOfRows();
        int places = model.getNumberOfPlaces();

        view.updateView(floors, rows, places);
    }

    //////
    // Runnable
    //////

    /**
     * Method is used to start the simulation. Start a new thread which will trigger run()
     */
    public void start()
    {
        new Thread(this).start();
    }

    /**
     * Method is used to pause the simulation.
     */
    public void stop()
    {
        run = false;
    }

    @Override
    /**
     * Runs the simulation.
     */
    public void run()
    {
        run = true;
        while (run)
        {
            model.tick();
            model.tickCar();
            updateView();

            try
            {
                Thread.sleep(tickPause);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            model.tock();
        }
    }
}