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
    private final JPanel contentPane;
    private RadarPlane radarPlane;
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
        //Вызов конструктора базового класса
        super("Самолет");
        //Закрытие на "крестик"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Установка положения на экране и размера формы
        setBounds(100, 100, 900, 500);
        //Неизменяемый размер
        setResizable(false);
        //Инициализация панели
        contentPane = new JPanel();
        //Установка её границ
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        //Все содержимое будет отрисовываться в панеле
        setContentPane(contentPane);
        //Установка Layout
        contentPane.setLayout(null);


        //Кнопка создания Самолета
        JButton btnCreatePlane = new JButton("Создать Самолет");
        btnCreatePlane.setBounds(719, 11, 150, 23);
        btnCreatePlane.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreatePlane);

        //Кнопка создания Самолета c радаром
        JButton btnCreateRadarPlane = new JButton("Создать Самолет с Радаром");
        btnCreateRadarPlane.setBounds(719, 54, 150, 23);
        btnCreateRadarPlane.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreateRadarPlane);

        //Стрелочка Вверх
        JButton btnUp = new JButton("");
        btnUp.setBounds(804, 379, 30, 30);
        btnUp.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\arrowUp.jpg"));
        contentPane.add(btnUp);

        //Стрелочка Влево
        JButton btnLeft = new JButton("");
        btnLeft.setBounds(764, 420, 30, 30);
        btnLeft.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\arrowLeft.png"));
        contentPane.add(btnLeft);

        //Стрелочка Вниз
        JButton btnDown = new JButton("");
        btnDown.setBounds(804, 420, 30, 30);
        btnDown.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\загружено.png"));
        contentPane.add(btnDown);

        //Стрелочка Вправо
        JButton btnRight = new JButton("");
        btnRight.setBounds(844, 420, 30, 30);
        btnRight.setIcon(new ImageIcon("C:\\Users\\kutyg\\Desktop\\arrowRight.png"));
        contentPane.add(btnRight);

        //Привязка действия на кнопку "Создать самолет"
        btnCreatePlane.addActionListener(e -> {
            Random rnd = new Random();
            plane = new Plane(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.WHITE);
            plane.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });

        //Привязка действия на кнопку "Создать самолет с радаром"
        btnCreateRadarPlane.addActionListener(e -> {
            Random rnd = new Random();
            int radarAmount = rnd.nextInt(3)+1;
            plane = new RadarPlane(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.WHITE, Color.BLACK, true, true, radarAmount);
            plane.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });


        //Привязка действия на стрелочку "Вверх"
        btnUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (plane != null) {
                    plane.MoveTransport(Directions.UP);
                    repaint();
                }
            }
        });

        //Привязка действия на стрелочку "Влево"
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

        //Привязка действия на стрелочку "Вниз"
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

}
