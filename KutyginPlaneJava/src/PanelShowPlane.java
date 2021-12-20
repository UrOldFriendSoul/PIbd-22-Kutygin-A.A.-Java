import javax.swing.*;
import java.awt.*;

public class PanelShowPlane extends JPanel
{
    private Vehicle plane;
    public void paintComponent(Graphics g)
    {
        if (plane!=null)
        {
            plane.DrawTransport(g);
        }
    }
    public void setPlane(Vehicle plane)
    {
        this.plane = plane;
        this.plane.SetPosition(145,20,270,90);
    }
}