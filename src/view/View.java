package view;

import controller.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class View extends AbstractView
{
    private CarParkView carParkView;
    private ButtonView buttonView;
//    ArrayList<CarParkView> views = new ArrayList<>();

    private Controller controller;

    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton minute = new JButton("+1 Minute");
    private JButton hour = new JButton("+1 Hour");
    private JButton day = new JButton("+1 Day");

    public View()
    {
        // This constructor is purposely left empty.
    }

    public void startView()
    {
//        int floors = controller.getNumberOfFloors();
//
//        int i = floors; //floors is het aantal verdiepingen
//        while (i > 0)
//        {
//            views.add(new CarParkView(controller));
//            i--;
//        }
        carParkView = new CarParkView(controller);
        buttonView = new ButtonView(controller);

        Container contentPane = getContentPane();

        buttonView.add(start);
        buttonView.add(stop);
        buttonView.add(minute);
        buttonView.add(hour);
        buttonView.add(day);

        contentPane.add(buttonView, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.WEST);
        pack();

        start.addActionListener(e -> controller.start());
        stop.addActionListener(e -> controller.stop());
        minute.addActionListener(e -> controller.manualTick(1));
        hour.addActionListener(e -> controller.manualTick(60));
        day.addActionListener(e -> controller.manualTick(1440));

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