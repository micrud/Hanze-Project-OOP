package view;

import controller.Controller;

import java.awt.*;

import static java.lang.Math.round;

public class CarParkView extends AbstractView
{
    private Controller controller;

    private Dimension size;
    private Image carParkImage;

    private int parkingPlaceWidth = 20;
    private int parkingPlaceHeight = 10;
    private int borderedCanvasPerFloorWidth = 0;

    private int[] anchorX;
    private int[] anchorY;

    /**
     * Constructor for objects of class CarParkView
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

    /**
     * Calculates the width and the height of each parking spot that has to be displayed
     * and saves them in a Array.
     *
     * @param floors The amount of floors to be displayed
     * @param rows   The amount of rows on each floor
     * @param places The amount of places in a row
     */
    public void calculateGraphics(int floors, int rows, int places)
    {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize()))
        {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
            calculateGraphics(floors, rows, places);
        }

        size = getSize();

        // Set the size of the border around the entire parking lot
        int border = 20;
        int spacer = (border / 2);

        // Calculate the width of a single parking spot
        int canvasWidth = size.width - ((border * 2) + (border * (floors - 1)));
        int canvasPerFloorWidth = canvasWidth / floors;
        int moduloRows = (rows % 2 == 1) ? rows + 1 : rows;
        int rowWidth = (int) round((canvasPerFloorWidth - ((moduloRows / 2) - 1) * spacer) / (rows * 1.0));

        // Calculate the height of a single parking spot
        int canvasHeight = size.height - (border * 2);
        int rowHeight = (int) round(canvasHeight / (places * 1.0));

        parkingPlaceWidth = rowWidth;
        parkingPlaceHeight = rowHeight;

        borderedCanvasPerFloorWidth = canvasPerFloorWidth + border;

        anchorX = new int[rows];
        anchorY = new int[places];

        int counter = 0;
        int rowX = border - rowWidth;
        int rowY = border - rowHeight;

        for (int row = 0; row < rows; row++)
        {
            if (counter > 1)
            {
                rowX = rowX + (spacer);
                counter = 0;

            }
            rowX = rowX + rowWidth;
            anchorX[row] = rowX;
            counter++;
        }

        for (int place = 0; place < places; place++)
        {
            rowY = rowY + rowHeight;
            anchorY[place] = rowY;
        }
    }

    /**
     * Updates CarParkView re-rendering the entire parkinglot
     *
     * @param floors The amount of floors to be displayed
     * @param rows   The amount of rows on each floor
     * @param places The amount of places in a row
     */
    public void updateView(int floors, int rows, int places)
    {
        if ((anchorX == null || anchorY == null) || (!(anchorX.length == rows) || !(anchorY.length == places)))
        {
            calculateGraphics(floors, rows, places);
        }

        Graphics graphics = carParkImage.getGraphics();

        for (int floor = 0; floor < floors; floor++)
        {
            for (int row = 0; row < rows; row++)
            {
                for (int place = 0; place < places; place++)
                {
                    Color color = controller.getCarColor(floor, row, place);
                    drawPlace(graphics, anchorX[row], anchorY[place], color, floor);

                }
            }
        }

        repaint();
    }

    /**
     * Draw a parking place in the specified colour on x- and y-axis
     *
     * @param graphics The background to draw on
     * @param x        The x-axis of the top-left corner of a specific parking place
     * @param y        The y-axis of the top-left corner of a specific parking place
     * @param colour   The colour the parking place needs to be drawn in
     * @param floor    The floor on which the parking place should be drawn
     */
    private void drawPlace(Graphics graphics, int x, int y, Color colour, int floor)
    {
        graphics.setColor(colour);
        graphics.fillRect(
                (floor == 0) ? x : x + (borderedCanvasPerFloorWidth * floor),
                y,
                parkingPlaceWidth - 2,
                parkingPlaceHeight - 2);
    }
}