import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class BaseGUI extends JFrame implements ActionListener, ChangeListener {

    transient Thread game;
    private GameGrid gameGrid = new GameGrid();
    private List List = new List();

    int distance = -1;
    
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
        List.Run.addActionListener(this);

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
//            gameGrid.run(List.spinner.getValue());
            distance = (Integer) List.spinner.getValue();
            repaint();
        }else if(e.getSource().equals(List.Save)) {
            try {
				gameGrid.saveDot();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getSource().equals(List.Load)) {
        	System.out.println("start load");
            try {
                gameGrid.loadDot();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        if(distance!=-1){
            for(Point outP : gameGrid.point) {
                for(Point inP: gameGrid.point){
                    double dis = gameGrid.euclideanDistance(outP, inP);;
                    if (dis!=0 && dis <= distance){
//                        Graphics gr = getGraphics();
                        try {
                            gr.setColor(GameGrid.black);
//                            gr.fillOval(CELL_SIZE + (CELL_SIZE*newPoint.x), CELL_SIZE + (CELL_SIZE*newPoint.y), CELL_SIZE, CELL_SIZE);
                        }
                        catch (ConcurrentModificationException cme){
                            //do nothing
                        }

                        int CELL_SIZE = GameGrid.CELL_SIZE;
                        gr.drawLine(((outP.x*CELL_SIZE)+CELL_SIZE+10), ((outP.y*CELL_SIZE)), ((inP.x*CELL_SIZE)+CELL_SIZE+10), ((inP.y*CELL_SIZE)));
                    }
                }
            }
        }
    }
}