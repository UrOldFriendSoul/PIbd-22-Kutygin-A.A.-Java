import java.awt.*;

public interface IRadars {
    void DrawPart(Graphics g, Color color, int x, int y);
    void SetRadarAmount(int d);
    int GetRadarAmount();
    String GetRadarFormName();
}