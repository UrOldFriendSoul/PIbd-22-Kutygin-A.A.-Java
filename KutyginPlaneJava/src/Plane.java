import java.util.Random;
import java.awt.*;

public class Plane {
    Random r = new Random();

    //Объект от класса ДопКласс
    private Radar radarAmount;

    //Позиция по оси Х
    private int _startPosX;

    //Позиция по оси Y
    private int _startPosY;

    //Ширина окна отрисовки
    private int _pictureWidth = 900;

    //Высота окна отрисовки
    private int _pictureHeight = 500;

    //Ширина самолета
    private final int planeWidth = 120;

    //Высота самолета
    private final int planeHeight = 60;
    //Признак наличия скорости самолета
    private int maxSpeed;
    public void SetMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }
    public int GetMaxSpeed(){
        return maxSpeed;
    }
    //Признак наличия веса самолета
    public int weight;
    public void SetWeight(int weight){
        this.weight = weight;
    }
    public int GetWeight(){
        return weight;
    }
    //Признак наличия основного цвета
    public Color mainColor;
    public void SetMainColor(Color mainColor){
        this.mainColor = mainColor;
    }
    public Color GetMainColor(){
        return mainColor;
    }
    //Признак наличия дополнительного цвета
    public Color dopColor;
    public void SetDopColor(Color dopColor){
        this.dopColor = dopColor;
    }
    public Color GetDopColor(){
        return dopColor;
    }

    // Признак наличия бокового крыла
    private boolean frontWing;
    public void SetFrontWing(boolean frontWing){
        this.frontWing = frontWing;
    }
    public boolean GetFrontWing(){
        return frontWing;
    }

    // Признак наличия дополнительных крыльев хвоста
    private boolean backTailWings;
    public void SetBackTailWings(boolean backTailWings){
        this.backTailWings = backTailWings;
    }
    public boolean GetBackTailWings(){
        return backTailWings;
    }

    // Признак наличия радара
    private boolean radar;
    public void SetRadar(boolean radar){
        this.radar = radar;
    }
    public boolean GetRadar(){
        return radar;
    }

    //Количество радаров
    private int numbOfRadars;
    private void SetNumbOfRadars(int numbOfRadars){
        this.numbOfRadars = numbOfRadars;
    }
    private int GetNumbOfRadars(){
        return numbOfRadars;
    }
    public void Init(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean frontWing, boolean backTailWings, int radarAmount) {
        SetMaxSpeed(maxSpeed);
        SetWeight(weight);
        SetMainColor(mainColor);
        SetDopColor(dopColor);
        SetFrontWing(frontWing);
        SetBackTailWings(backTailWings);
        //Инициализация объекта для отрисовки радаров
        this.radarAmount = new Radar(radarAmount);
    }
    //Метод установки начальной позиции самолета
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    //Метод для передвижения самодета
    public void MoveTransport(Directions direction)
    {
        int step = GetMaxSpeed() * 100 / GetWeight();
        switch (direction)
        {
            // вправо
            case RIGHT:
                if (_startPosX + step < _pictureWidth - 150 - planeWidth)
                {
                    _startPosX += step;
                }
                break;
            //влево
            case LEFT:
                if (_startPosX - step > 11)
                {
                    _startPosX -= step;
                }
                break;
            //вверх
            case UP:
                if (_startPosY - step > 40)
                {
                    _startPosY -= step;
                }
                break;
            //вниз
            case DOWN:
                if (_startPosY + step < _pictureHeight + 35 - planeHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }
    public void DrawTransport(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        //base
        g.fillRect( _startPosX, _startPosY + 35, 120, 30);
        g2d.setColor(Color.GRAY);
        g.fillOval( _startPosX - 10, _startPosY + 35, 30, 30);
        //Tail
        Polygon tail = new Polygon();
        tail.addPoint(_startPosX, _startPosY + 35);
        tail.addPoint(_startPosX, _startPosY + 10);
        tail.addPoint(_startPosX + 40, _startPosY + 35);
        g.fillPolygon(tail);
        //Cabin
        Polygon cabin = new Polygon();
        cabin.addPoint(_startPosX + 120, _startPosY + 37);
        cabin.addPoint(_startPosX + 155, _startPosY + 63);
        cabin.addPoint(_startPosX + 120, _startPosY + 63);
        g.drawPolygon(cabin);
        //TailWings
        if(backTailWings) {
            g2d.setColor(Color.BLACK);
            g.fillOval(_startPosX - 3, _startPosY + 30, 30, 7);
        }
        //BaseWings
        if(frontWing)
        g2d.setColor(Color.BLACK);
        g.fillOval(_startPosX+25 ,_startPosY + 45,70,7);



        //Wheels
        g2d.setColor(Color.GRAY);
        g.fillRect( _startPosX + 20, _startPosY + 65, 4, 8);
        g.fillRect( _startPosX + 110, _startPosY + 65, 4, 8);
        g.fillOval( _startPosX + 20, _startPosY + 70, 7, 7);
        g.fillOval( _startPosX + 15, _startPosY + 70, 7, 7);
        g.fillOval( _startPosX + 108, _startPosY + 70, 7, 7);

        //Radars
        radarAmount.DrawPart(g,GetDopColor(), _startPosX,_startPosY);
    }
}

