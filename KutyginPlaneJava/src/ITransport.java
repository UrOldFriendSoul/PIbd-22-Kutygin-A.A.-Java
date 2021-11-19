import java.awt.*;

public interface ITransport {
    void SetPosition(int x, int y, int width, int height);

    void MoveTransport(Directions directions);

    void DrawTransport(Graphics g);
}
