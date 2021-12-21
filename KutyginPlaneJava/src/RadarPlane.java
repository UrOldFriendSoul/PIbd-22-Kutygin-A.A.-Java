import java.util.Random;
import java.util.Objects;
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
    IRadars GetRadarForm(){
        return RadarForm;
    }
    public RadarPlane(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean frontWing, boolean backTailWings, IRadars radarForm)
    {
        super(maxSpeed,weight,mainColor,120,60);
        SetDopColor(dopColor);
        SetFrontWing(frontWing);
        SetBackTailWings(backTailWings);
        //Инициализация объекта для отрисовки радаров
        RadarForm = radarForm;
    }
    public RadarPlane(String info)
    {
        super(info);
        String[] strs = info.split(separator);
        if (strs.length == 8)
        {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Integer.parseInt(strs[1]);
            MainColor = Color.decode(strs[2]);
            dopColor = Color.decode(strs[3]);
            frontWing = Boolean.parseBoolean(strs[4]);
            backTailWings = Boolean.parseBoolean(strs[5]);
            if(Objects.equals(strs[6], "Radar"))
            {
                RadarForm = new Radar(Integer.parseInt(strs[7]));
            }
            if(Objects.equals(strs[6], "SecondRadarForm"))
            {
                RadarForm = new SecondRadarForm(Integer.parseInt(strs[7]));
            }
            if(Objects.equals(strs[6], "ThirdRadarForm"))
            {
                RadarForm = new ThirdRadarForm(Integer.parseInt(strs[7]));
            }
        }
    }

    public void setRadarForm(IRadars radarForm){
        RadarForm = radarForm;
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
        if(RadarForm != null){
            RadarForm.DrawPart(g, GetDopColor(), _startPosX, _startPosY);
        }
    }
    public String GetRadarPlaneConfig()
    {
        String radarFormString = "";
        radarFormString = RadarForm.GetRadarFormName();
        return(MaxSpeed + ";" + Weight + ";" + MainColor.getRGB() + ";" + dopColor.getRGB() + ";" + GetFrontWing() + ";" + GetBackTailWings() + ";" + radarFormString + ";" + RadarForm.GetRadarAmount());
    }
}

