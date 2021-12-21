import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.EventQueue;
import java.util.Stack;

public class FormAirfield extends JFrame {

    private final Airfield<Plane, IRadars> airfield;
    private Plane plane;
    private AirfieldCollection airfieldCollection;
    private JList<String> jListBoxAirfield;
    private DefaultListModel<String> defListModelAirfield;

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

        defListModelAirfield = new DefaultListModel<>();
        airfieldPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        airfieldPanel.setBounds(0, 0, 900, 500);
        setContentPane(airfieldPanel);
        airfieldPanel.setLayout(null);

        airfieldCollection = new AirfieldCollection(airfieldPanel.getHeight(), airfieldPanel.getWidth());

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

        JButton btnAddAirfield = new JButton("Добавить аэродром");
        btnAddAirfield.setBounds(25,25,150,40);
        btnAddAirfield.setMargin(new Insets(10,10,10,10));
        panelManage.add(btnAddAirfield);

        JButton btnDelAirfield = new JButton("Удалить аэродром");
        btnDelAirfield.setBounds(25,150,150,40);
        btnDelAirfield.setMargin(new Insets(10,10,10,10));
        panelManage.add(btnDelAirfield);

        JButton btnParkPlane = new JButton("Добавить самолет");
        btnParkPlane.setBounds(25, 215, 150, 40);
        btnParkPlane.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkPlane);

        JButton btnParkingForDeletedCars = new JButton("Изъятые самолеты");
        btnParkingForDeletedCars.setBounds(25,410,150,40);
        btnParkingForDeletedCars.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkingForDeletedCars);

        JPanel panelTakeAway = new JPanel();
        panelTakeAway.setBorder(BorderFactory.createTitledBorder("Забрать самолет"));
        panelTakeAway.setBounds(25, 295, 150, 100);
        panelTakeAway.setLayout(null);
        panelManage.add(panelTakeAway);

        JLabel labelTakeAway = new JLabel("Место:");
        labelTakeAway.setBounds(25, 40, 50, 20);
        panelTakeAway.add(labelTakeAway);

        JTextField textFieldTakeAway = new JTextField();
        textFieldTakeAway.setBounds(80, 40, 45, 20);
        textFieldTakeAway.setColumns(5);
        panelTakeAway.add(textFieldTakeAway);

        JLabel labelAirfields = new JLabel("Аэродром:");
        labelAirfields.setBounds(25,0,70,20);
        panelManage.add(labelAirfields);

        JTextField addDelAirfieldField = new JTextField();
        addDelAirfieldField.setBounds(95,0,70,20);
        addDelAirfieldField.setColumns(5);
        panelManage.add(addDelAirfieldField);

        jListBoxAirfield = new JList<>(defListModelAirfield);
        jListBoxAirfield.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListBoxAirfield.addListSelectionListener(new jListSelectedIndexChanged());

        final JScrollPane scrollPane = new JScrollPane(jListBoxAirfield);
        scrollPane.setBounds(25, 65,150,120);
        panelManage.add(scrollPane);

        JButton buttonTakeAway = new JButton("Забрать");
        buttonTakeAway.setMargin(new Insets(10, 10, 10, 10));
        buttonTakeAway.setBounds(15, 65, 120, 25);
        panelTakeAway.add(buttonTakeAway);

        airfield = new Airfield<>(panelAirfield.getWidth(), panelAirfield.getHeight());

        Stack<Vehicle> deletedPlanes = new Stack<>();

        JMenuBar jMenuBar = new JMenuBar();

        JFileChooser jFileChooser = new JFileChooser();

        JMenu jMenu = new JMenu("Файл");
        jMenuBar.add(jMenu);

        JMenuItem jMenuItemSaveFile = new JMenuItem("Сохранить");
        JMenuItem jMenuItemLoadFile = new JMenuItem("Загрузить");
        jMenu.add(jMenuItemLoadFile);
        jMenu.add(jMenuItemSaveFile);

        JMenu jMenuBase = new JMenu("Аэродром");
        jMenuBar.add(jMenuBase);

        JMenuItem jMenuItemSaveAirfield = new JMenuItem("Сохранить");
        JMenuItem jMenuItemLoadAirfield = new JMenuItem("Загрузить");
        jMenuBase.add(jMenuItemSaveAirfield);
        jMenuBase.add(jMenuItemLoadAirfield);

        setJMenuBar(jMenuBar);

        jMenuItemSaveFile.addActionListener(e->{
            if(jFileChooser.showDialog(null, "Сохранить в файл") == jFileChooser.APPROVE_OPTION)
            {
                if(airfieldCollection.SaveData(jFileChooser.getSelectedFile()))
                {
                    JOptionPane.showMessageDialog(null, "Сохранение прошло успешно", "Результат", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Не сохранилось", "Результат", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jMenuItemLoadFile.addActionListener(e->{
            if(jFileChooser.showDialog(null, "Загрузить из файла") == jFileChooser.APPROVE_OPTION)
            {
                if(airfieldCollection.LoadData(jFileChooser.getSelectedFile()))
                {
                    JOptionPane.showMessageDialog(null, "Загрузили", "Результат", JOptionPane.WARNING_MESSAGE);
                    reloadLevel();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Не загрузили", "Результат", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jMenuItemSaveAirfield.addActionListener(e->{
            if(jFileChooser.showDialog(null, "Сохранить в файл") == jFileChooser.APPROVE_OPTION)
            {
                if(airfieldCollection.SaveAirfield(jFileChooser.getSelectedFile(), jListBoxAirfield.getSelectedValue()))
                {
                    JOptionPane.showMessageDialog(null, "Сохранение прошло успешно", "Результат", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Не сохранилось", "Результат", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jMenuItemLoadAirfield.addActionListener(e->{
            if(jFileChooser.showDialog(null, "Загрузить из файла") == jFileChooser.APPROVE_OPTION)
            {
                if(airfieldCollection.LoadAirfield(jFileChooser.getSelectedFile()))
                {
                    JOptionPane.showMessageDialog(null, "Загрузили", "Результат", JOptionPane.WARNING_MESSAGE);
                    reloadLevel();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Не загрузили", "Результат", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnParkingForDeletedCars.addActionListener(e ->{
            if (!deletedPlanes.isEmpty())
            {
                FormPlane formPlane = new FormPlane();
                formPlane.SetPlane(deletedPlanes.pop());
                formPlane.setVisible(true);
            }
        });

        btnAddAirfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addDelAirfieldField.getText().equals("")) {
                    airfieldCollection.AddAirfield(addDelAirfieldField.getText());
                    reloadLevel();
                } else {
                    JOptionPane.showMessageDialog(null, "Введите название аэродрома", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                reloadLevel();
            }
        });

        btnDelAirfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jListBoxAirfield.getSelectedValue() != null)
                {
                    if (JOptionPane.showConfirmDialog(null, "Удалить аэродром " + "*" + jListBoxAirfield.getSelectedValue() + "*" + " ?") == 0)
                    {
                        airfieldCollection.DeleteAirfield(jListBoxAirfield.getSelectedValue());
                        reloadLevel();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Данного аэродрома не существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnParkPlane.addActionListener(e -> {
            if (jListBoxAirfield.getSelectedIndex() > -1){
                FormPlaneConfig fpc = new FormPlaneConfig(this);
                fpc.setVisible(true);
            }
        });

        buttonTakeAway.addActionListener((e) -> {
            plane = airfieldCollection.get(jListBoxAirfield.getSelectedValue()).Minus(Integer.parseInt(textFieldTakeAway.getText()));
            if (plane != null) {
                deletedPlanes.add(plane);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Это место пусто!");
            }

            repaint();
        });
    }

    public class jListSelectedIndexChanged implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e){
            repaint();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(jListBoxAirfield != null){
            if(jListBoxAirfield.getSelectedValue() != null){
                airfieldCollection.get(jListBoxAirfield.getSelectedValue()).Draw(g);
            }
        }
    }
    public void addPlane(Plane plane)
    {
        if((airfieldCollection.get(jListBoxAirfield.getSelectedValue()).Plus(plane))!=-1)
        {
            repaint();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Аэродром переполнен");
        }
    }

    private void reloadLevel(){
        int index = jListBoxAirfield.getSelectedIndex();
        defListModelAirfield.clear();
        for (int i = 0; i < airfieldCollection.Keys().size(); i++){
            defListModelAirfield.addElement(airfieldCollection.Keys().get(i));
        }
        if(defListModelAirfield.size() > 0 && (index == -1 || index >= defListModelAirfield.size())){
            jListBoxAirfield.setSelectedIndex(0);
        }
        else
        {
            if(defListModelAirfield.size() > 0 && index >-1 && index < defListModelAirfield.size()){
                jListBoxAirfield.setSelectedIndex(index);
            }
        }
    }
}
