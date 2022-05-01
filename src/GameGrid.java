import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class GameGrid extends JPanel implements Runnable, MouseListener, ComponentListener{

    public static final Color black = new Color(0,0,0);
    private boolean gameSimStatus = false;
    private Dimension gridSize = null;
    public ArrayList<Point> point = new ArrayList<>(0);
    public static final int CELL_SIZE = 20;
    private HashMap<Point,ArrayList<Point>> map = new HashMap<>();
    
    
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
                 }
        } catch (ConcurrentModificationException cme){
            //do nothing
        }

        gr.setColor(Color.white);
        if(gridSize!=null) {
            for (int i = 0; i <= gridSize.width; i++) {
                gr.drawLine(((i * CELL_SIZE) + CELL_SIZE), CELL_SIZE, (i * CELL_SIZE) + CELL_SIZE, CELL_SIZE + (CELL_SIZE * gridSize.height));
            }
            for (int i = 0; i <= gridSize.height; i++) {
                gr.drawLine(CELL_SIZE, ((i * CELL_SIZE) + CELL_SIZE), CELL_SIZE * (gridSize.width + 1), ((i * CELL_SIZE) + CELL_SIZE));
            }
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

    }

    public void run(Object distance){
        for(Point outP : point) {
            for(Point inP: point){
                if (euclideanDistance(outP, inP) <= Integer.parseInt(distance.toString())){
                    Graphics gr = getGraphics();
                    try {
                            gr.setColor(black);
                        }
                     catch (ConcurrentModificationException cme){
                        //do nothing
                    }
                    gr.drawLine(outP.x, outP.y, inP.x, inP.y);
                }
            }
        }
    }

	public void setrandom() {
		for(int i=1;i<=20;++i) {
			
			
		int x = ThreadLocalRandom.current().nextInt(0, 30 + 1);
		int y = ThreadLocalRandom.current().nextInt(0, 30 + 1);
		if (!point.contains(new Point(x,y))) {
            point.add(new Point(x,y));
        }
      
        repaint();
		}
	}
	


	public void loadDot() throws IOException {
        FileReader fr=new FileReader("output.txt");

        // read character wise from string and write
        // into FileWriter
        int ch;
        ArrayList<Character> arrayList = new ArrayList<Character>();

        while ((ch=fr.read())!=-1)
            arrayList.add((char)(ch));

        String str = new String("");
        str = "";
        for(int i = 0; i<arrayList.size(); i++){
            if(arrayList.get(i) != '\n'){
                str += (arrayList.get(i));
            }
            else{
                String[] tokens = str.toString().split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);

                if (!point.contains(new Point(x,y))) {
                    point.add(new Point(x,y));
                }

                str = "";

            }
        }

        repaint();
        // close the file
        fr.close();
    }


	public void saveDot() throws IOException {
        FileWriter fw=new FileWriter("output.txt");

        // read character wise from string and write
        // into FileWriter
        for(Point eachPoint: point)
            fw.write(String.valueOf(eachPoint.x) + ',' +String.valueOf(eachPoint.y) + "\n");

        fw.close();
		
		
	}
	
	public double euclideanDistance(Point p1, Point p2) {
        double ycoord = Math.abs (p1.y - p2.y);
        double xcoord = Math.abs (p1.x - p2.x);
        return Math.sqrt((ycoord)*(ycoord) +(xcoord)*(xcoord));
	}
	
	
	
	
	
	
}