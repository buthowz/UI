
/**
 * Main Class for this package.
 * 
 * @author Sujit Neupane 
 * @version  06/04/2017
 */
public class Main
{
    public static void main(String[] args) {
        Fractal myFractal = new Fractal();		
		Display myDisplay = new Display(myFractal);
		UserInterface myUser = new UserInterface(myFractal);
		myDisplay.setVisible(true);
		myUser.setVisible(true);
    }
    
}
