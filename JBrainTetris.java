import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * Write a description of class JBrainTetris here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBrainTetris extends JTetris
{
    private Brain brain;
    
    public JBrainTetris(int width, int height)
    {
        super(width, height);
    }
    
    @Override public Container createControlPanel()
    {
        return super.createControlPanel();
    }
}
