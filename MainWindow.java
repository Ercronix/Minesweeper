
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public final class MainWindow implements  ActionListener, MouseListener{

    private JFrame window;
    private JButton button;
    private JButton button1;

    public MainWindow() {
        initialize();
    }
    
    public void initialize() {
        this.window = new JFrame();
        this.window.setTitle("Minesweeper");
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setSize(800, 800);
        this.window.setLocationRelativeTo(null);
        this.window.setLayout(new FlowLayout());

        
        button = new JButton();
        window.add(button);
        button.addActionListener(this);
        button.setActionCommand("Hallo");
        button.addMouseListener(this);

        button1 = new JButton();
        window.add(button1);
        button1.addActionListener(this);
        button1.setActionCommand("Hallo1");
        button1.addMouseListener(this);
    }

    public void show() {
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("jklsdhf");
        if("Hallo".equals(e.getActionCommand())){
            System.out.println("hallo");
        }
        if("Hallo1".equals(e.getActionCommand())){
            System.out.println("hallo1");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==2){System.out.println("middle maustaste");}
        if(e.getButton()==1){System.out.println("linke maustaste");}
        if(e.getButton()==3){System.out.println("rechte maustaste");}
        if(e.getSource()==button){System.out.println("button");}
        if(e.getSource()==button1){System.out.println("button1");}
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
