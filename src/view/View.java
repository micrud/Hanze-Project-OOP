package view;

import controller.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View extends AbstractView
{
    private CarParkView carParkView;

    private Controller controller;

    // Create all buttons
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton minute = new JButton("+1 Minute");
    private JButton hour = new JButton("+1 Hour");
    private JButton day = new JButton("+1 Day");

    // Create the JSlider
    private JSlider slider = new JSlider(JSlider.VERTICAL, 11, 1001, 100);

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


        // All buttons need to be added to the buttonView
        buttonView.add(start);
        buttonView.add(stop);
        buttonView.add(minute);
        buttonView.add(hour);
        buttonView.add(day);

        // Testing the JSlider implementation
        sliderView.add(slider);

        // Setting up the JSlider
        // Hashtable is needed te add labels to the JSlider
        Hashtable labelTable = new Hashtable();
        labelTable.put(1, new JLabel("Fast"));
        labelTable.put(1000, new JLabel("Slow"));

        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);

        slider.addChangeListener(event -> {
            int value = slider.getValue();
            controller.setTickPause(value);
        });


        // Create a Container
        Container contentPane = getContentPane();
        // Add all Views
        contentPane.add(carParkView, BorderLayout.WEST);
        contentPane.add(buttonView, BorderLayout.SOUTH);
        contentPane.add(sliderView, BorderLayout.EAST);
        // Finalize
        pack();

        // ActionListeners; all lambdas reference the Controller
        start.addActionListener(e -> controller.start());
        stop.addActionListener(e -> controller.stop());
        minute.addActionListener(e -> controller.manualTick(1));
        hour.addActionListener(e -> controller.manualTick(60));
        day.addActionListener(e -> controller.manualTick(1440));

        // Make everything visible and draw the parking lot
        setVisible(true);
        updateView();
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void updateView(int floors, int rows, int places)
    {
//        for(CarParkView v: views)
//        {
//            v.updateView(floors, rows, places);
//        }
        carParkView.updateView(floors, rows, places);
    }

}