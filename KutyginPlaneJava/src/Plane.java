import java.awt.*;

public class Plane extends Vehicle{
private int planeWidth = 120;
private int planeHeight = 60;
public Plane(int maxSpeed, int weight, Color mainColor){
setMaxSpeed(maxSpeed);
setWeight(weight);
setMainColor(mainColor);
}

protected Plane(int maxSpeed, int weight, Color mainColor, int planeWidth, int planeHeight){
    setMaxSpeed(maxSpeed);
    setWeight(weight);
    setMainColor(mainColor);
    this.planeWidth =  planeWidth;
    this.planeHeight =  planeHeight;
}

@Override
    public void MoveTransport(Directions directions){
    int step = getMaxSpeed()*100 / getWeight();
    switch (directions){
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
    @Override
    public void DrawTransport(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getMainColor());
        //base
        g.fillRect( _startPosX, _startPosY + 35, 120, 30);
        g2d.setColor(getMainColor());
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
        //Wheels
        g2d.setColor(getMainColor());
        g.fillRect( _startPosX + 20, _startPosY + 65, 4, 8);
        g.fillRect( _startPosX + 110, _startPosY + 65, 4, 8);
        g.fillOval( _startPosX + 20, _startPosY + 70, 7, 7);
        g.fillOval( _startPosX + 15, _startPosY + 70, 7, 7);
        g.fillOval( _startPosX + 108, _startPosY + 70, 7, 7);
    }
}
