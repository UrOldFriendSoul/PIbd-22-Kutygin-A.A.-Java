import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class FormPlane extends JFrame {
    private final JPanel panelPlane;
    private ITransport plane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FormPlane frame = new FormPlane();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FormPlane() {
        super("Самолет");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setResizable(false);

        panelPlane = new JPanel();
        panelPlane.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(panelPlane);
        panelPlane.setLayout(null);

        JButton btnCreatePlane = new JButton("Создать Самолет");
        btnCreatePlane.setBounds(719, 11, 150, 23);
        btnCreatePlane.setMargin(new Insets(10, 10, 10, 10));
        panelPlane.add(btnCreatePlane);

        JButton btnCreateRadarPlane = new JButton("Создать Самолет с Радаром");
        btnCreateRadarPlane.setBounds(719, 54, 150, 23);
        btnCreateRadarPlane.setMargin(new Insets(10, 10, 10, 10));
        panelPlane.add(btnCreateRadarPlane);

        JButton btnUp = new JButton("");
        btnUp.setBounds(804, 379, 30, 30);
        btnUp.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\arrowUp.jpg"));
        panelPlane.add(btnUp);

        JButton btnLeft = new JButton("");
        btnLeft.setBounds(764, 420, 30, 30);
        btnLeft.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\arrowLeft.png"));
        panelPlane.add(btnLeft);

        JButton btnDown = new JButton("");
        btnDown.setBounds(804, 420, 30, 30);
        btnDown.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\загружено.png"));
        panelPlane.add(btnDown);

        JButton btnRight = new JButton("");
        btnRight.setBounds(844, 420, 30, 30);
        btnRight.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\arrowRight.png"));
        panelPlane.add(btnRight);

        btnCreatePlane.addActionListener(e -> {
            Random rnd = new Random();
            plane = new Plane(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.WHITE);
            plane.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, panelPlane.getWidth(), panelPlane.getHeight());
            repaint();
        });

        btnCreateRadarPlane.addActionListener(e -> {
            Random rnd = new Random();
            int radarAmount = rnd.nextInt(3)+1;
            plane = new RadarPlane(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.WHITE, Color.BLACK, true, true, new Radar(radarAmount));
            plane.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, panelPlane.getWidth(), panelPlane.getHeight());
            repaint();
        });

        btnUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plane != null) {
                    plane.MoveTransport(Directions.UP);
                    repaint();
                }
            }
        });

        btnLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plane != null) {
                    plane.MoveTransport(Directions.LEFT);
                    repaint();
                }
            }
        });

        btnRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plane != null) {
                    plane.MoveTransport(Directions.RIGHT);
                    repaint();
                }
            }
        });

        btnDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plane != null) {
                    plane.MoveTransport(Directions.DOWN);
                    repaint();
                }
            }
        });
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(plane != null)
            plane.DrawTransport(g);
    }

    //Переопределение метода repaint
    @Override
    public void repaint()
    {
        super.repaint();
    }

    public void SetPlane(ITransport plane)
    {
        Random rnd = new Random();
        this.plane = plane;
        this.plane.SetPosition(rnd.nextInt(100)+100, rnd.nextInt(100)+100, panelPlane.getWidth(),panelPlane.getHeight());
        repaint();
    }
}
