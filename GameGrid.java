import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.*;

public class GameGrid extends JPanel implements Runnable, MouseListener, ComponentListener{

    private static final Color black = new Color(0,0,0);
    private boolean gameSimStatus = false;
    private Dimension gridSize = null;
    private ArrayList<Point> point = new ArrayList<>(0);
    private static final int CELL_SIZE = 20;
    
    public GameGrid(){
        addComponentListener(this);
        addMouseListener(this);
        setBackground(Color.white);
    }

    


    public void drawDot(int x, int y){
        if (!point.contains(new Point(x,y))) {
            point.add(new Point(x,y));
        }
      
        repaint();
    }

    public void drawDot(MouseEvent e){
        int x = e.getPoint().x/CELL_SIZE-1;
        int y = e.getPoint().y/CELL_SIZE-1;
        if ((x >= 0) && (x < gridSize.width) && (y >= 0) && (y < gridSize.height)) {
            drawDot(x,y);
        }
    }

   

    public void eraseGrid(){
        point.clear();
    }

    public void updateGridSize(){
        ArrayList<Point> removeList = new ArrayList<>(0);
        for (Point current : point) {
            if ((current.x > gridSize.width-1) || (current.y > gridSize.height-1)) {
                removeList.add(current);
            }
        }
        point.removeAll(removeList);
        repaint();
    }

    @Override
    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        try {
            for (Point newPoint : point){
                gr.setColor(black);
                gr.fillOval(CELL_SIZE + (CELL_SIZE*newPoint.x), CELL_SIZE + (CELL_SIZE*newPoint.y), CELL_SIZE, CELL_SIZE);
                System.out.println(newPoint.x);
                 }
        } catch (ConcurrentModificationException cme){
            //do nothing
        }

        gr.setColor(Color.white);
        for (int i=0; i<=gridSize.width; i++){
            gr.drawLine(((i*CELL_SIZE)+CELL_SIZE), CELL_SIZE, (i*CELL_SIZE)+CELL_SIZE, CELL_SIZE + (CELL_SIZE*gridSize.height));
        }
        for (int i=0; i<=gridSize.height; i++){
            gr.drawLine(CELL_SIZE, ((i*CELL_SIZE)+CELL_SIZE), CELL_SIZE*(gridSize.width+1), ((i*CELL_SIZE)+CELL_SIZE));
        }
    }

    @Override
    public void componentResized(ComponentEvent e){
        gridSize = new Dimension(getWidth()/CELL_SIZE-2, (getHeight())/CELL_SIZE-2);
        updateGridSize();
    }

    @Override
    public void componentMoved(ComponentEvent e){
        // do nothing
    }

    @Override
    public void componentShown(ComponentEvent e){
        // do nothing
    }

    @Override
    public void componentHidden(ComponentEvent e){
        // do nothing
    }

    @Override
    public void mouseClicked(MouseEvent e){
        // do nothing
    }

    @Override
    public void mousePressed(MouseEvent e){
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if(!gameSimStatus){
            drawDot(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e){
        // do nothing
    }

    @Override
    public void run(){
        boolean[][] gameGrid = new boolean[gridSize.width+2][gridSize.height+2];
        for(Point current : point) {
            gameGrid[current.x+1][current.y+1] = true;
        }
        ArrayList<Point> dots = new ArrayList<>(0);

       
  
        
        eraseGrid();
        point.addAll(dots);
        repaint();
       
    }

	public void setrandom() {
		
		if (!point.contains(new Point(14,20))) {
            point.add(new Point(14,20));
        }
      
        repaint();
		
	}




	public void loadDot() {
		// TODO Auto-generated method stub
		
	}


	public void saveDot() {
		// TODO Auto-generated method stub
		
	}
}
