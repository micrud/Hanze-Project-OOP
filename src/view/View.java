package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame
{
    private Controller controller;

    private CarParkView carParkView;

    public View()
    {
        // This constructor is purposely left empty.
    }

    public void startView()
    {
        // Initiate the necessary Views
        // Contains the simulator
        carParkView = new CarParkView(controller);

        // Contains all buttons
        ButtonView buttonView = new ButtonView(controller);

        // Contains the slider
        SliderView sliderView = new SliderView(controller);
        // TO-DO: finalize a layout

        // Create a Container
        Container contentPane = getContentPane();
        // Add all Views
        contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(buttonView, BorderLayout.SOUTH);
        contentPane.add(sliderView, BorderLayout.EAST);
        // Finalize
        pack();

        // Make everything visible and draw the parking lot
        setVisible(true);
        updateView();
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void updateView()
    {
        repaint();
    }

    public void updateView(int floors, int rows, int places)
    {
        carParkView.updateView(floors, rows, places);
    }

}