package view;

import javax.swing.*;
import controller.*;

public abstract class AbstractView extends JFrame
{
    protected Controller controller;

    public AbstractView()
    {

    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void updateView() {
        repaint();
    }
}
