package view;

import controller.Controller;

import javax.swing.*;

public abstract class AbstractView extends JPanel
{
    protected Controller controller;

    public AbstractView()
    {

    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void updateView()
    {
        repaint();
    }
}
