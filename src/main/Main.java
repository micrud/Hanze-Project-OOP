package main;

import model.*;
import view.*;
import controller.*;

import javax.swing.*;

public class Main
{
    private Model model;
    private View view;
    private Controller controller;

//    private JFrame screen;

    public Main()
    {
        model = new Model();
        view = new View();
        controller = new Controller(model, view);

        view.setController(controller);
        view.startView();

//        screen = new JFrame("Hello world!");
//        screen.setSize(450, 285);
//        screen.setResizable(true);
//        screen.setLayout(null);
//
//        screen.getContentPane().add(view);
//
//        screen.pack();
//
//        screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        screen.setVisible(true);
//
        // controller.start();
    }
}
