import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class List extends JPanel implements ActionListener

{
    JButton Random ;
    JButton Save;
    JButton Erase;
    JButton Load;
    JButton Run;
    {
    Random =new JButton ("Random");
    Save = new JButton("Save");
    Erase = new JButton("Clear");
    Load = new JButton("Load");
    Run = new JButton("Run");
    }
    
    transient SpinnerModel model = new SpinnerNumberModel(1, 1, 50, 1);     
    public JSpinner spinner = new JSpinner(model);
    private JLabel label = new JLabel("Distance");
    private  Dimension btn_dim = new Dimension(100, 50);
    private  Dimension spi_dim = new Dimension(50, 50);

    List()
    {
        setPreferredSize(new Dimension(10, 60));
        Random.setPreferredSize(btn_dim);
        Save.setPreferredSize(btn_dim);
        Erase.setPreferredSize(btn_dim);
        Load.setPreferredSize(btn_dim);
        Run.setPreferredSize(btn_dim);
        spinner.setPreferredSize(spi_dim);
        label.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 5));
        add(Random);
        add(Save);
        add(Erase);
        add(Load);
        add(Run);
        add(label);
        add(spinner,BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(0, 50, 25, 50));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // do nothing   
    }
}
