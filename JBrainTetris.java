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
        Container panel = new Container();
        panel = super.createControlPanel();
        JComboBox cb = new JComboBox();
        ArrayList<Brain> brainList = BrainFactory.createBrains();
        Brain[] brains = new Brain[brainList.size()];
        for(int i = 0; i < brains.length; i++)
        {
            brains[i] = brainList.get(i);
        }
        cb = new JComboBox(brains);

        return panel;
    }

    public class ComboBox implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JComboBox cb = (JComboBox)e.getSource();
            String brainName = (String)cb.getSelectedItem();
        }
    }
}
