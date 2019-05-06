import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.text.NumberFormat;
import javax.swing.JColorChooser;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Dimension;
/**
 * Interacts with User and collects data to draw Fractals.
 * 
 * @author Sujit Neupane 
 * @version 05/29/2017
 */
public class UserInterface extends JFrame
{
    private Toolkit toolkit;
    private int depthOfFractals;
    private int parentChildRatio;
    private Color cactusColor;
    private Color pearColor;
    private Fractal myFractal; 
    private static final int MIN_RECURSION = 2;
    private static final int MAX_RECURSION = 10;
    private static final int MIN_RATIO = 40;
    private static final int MAX_RATIO = 70;
    /**
     * Constructor for objects of class UserInterface
     * @param   myFractal   Fractal object
     */
    public UserInterface(Fractal myFractal)
    {
        this.myFractal = myFractal;
        
        this.cactusColor = Color.GREEN;//default cactus color
        this.pearColor = Color.RED;//default pear color

        NumberFormat depthFormat = NumberFormat.getIntegerInstance();//Integer number only for user
        NumberFormat ratioFormat = NumberFormat.getIntegerInstance();//Integer number only for user
        //depthFormat.setMaximumIntegerDigits(2);
        //ratioFormat.setMaximumIntegerDigits(2);
        toolkit = getToolkit();
        this.setTitle("Setting Dialogue");
        setSize(300, 250);
        setLocation((toolkit.getScreenSize().width - getWidth())/2, (toolkit.getScreenSize().height - getHeight())/2);
        setResizable(false);
        
        JPanel sDPanel = new JPanel();
        getContentPane().add(sDPanel);
                        
        JLabel fractalDepthLabel = new JLabel("Fractal depth");
        JFormattedTextField depthOfFractalJTF = new JFormattedTextField(depthFormat);
        depthOfFractalJTF.setValue(new Integer(MIN_RECURSION));//default depth of fractal
                
        JLabel ratioLabel = new JLabel("Parent child ratio");
        JFormattedTextField parentChildRatioJTF = new JFormattedTextField(ratioFormat); 
        parentChildRatioJTF.setValue(new Integer(MIN_RATIO)); //default parent child ratio
        
        JPanel labelPanel = new JPanel(new GridLayout(3, 2));
        labelPanel.add(fractalDepthLabel);
        labelPanel.add(depthOfFractalJTF);
        labelPanel.add(ratioLabel);
        labelPanel.add(parentChildRatioJTF);
       
        JButton cactusColorButton = new JButton("Choose cactus color");
        JPanel cactusColorSample = new JPanel();
        cactusColorSample.setBackground(Color.GREEN);
        cactusColorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JColorChooser clr = new JColorChooser();
                    Color color = clr.showDialog(cactusColorSample, "Choose Color",
                            Color.green);
                    cactusColorSample.setBackground(color);
                    cactusColor = color;
                }
            });
                    
        JButton pearColorButton = new JButton("Choose pear color");
        JPanel pearColorSample = new JPanel();      
        pearColorSample.setBackground(Color.RED);
        pearColorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JColorChooser clr = new JColorChooser();
                    Color color = clr.showDialog(pearColorSample, "Choose Color",
                                                Color.red);
                    pearColorSample.setBackground(color);
                    pearColor = color;
                }
            });
        
        JButton createFractalButton = new JButton("Create Fractal");
        createFractalButton.setBackground(Color.yellow);
        createFractalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {    
                depthOfFractals = ((Number) (depthOfFractalJTF.getValue())).intValue();
                if (depthOfFractals < MIN_RECURSION) {
                    depthOfFractals = MIN_RECURSION;
                }
                if (depthOfFractals > MAX_RECURSION) {
                    depthOfFractals = MAX_RECURSION;
                }
                parentChildRatio = ((Number) (parentChildRatioJTF.getValue())).intValue();
                if (parentChildRatio < MIN_RATIO) {
                    parentChildRatio = MIN_RATIO;
                }
                if (parentChildRatio > MAX_RATIO) {
                    parentChildRatio = MAX_RATIO;
                }
                myFractal.setData(depthOfFractals, parentChildRatio, cactusColor, pearColor);
            }
        });
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 4));
        buttonPanel.add(cactusColorButton);     
        buttonPanel.add(cactusColorSample);
        buttonPanel.add(pearColorButton);
        buttonPanel.add(pearColorSample);
        
        sDPanel.add(labelPanel, BorderLayout.NORTH);
        sDPanel.add(buttonPanel, BorderLayout.CENTER);
        sDPanel.add(createFractalButton);
        //this.pack();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }  
}