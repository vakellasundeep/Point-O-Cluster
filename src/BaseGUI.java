import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BaseGUI extends JFrame implements ActionListener, ChangeListener {

    transient Thread game;
    private GameGrid gameGrid = new GameGrid();
    private List List = new List();
    
    public void init() {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2) - 400;
        int y = (int) ((dimension.getHeight() - getHeight()) / 2) - 400;
        setLocation(x, y);

        List.Random.addActionListener(this);
        List.Save.addActionListener(this);
        List.Erase.addActionListener(this);
        List.Load.addActionListener(this);
        List.spinner.addChangeListener(this);

        this.setLayout(new BorderLayout());
        this.add(gameGrid, BorderLayout.CENTER);
        this.add(List, BorderLayout.SOUTH);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(List.Erase)){
            gameGrid.eraseGrid();
            gameGrid.repaint();
        }else if(e.getSource().equals(List.Run)) {
            runSim();
        }else if(e.getSource().equals(List.Save)) {
            try {
				gameGrid.saveDot();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getSource().equals(List.Load)) {
        	System.out.println("start load");
            gameGrid.loadDot();
			System.out.println("load worked");
        }else if(e.getSource().equals(List.Random)) {
            gameGrid.setrandom();
        }
    }



	private void runSim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}