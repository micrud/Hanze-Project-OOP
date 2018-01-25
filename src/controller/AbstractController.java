package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.util.*;

public abstract class AbstractController extends JPanel
{
    protected Model model;
    protected View view;

    /**
     * Constructor for AbstractController
     * @param model The model with all logic
     * @param view The view to display
     */
    public AbstractController(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }
}
