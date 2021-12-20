import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.Objects;
import java.awt.event.ActionEvent;

public class FormPlaneConfig extends JFrame {
    private JPanel panelConfig;
    private JPanel panelParameters;
    private JPanel panelColors;
    private JPanel panelRadarType;
    private JPanel panelPlaneType;
    private PanelShowPlane panelShowPlane;
    private JCheckBox checkBoxBackTailWings;
    private JCheckBox checkBoxFrontWing;
    private JCheckBox checkBoxRadars;
    private JSpinner spinnerWeight;
    private JSpinner spinnerMaxSpeed;
    public Plane plane;

    public FormPlaneConfig(FormAirfield formAirfield){
        super("Выбор самолета");
        setSize(1050,550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelConfig = new JPanel();
        panelConfig.setBounds(5,5,1050,550);
        setContentPane(panelConfig);
        panelConfig.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK,1);

        panelPlaneType = new JPanel();
        panelPlaneType.setBounds(21,12,259,105);
        panelPlaneType.setLayout(null);
        panelPlaneType.setBorder(BorderFactory.createTitledBorder("Тип самолета"));
        panelConfig.add(panelPlaneType);

        JLabel labelPlane = new JLabel("Самолет", SwingConstants.CENTER);
        labelPlane.setBorder(border);
        labelPlane.setBounds(21,20,214,30);
        labelPlane.addMouseListener(new DragMouseAdapter());
        labelPlane.setTransferHandler(new TransferHandler("text"));
        panelPlaneType.add(labelPlane);

        JLabel labelRadarPlane = new JLabel("Самолет с радаром", SwingConstants.CENTER);
        labelRadarPlane.setBounds(21,60,214,30);
        labelRadarPlane.setBorder(border);
        labelRadarPlane.addMouseListener(new DragMouseAdapter());
        labelRadarPlane.setTransferHandler(new TransferHandler("text"));
        panelPlaneType.add(labelRadarPlane);

        JLabel labelTypeCheck = new JLabel("");
        labelTypeCheck.setBounds(305,30,404,128);
        labelTypeCheck.setTransferHandler(new TransferHandler("text"));
        panelConfig.add(labelTypeCheck);

        panelParameters = new JPanel();
        panelParameters.setLayout(null);
        panelParameters.setBorder(BorderFactory.createTitledBorder("Параметры"));
        panelParameters.setBounds(270,270,500,200);
        panelConfig.add(panelParameters);

        checkBoxBackTailWings = new JCheckBox("Хвост самолета");
        checkBoxBackTailWings.setBounds(323,70,140,24);
        panelParameters.add(checkBoxBackTailWings);
        checkBoxFrontWing = new JCheckBox("Боковое крыло");
        checkBoxFrontWing.setBounds(323,145,140,24);
        panelParameters.add(checkBoxFrontWing);
        checkBoxRadars = new JCheckBox("Радар");
        checkBoxRadars.setBounds(323,110,140,24);
        panelParameters.add(checkBoxRadars);

        spinnerWeight = new JSpinner(new SpinnerNumberModel(100,100,1000,1));
        spinnerWeight.setBounds(21,145,103,26);
        panelParameters.add(spinnerWeight);
        spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100,100,1000,1));
        spinnerMaxSpeed.setBounds(21,70,103,26);
        panelParameters.add(spinnerMaxSpeed);

        JLabel labelMaxSpeed = new JLabel("Максимальная скорость: ");
        labelMaxSpeed.setBounds(17,44,203,20);
        panelParameters.add(labelMaxSpeed);
        JLabel labelWeight = new JLabel("Вес самолета: ");
        labelWeight.setBounds(17,122,107,20);
        panelParameters.add(labelWeight);

        panelShowPlane = new PanelShowPlane();
        panelShowPlane.setBounds(305,30,404,128);
        panelShowPlane.setBorder(border);
        panelConfig.add(panelShowPlane);

        panelColors = new JPanel();
        panelColors.setLayout(null);
        panelColors.setBounds(744,12,255,249);
        panelColors.setBorder(BorderFactory.createTitledBorder("Цвета"));
        panelConfig.add(panelColors);

        JPanel panelColor1 = new JPanel();
        panelColor1.setBorder(border);
        panelColor1.setBackground(Color.RED);
        panelColor1.setBounds(194,140,39,36);
        panelColor1.addMouseListener(new DragMouseAdapter());
        panelColor1.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor1);

        JPanel panelColor2 = new JPanel();
        panelColor2.setBorder(border);
        panelColor2.setBackground(Color.YELLOW);
        panelColor2.setBounds(75,140,39,36);
        panelColor2.addMouseListener(new DragMouseAdapter());
        panelColor2.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor2);

        JPanel panelColor3 = new JPanel();
        panelColor3.setBorder(border);
        panelColor3.setBackground(Color.BLACK);
        panelColor3.setBounds(16,140,39,36);
        panelColor3.addMouseListener(new DragMouseAdapter());
        panelColor3.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor3);

        JPanel panelColor4 = new JPanel();
        panelColor4.setBorder(border);
        panelColor4.setBackground(Color.WHITE);
        panelColor4.setBounds(136,140,39,36);
        panelColor4.addMouseListener(new DragMouseAdapter());
        panelColor4.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor4);

        JPanel panelColor5 = new JPanel();
        panelColor5.setBorder(border);
        panelColor5.setBackground(Color.GRAY);
        panelColor5.setBounds(136,100,39,36);
        panelColor5.addMouseListener(new DragMouseAdapter());
        panelColor5.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor5);

        JPanel panelColor6 = new JPanel();
        panelColor6.setBorder(border);
        panelColor6.setBackground(Color.ORANGE);
        panelColor6.setBounds(75,100,39,36);
        panelColor6.addMouseListener(new DragMouseAdapter());
        panelColor6.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor6);

        JPanel panelColor7 = new JPanel();
        panelColor7.setBorder(border);
        panelColor7.setBackground(Color.GREEN);
        panelColor7.setBounds(194,100,39,36);
        panelColor7.addMouseListener(new DragMouseAdapter());
        panelColor7.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor7);

        JPanel panelColor8 = new JPanel();
        panelColor8.setBorder(border);
        panelColor8.setBackground(Color.BLUE);
        panelColor8.setBounds(16,100,39,36);
        panelColor8.addMouseListener(new DragMouseAdapter());
        panelColor8.setTransferHandler(new TransferHandler("background"));
        panelColors.add(panelColor8);

        JLabel labelMainColor = new JLabel("Основной цвет", SwingConstants.CENTER);
        labelMainColor.setBounds(16,38,98,42);
        labelMainColor.setBorder(border);
        labelMainColor.setTransferHandler(new TransferHandler("background"));
        panelColors.add(labelMainColor);

        JLabel labelDopColor = new JLabel("Дополнительный цвет", SwingConstants.CENTER);
        labelDopColor.setBounds(136,38,97,42);
        labelDopColor.setBorder(border);
        labelDopColor.setTransferHandler(new TransferHandler("background"));
        panelColors.add(labelDopColor);

        JButton btnAdd = new JButton("Добавить");
        btnAdd.setBounds(840,310,106,46);
        panelConfig.add(btnAdd);

        JButton btnCancel = new JButton("Отмена");
        btnCancel.setBounds(840,380,106,48);
        panelConfig.add(btnCancel);

        panelRadarType = new JPanel();
        panelRadarType.setLayout(null);
        panelRadarType.setBorder(BorderFactory.createTitledBorder("Тип радара"));
        panelRadarType.setBounds(21,125,259,145);
        panelConfig.add(panelRadarType);


        JLabel labelRadarOne = new JLabel("1 Тип Радара", SwingConstants.CENTER);
        labelRadarOne.setBounds(21,20,214,30);
        labelRadarOne.setBorder(border);
        labelRadarOne.addMouseListener(new DragMouseAdapter());
        labelRadarOne.setTransferHandler(new TransferHandler("text"));
        panelRadarType.add(labelRadarOne);

        JLabel labelRadarTwo = new JLabel("2 Тип Радара", SwingConstants.CENTER);
        labelRadarTwo.setBounds(21,60,214,30);
        labelRadarTwo.setBorder(border);
        labelRadarTwo.addMouseListener(new DragMouseAdapter());
        labelRadarTwo.setTransferHandler(new TransferHandler("text"));
        panelRadarType.add(labelRadarTwo);

        JLabel labelRadarThree = new JLabel("3 Тип Радара", SwingConstants.CENTER);
        labelRadarThree.setBounds(21,100,214,30);
        labelRadarThree.setBorder(border);
        labelRadarThree.addMouseListener(new DragMouseAdapter());
        labelRadarThree.setTransferHandler(new TransferHandler("text"));
        panelRadarType.add(labelRadarThree);

        JLabel labelRadarTypeCheck = new JLabel("Тип радара", SwingConstants.CENTER);
        labelRadarTypeCheck.setBounds(25,190,200,42);
        labelRadarTypeCheck.setBorder(border);
        labelRadarTypeCheck.setTransferHandler(new TransferHandler("text"));
        panelColors.add(labelRadarTypeCheck);

        PropertyChangeListener radarTypeListener = propertyChangeEvent -> {
            if (plane != null && checkBoxRadars.isSelected())
            {
                RadarPlane radarPlane = (RadarPlane)plane;
                if(Objects.equals(labelRadarTypeCheck.getText(), "1 Тип Радара"))
                {
                    Random rnd = new Random();
                    int radarAmount = rnd.nextInt(3) + 1;
                    radarPlane.setRadarForm(new Radar(radarAmount));
                }
                if(Objects.equals(labelRadarTypeCheck.getText(), "2 Тип Радара"))
                {
                    Random rnd = new Random();
                    int radarAmount = rnd.nextInt(3) + 1;
                    radarPlane.setRadarForm(new SecondRadarForm(radarAmount));
                }
                if(Objects.equals(labelRadarTypeCheck.getText(), "3 Тип Радара"))
                {
                    Random rnd = new Random();
                    int radarAmount = rnd.nextInt(3) + 1;
                    radarPlane.setRadarForm(new ThirdRadarForm(radarAmount));
                }
                plane = radarPlane;
                panelShowPlane.setPlane(plane);
                repaint();
            }
        };
        PropertyChangeListener planeTypeListener = propertyChangeEvent -> {
            if(Objects.equals(labelTypeCheck.getText(), "Самолет"))
            {
                SetPlane();
            }
            if(Objects.equals(labelTypeCheck.getText(), "Самолет с радаром"))
            {
                SetRadarPlane();
            }
            labelTypeCheck.setText("");
        };

        PropertyChangeListener mainColorListener = propertyChangeEvent -> {
            if (plane != null)
            {
                plane.setMainColor(labelMainColor.getBackground());
                panelShowPlane.setPlane(plane);
                repaint();
            }
        };

        PropertyChangeListener dopColorListener = propertyChangeEvent -> {
            if (plane != null && (checkBoxFrontWing.isSelected() || checkBoxBackTailWings.isSelected() || checkBoxRadars.isSelected()))
            {
                RadarPlane radarPlane = (RadarPlane)plane;
                radarPlane.SetDopColor(labelDopColor.getBackground());
                plane = radarPlane;
                panelShowPlane.setPlane(plane);
                repaint();
            }
        };

        labelTypeCheck.addPropertyChangeListener("text", planeTypeListener);
        labelMainColor.addPropertyChangeListener("background", mainColorListener);
        labelDopColor.addPropertyChangeListener("background", dopColorListener);
        labelRadarTypeCheck.addPropertyChangeListener("text", radarTypeListener);

        btnCancel.addActionListener(actionEvent -> dispose());
        btnAdd.addActionListener((ActionEvent e) -> {
            if(plane!=null)
            {
                formAirfield.addPlane(plane);
            }
            dispose();
        });
    }

    public void paint(Graphics g)
    {
        super.paint(g);
    }

    private void SetPlane()
    {
        plane = new Plane((Integer)spinnerMaxSpeed.getValue(), (Integer)spinnerWeight.getValue(), Color.BLACK);
        panelShowPlane.setPlane(plane);
        repaint();
    }

    private void SetRadarPlane()
    {
        Random rnd = new Random();
        int radarAmount = 0;
        if(checkBoxRadars.isSelected())
        {
            radarAmount = rnd.nextInt(3) + 1;
        }
        plane = new RadarPlane((Integer)spinnerMaxSpeed.getValue(), (Integer)spinnerWeight.getValue(), Color.BLACK, Color.BLACK, checkBoxFrontWing.isSelected(), checkBoxBackTailWings.isSelected(), new Radar(radarAmount));
        panelShowPlane.setPlane(plane);
        repaint();
    }
}
