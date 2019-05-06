import java.util.ArrayList;
import java.awt.Color;
/**
 * Fractal drawing data 
 * @author Sujit Neupane 
 * @version 05/29/2017
 */
public class Fractal implements Subject {
    private ArrayList<Observer> observerList;
    private ArrayList<FractalPiece> fractalDataList;
    private int depthOfFractals;
    private int parentChildRatio;
    private Color cactusColor;
    private Color pearColor;
    private static final int INITIAL_X = 365;
    private static final int INITIAL_Y = 670;
    private static final int INITIAL_DIAMETER = 200;
    private static final double MAIN_ANGLE = 90;
    /**
     * Contructor for this class
     */
    public Fractal() {
        observerList = new ArrayList<Observer>();
        fractalDataList = new ArrayList<FractalPiece>();
    }

    /**
     * Sets data for this class
     *
     * @param depthOfFractals depth of fractals to be drawn
     * @param ratio Parent child ratio of the fractals
     * @param cactusColor Cactus color
     * @param pearColor Pear color
     */
    public void setData(int depthOfFractals, int ratio, Color cactusColor, Color pearColor) {
        this.depthOfFractals = depthOfFractals;
        this.parentChildRatio = ratio;
        this.cactusColor = cactusColor;
        this.pearColor = pearColor;
        fractalDataList.clear();
        notifyObservers();
        createFractalPieces();  
    }

    /**
     * Creates fractals pieces
     *
     */
    public void createFractalPieces() {        
        FractalPiece fractal = new FractalPiece(INITIAL_X, INITIAL_Y, INITIAL_DIAMETER, cactusColor);
        fractalDataList.add(fractal);
        createFractalPieces(INITIAL_X, INITIAL_Y, INITIAL_DIAMETER, cactusColor, MAIN_ANGLE, depthOfFractals);
    }    

    /**
     * Recursive private method to create fractals pieces
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param diameter Diameter of the circle to be drawn
     * @param circleColor Color of the oval
     * @param angle Angle to draw the circle at
     * @param depthOfFractals Depth of the fractals to be drawn
     */
    private void createFractalPieces(int x, int y, int diameter, Color circleColor, double angle, int depthOfFractals) {
        int childDiameter = (int) ((parentChildRatio/100.0) * diameter);
        int h = diameter/2 + childDiameter/2;//hypotenuse

        double leftAngle = angle - 45;
        double rightAngle = angle + 45;
        
        int leftX = x + diameter/2 - childDiameter/2  - (int)(h * (Math.cos(Math.toRadians(leftAngle))));
        int leftY = y  + diameter/2 - childDiameter/2 - (int)(h * (Math.sin(Math.toRadians(leftAngle))));
        
        int rightX = x + diameter/2 - childDiameter/2  -  (int)(h * (Math.cos(Math.toRadians(rightAngle))));
        int rightY = y + diameter/2 - childDiameter/2 - (int)(h * (Math.sin(Math.toRadians(rightAngle))));
        
        if (depthOfFractals > 1 && diameter > 0) {//depth of fractals goes upto 1 only as we already added first oval(main oval) above
            // and no diameter below zero
            if (depthOfFractals == 2) {//as depth of fractals is 2, it is the pear fractal
                circleColor = pearColor;//circle changes to pear color
            }
            
            FractalPiece fractalLeft = new FractalPiece(leftX, leftY, childDiameter, circleColor);
            fractalDataList.add(fractalLeft);

            FractalPiece fractalRight = new FractalPiece(rightX, rightY, childDiameter, circleColor);
            fractalDataList.add(fractalRight);
            
            createFractalPieces(leftX, leftY, childDiameter, circleColor, leftAngle, depthOfFractals - 1);
            createFractalPieces(rightX, rightY, childDiameter, circleColor, rightAngle, depthOfFractals - 1);
        } 
    }

//     /**
//      * Gets depth of fractals
//      *
//      * @return The depth of fractals
//      */
//     public int getDepthOfFractals() {
//         return depthOfFractals;
//     }
// 
//     /**
//      * Gets parent child ratio
//      *
//      * @return The parent child ratio
//      */
//     public double getParentChildRatio() {
//         return parentChildRatio;
//     }
// 
//     /**
//      * Gets cactus color
//      *
//      * @return The color of the cactus part
//      */
//     public Color getCactusColor() {
//         return cactusColor;
//     }
// 
//     /**
//      * Gets pear color
//      *
//      * @return The color of the pear part
//      */
//     public Color getPearColor() {
//         return pearColor;        
//     }
// 
     /**
     * Gets arraylist containing fractal data
     *
     * @return ArrayList containing fractal data
     */
    public ArrayList<FractalPiece> getFractalDataList() {
        return fractalDataList;
    }   

    /**
     * Registers observers
     *
     * @param newObserver An observer which needs to be registered
     */
    public void registerObserver(Observer newObserver) {
        observerList.add(newObserver);
    }

    /**
     * Removes an observer
     *
     * @param removeObserver An observer to be removed
     */
    public void removeObserver(Observer removeObserver) {
        observerList.remove(removeObserver);        
    }

    /**
     * Notifies observers
     *
     */
    public void notifyObservers() {
        for (int index = 0; index < observerList.size(); index++) {
            observerList.get(index).update(depthOfFractals, parentChildRatio, cactusColor, pearColor);
        }
    }
}