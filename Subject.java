import java.awt.Color;
/**
 * Subject interface
 * 
 * @author Sujit Neupane 
 * @version 05/29/2017
 */
public interface Subject
{
    public void registerObserver(Observer display);
    public void removeObserver(Observer display);
    public void notifyObservers();
    public void setData(int depthOfFractals, int ratio, Color cactusColor, Color pearColor);
}
