package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class CarParkView extends JPanel
{
    private Controller controller;

    private Dimension size;
    private Image carParkImage;

    private int testX = 20;
    private int testY = 10;
    private int testCanvasX = 0;

    private int[] rowSizeX;
    private int[] rowSizeY;

    /**
     * Constructor for objects of class CarPark
     */
    public CarParkView(Controller controller)
    {
        this.controller = controller;
        size = new Dimension(0, 0);
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 450);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g)
    {
        if (carParkImage == null)
        {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize))
        {
            g.drawImage(carParkImage, 0, 0, null);
        } else
        {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    public void calculateGraphics(int floors, int rows, int places)
    {
        size = getSize();

        // Set the size of the border around separate floors
        int border = 20;
        int spacer = (border / 2);


        int canvasY = size.height - (border * 2);
        int rowHeight = canvasY / places;

        int totalCanvasX = size.width - ((border * 2) + (border * (floors - 1)));
        int canvasX = totalCanvasX / floors;

        int moduloRows = (rows % 2 == 1) ? rows + 1 : rows;

        int rowWidth = (canvasX - ((moduloRows / 2) - 1) * spacer) / rows;

        testX = rowWidth;
        testY = rowHeight;

        testCanvasX = canvasX + border;

        int counter = 0;

        int rowX = border - rowWidth;
        int rowY = border - rowHeight;

        rowSizeX = new int[rows];
        rowSizeY = new int[places];

        for (int r = 0; r < rows; r++)
        {
            if (counter > 1)
            {
                rowX = rowX + (spacer);
                counter = 0;

            }
            rowX = rowX + rowWidth;
            rowSizeX[r] = rowX;
            counter++;
        }

        for (int p = 0; p < places; p++)
        {
            rowY = rowY + rowHeight;
            rowSizeY[p] = rowY;
        }
    }

    public void updateView(int floors, int rows, int places)
    {
        int x = 0;

        size = getSize();
        carParkImage = createImage(size.width, size.height);
        
        if ((rowSizeX == null || rowSizeY == null) || (!(rowSizeX.length == rows) || !(rowSizeY.length == places))) {calculateGraphics(floors, rows, places);}

        // Create a new car park image if the size has changed.
        if (!size.equals(getSize()))
        {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
            calculateGraphics(floors, rows, places);
        }

        Graphics graphics = carParkImage.getGraphics();


        for (int floor = 1; floor <= floors; floor++)
        {
            for (int rowTT = 0; rowTT < rows; rowTT++)
            {
                for (int placeTT = 0; placeTT < places; placeTT++)
                {
                    Color color = controller.getCarColor(x, rowTT, placeTT);
                    drawPlace(graphics, rowSizeX[rowTT], rowSizeY[placeTT], color, floor);

                }
            }
            x++;
        }

        repaint();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlaceBackup(Graphics graphics, int floor, int row, int place, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(
                floor * 260 + (1 + (int) Math.floor(row * 0.5)) * 75 + (row % 2) * 20,
                60 + place * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }

    private void drawPlace(Graphics graphics, int x, int y, Color color, int floor)
    {
        graphics.setColor(color);
        graphics.fillRect(
                //(floor == 1) ? x : (floor == 2) ? x + testCanvasX : x + (testCanvasX * 2),
                (floor == 1) ? x : x + (testCanvasX * (floor - 1)),
                y,
                testX - 2,
                testY - 2);
    }
}