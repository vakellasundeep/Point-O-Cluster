
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        BaseGUI appWindow = new BaseGUI();
        appWindow.init(); 
        appWindow.setSize(800, 650);
        appWindow.setTitle("Point O Cluster Game");
        appWindow.setVisible(true);
        appWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}