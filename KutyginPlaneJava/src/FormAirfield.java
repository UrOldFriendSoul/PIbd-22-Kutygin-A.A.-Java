import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;
import java.awt.EventQueue;
public class FormAirfield extends JFrame {

    private final Airfield<Plane, IRadars> airfield;
    private Plane plane;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            FormAirfield formAirfield = new FormAirfield();
            formAirfield.setVisible(true);
        } );
    }

    public FormAirfield() {
        super("Аэродром");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel airfieldPanel = new JPanel();
        setBounds(100, 100, 900, 500);

        airfieldPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        airfieldPanel.setBounds(0, 0, 900, 500);

        setContentPane(airfieldPanel);

        airfieldPanel.setLayout(null);

        JPanel panelAirfield = new JPanel();
        panelAirfield.setBackground(Color.WHITE);
        panelAirfield.setBounds(5, 5, 690, 450);
        panelAirfield.setLayout(null);
        airfieldPanel.add(panelAirfield);

        JPanel panelManage = new JPanel();
        panelManage.setBackground(Color.GRAY);
        panelManage.setBounds(690, 5, 190, 450);
        panelManage.setLayout(null);
        airfieldPanel.add(panelManage);

        JButton btnParkPlane = new JButton("Добавить самолет");
        btnParkPlane.setBounds(25, 5, 150, 70);
        btnParkPlane.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkPlane);

        JButton btnParkRadarPlane = new JButton("Доб. самолет с рад");
        btnParkRadarPlane.setBounds(25, 80, 150, 70);
        btnParkRadarPlane.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkRadarPlane);

        JPanel panelTakeAway = new JPanel();
        panelTakeAway.setBorder(BorderFactory.createTitledBorder("Забрать самолет"));
        panelTakeAway.setBounds(25, 155, 150, 100);
        panelTakeAway.setLayout(null);
        panelManage.add(panelTakeAway);

        JLabel labelTakeAway = new JLabel("Место:");
        labelTakeAway.setBounds(25, 40, 50, 20);
        panelTakeAway.add(labelTakeAway);

        JTextField textFieldTakeAway = new JTextField();
        textFieldTakeAway.setBounds(80, 40, 45, 20);
        textFieldTakeAway.setColumns(5);
        panelTakeAway.add(textFieldTakeAway);

        JButton buttonTakeAway = new JButton("Забрать");
        buttonTakeAway.setMargin(new Insets(10, 10, 10, 10));
        buttonTakeAway.setBounds(15, 65, 120, 25);
        panelTakeAway.add(buttonTakeAway);

        airfield = new Airfield<>(panelAirfield.getWidth(), panelAirfield.getHeight());

        btnParkPlane.addActionListener(e -> {
            Color chooseColor = JColorChooser.showDialog(null, "Выберите цвет", Color.BLACK);
            plane = new Plane(100,1000,chooseColor);
            airfield.Plus(plane);

            repaint();
        });
        btnParkRadarPlane.addActionListener(e -> {
            Random rnd = new Random();
            Color chooseColor = JColorChooser.showDialog(null, "Выберите цвет", Color.BLACK);
            Color chooseDopColor = JColorChooser.showDialog(null, "Выберите цвет", Color.BLACK);
            plane = new RadarPlane(100, 1000, chooseColor, chooseDopColor, true, true, rnd.nextInt(3) + 1);
            airfield.Plus(plane);
            repaint();
        });

        buttonTakeAway.addActionListener((e) -> {
            int placeNumber = 0;
            placeNumber = Integer.parseInt(textFieldTakeAway.getText());
            plane = airfield.Minus(placeNumber);
            if (textFieldTakeAway.getText() != null) {
                    if (plane != null) {
                        FormPlane formPlane = new FormPlane();
                        formPlane.SetPlane(plane);
                        formPlane.setVisible(true);
                    }
                    else
                        {
                        JOptionPane.showMessageDialog(null, "Это место пусто!");
                    }
                }
            else
                {
                    textFieldTakeAway.setText("");
                    JOptionPane.showMessageDialog(null, "Введите число!");
                }
                repaint();
        });
    }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            airfield.Draw(g);
        }

}
