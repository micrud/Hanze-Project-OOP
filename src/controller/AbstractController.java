package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.util.*;

public abstract class AbstractController extends JPanel
{
    protected Model model;
    protected View view;

    public AbstractController(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }
}
