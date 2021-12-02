import java.util.Random;
import java.awt.*;

public class RadarPlane extends Plane {
    Random r = new Random();

    //Объект от класса ДопКласс
    private int radarAmount;
    private IRadars RadarForm;

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
    public RadarPlane(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean frontWing, boolean backTailWings, int radarAmount)
    {
        super(maxSpeed,weight,mainColor,120,60);
        SetDopColor(dopColor);
        SetFrontWing(frontWing);
        SetBackTailWings(backTailWings);
        //Инициализация объекта для отрисовки радаров
        this.radarAmount = radarAmount;
        GetRadarForm();
    }
    //Метод для выбора формы радара
    private void GetRadarForm(){
        Random rnd = new Random();
        int radarForm = rnd.nextInt(3)+1;
        if (radarForm == 1) {
            RadarForm = new Radar(radarAmount);
        }
        if (radarForm == 2) {
            RadarForm = new SecondRadarForm(radarAmount);
        }
        if (radarForm == 3){
            RadarForm = new ThirdRadarForm(radarAmount);
        }
    }

    //Метод для передвижения самодета

    @Override
    public void DrawTransport(Graphics g) {
        super.DrawTransport(g);
        Graphics2D g2d = (Graphics2D) g;

        //TailWings
        if(backTailWings) {
            g2d.setColor(GetDopColor());
            g.fillOval(_startPosX - 3, _startPosY + 30, 30, 7);
        }
        //BaseWings
        if(frontWing)
        g2d.setColor(GetDopColor());
        g.fillOval(_startPosX+25 ,_startPosY + 45,70,7);

        //Radars
        RadarForm.DrawPart(g,GetDopColor(), _startPosX,_startPosY);
    }
}

