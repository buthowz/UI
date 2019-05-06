import java.awt.Color;
import java.awt.Graphics;
/**
 * Draws an oval.
 * 
 * @author Sujit Neupane
 * @version 05/31/2017
 */
public class FractalPiece
{
    private int x;
    private int y;
    private int diameter;
    private Color circleColor;
    
    /**
     * Contructor for this class
     * @param   x x coordinate of the circle to be drawn
     * @param   y y coordinate of the cirlce to be drawn
     * @param   diameter Diameter of the circle to be drawn
     * @param   circleColor Color of the oval to be drawn
     */
    public FractalPiece(int x, int y, int diameter, Color circleColor) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.circleColor = circleColor;
    }
    
    /**
     * Draws a oval
     * @param   g Graphics object on which the oval is to be drawn
     */
    public void draw(Graphics g) {
        g.setColor(circleColor);
        g.fillOval(x, y, diameter, diameter);
    }
    
    /**
     * Gets x
     *
     * @return The X coordinate of the circle to be drawn
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets y
     *
     * @return The Y coordinate of the circle to be drawn
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets diameter of the circle to be drawn
     *
     * @return The X coordinate of the circle to be drawn
     */
    public int getDiameter() {
        return diameter;
    }
    
    /**
     * Gets Color of the oval to be drawn
     *
     * @return The color of the oval
     */
    public Color getCircleColor() {
        return circleColor;
    }    
}
