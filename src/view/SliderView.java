package view;

import controller.Controller;

import javax.swing.*;
import java.util.Hashtable;

public class SliderView extends JPanel
{
    private Controller controller;

    private JSlider slider;

    /**
     * Constructor for objects of class ButtonView
     */
    public SliderView(Controller controller)
    {
        this.controller = controller;

        // Testing the JSlider implementation
        slider = new JSlider(JSlider.VERTICAL, 11, 1001, 100);
        add(slider);

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

    }
}