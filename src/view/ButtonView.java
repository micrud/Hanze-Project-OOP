package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ButtonView extends JPanel
{
    private Controller controller;

    /**
     * Constructor for objects of class ButtonView
     */
    public ButtonView(Controller controller)
    {
        this.controller = controller;
        setLayout(new FlowLayout());
    }
}