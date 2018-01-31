package model;

import java.awt.*;
import java.util.Random;

public class Yellow extends Car
{
    private static final Color COLOR = Color.yellow;

    public Yellow()
    {
        Random random = new Random();
        int stayMinutes = Integer.MAX_VALUE;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor()
    {
        return COLOR;
    }
}