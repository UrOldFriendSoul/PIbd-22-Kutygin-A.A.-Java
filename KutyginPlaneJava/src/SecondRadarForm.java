import java.awt.*;

public class SecondRadarForm implements IRadars {
    private RadarAmount radarAmount;
    int amount;

    public SecondRadarForm(int d){
        SetRadarAmount(d);
        amount = d;
    }

    public void SetRadarAmount(int d)
    {
        radarAmount = RadarAmount.getRadarAmount(d);
    }

    public int GetRadarAmount()
    {
        return amount;
    }

    public String GetRadarFormName()
    {
        return "SecondRadarForm";
    }

    public void DrawPart(Graphics g,Color color, int x, int y)
    {
        //Позиция по оси Х
        int _startPosX = x;

        //Позиция по оси Y
        int _startPosY = y;
        g.setColor(color);
        switch (radarAmount){
            case One: {
                g.fillRect(_startPosX + 70, _startPosY + 30, 4, 8);
                Polygon radar = new Polygon();
                radar.addPoint(_startPosX + 70, _startPosY + 30);
                radar.addPoint(_startPosX + 90, _startPosY + 30);
                radar.addPoint(_startPosX + 65, _startPosY + 10);
                g.fillPolygon(radar);
            }

            case Two: {
                g.fillRect(_startPosX + 90, _startPosY + 30, 4, 8);
                Polygon radar2 = new Polygon();
                radar2.addPoint(_startPosX + 90, _startPosY + 30);
                radar2.addPoint(_startPosX + 110, _startPosY + 30);
                radar2.addPoint(_startPosX + 85, _startPosY + 10);
                g.fillPolygon(radar2);
            }

            case Three: {
                g.fillRect(_startPosX + 50, _startPosY + 30, 4, 8);
                Polygon radar3 = new Polygon();
                radar3.addPoint(_startPosX + 50, _startPosY + 30);
                radar3.addPoint(_startPosX + 70, _startPosY + 30);
                radar3.addPoint(_startPosX + 45, _startPosY + 10);
                g.fillPolygon(radar3);
            }
            break;
        }
    }
}