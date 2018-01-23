package controller;

import model.*;
import view.*;

import java.awt.*;

public class Controller extends AbstractController implements Runnable
{
    private Boolean run;

    private int tickPause = 100;

    public Controller(Model model, View view)
    {
        super(model, view);
    }

    // Why am I broken?!?
    public void manualTick(int ticks)
    {
        for(int i = 0; i < ticks; i++)
        {
            model.tick();
            updateView();
            model.tock();
        }
    }

    public Color getCarColor(int floor, int row, int place)
    {
        Location location = new Location(floor, row, place);
        Car car = model.getCarAt(location);
        return car == null ? Color.white : car.getColor();
    }

    private void updateView()
    {
        int floors = model.getNumberOfFloors();
        int rows = model.getNumberOfRows();
        int places = model.getNumberOfPlaces();

        view.updateView(floors, rows, places);
    }


    // Runnable

    public void start()
    {
        new Thread(this).start();
    }

    public void stop()
    {
        run = false;
    }

    @Override
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