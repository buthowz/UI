import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Graphics;
/**
 * Displays fractal using Fractal Data.
 * 
 * @author  Sujit Neupane 
 * @version 05/26/2017
 */
public class Display extends JFrame implements Observer
{
    private Toolkit toolkit;
	private int depthOfFractals;
	private int parentChildRatio;
    private Color cactusColor;
    private Color pearColor;
    private Fractal myFractal;
    private FractalPiece myFractalPiece;
    private GPanel fractalPanel;
    /**
     * Constructor for objects of class Display
	 * @param myFractal Fractal Object
     */
    public Display(Fractal myFractal)
    {
        this.myFractal = myFractal;
		myFractal.registerObserver(this);//this is registered as Observer here
		
        setTitle("Prickly Pear Fractal");
        setSize(1000, 1000);
        toolkit = getToolkit();
        setLocation((toolkit.getScreenSize().width - getWidth())/2, (toolkit.getScreenSize().height - getHeight())/2);//middle of screen
        fractalPanel = new GPanel();
        getContentPane().add(fractalPanel); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Updates and repaints this object
     *
     * @param depthOfFractals Depth of fractals to be drawn
     * @param ratio Parent and child ratio
     * @param cactusColor Cactus color
     * @param pearColor Pear color
     */
    public void update(int depthOfFractals, int ratio, Color cactusColor, Color pearColor) {
        //gets data here after the update is published to Observers
        this.depthOfFractals = depthOfFractals;
		this.parentChildRatio = ratio;
		this.cactusColor = cactusColor;
		this.pearColor = pearColor;
		
        repaint();
    }

    /**
     * GPanel class extends JPanel
     */
    private class GPanel extends JPanel{
		@Override
		/**
		 * Paints fractals 
		 * @param g Graphics object where the painting is being done
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int index = 0; index < myFractal.getFractalDataList().size(); index++) {
				int x = myFractal.getFractalDataList().get(index).getX();
				int y = myFractal.getFractalDataList().get(index).getY();
				int radius = myFractal.getFractalDataList().get(index).getDiameter();
				Color color = myFractal.getFractalDataList().get(index).getCircleColor();
				myFractalPiece = new FractalPiece(x, y, radius, color);
				myFractalPiece.draw(g);
			}
		}
    }
}

