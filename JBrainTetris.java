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
    private JButton enableBrain;
    private boolean isBrainEnabled = false;
    private int count;
    private Move bestMove;

    public JBrainTetris(int width, int height)
    {
        super(width, height);
    }

    @Override public Container createControlPanel()
    {
        Container panel = new Container();
        panel = super.createControlPanel();
        ClickListener listener = new ClickListener();

        JComboBox cb = new JComboBox();
        ArrayList<Brain> brainList = BrainFactory.createBrains();
        Brain[] brains = new Brain[brainList.size()];
        for(int i = 0; i < brains.length; i++)
        {
            brains[i] = brainList.get(i);
        }
        cb = new JComboBox(brains);
        brain = new SimpleBrain();
        cb.addActionListener(new ComboBox());
        panel.add(cb);

        this.enableBrain = new JButton("Enable Brain");
        panel.add(enableBrain);
        this.enableBrain.addActionListener(listener);

        return panel;
    }

    public class ComboBox implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JComboBox cb = (JComboBox)e.getSource();
            brain = (Brain)cb.getSelectedItem();
        }
    }

    public class ClickListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == enableBrain)
            {
                count++;
                if(count % 2 != 0)
                {
                    isBrainEnabled = true;
                    enableBrain.setText("Disable Brain");
                }
                else
                {
                    isBrainEnabled = false;
                    enableBrain.setText("Enable Brain");
                }
            }
        }
    }

    @Override public Piece pickNextPiece()
    {
        int pieceNum = (int)(this.pieces.length * this.random.nextDouble());
        int limitHeight = HEIGHT + TOP_SPACE;
        System.out.println(brain);
        this.bestMove = brain.bestMove(this.board, this.pieces[pieceNum], limitHeight);
        return this.pieces[pieceNum];
    }

    @Override public void tick(int verb)
    {
        if(isBrainEnabled)
        {
            if(this.currentX < bestMove.getX())
            {
                tick(RIGHT);
            }
            else if(this.currentX > bestMove.getX())
            {
                tick(LEFT);
            }
            if(!this.currentPiece.equals(bestMove))
            {
                tick(ROTATE);
            }
            tick(DOWN);
        }
        else
        {
            super.tick(verb);
        }
    }
}
