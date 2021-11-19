import java.awt.*;
public abstract class Vehicle implements ITransport {
    protected int _startPosX;
    protected int _startPosY;
    protected int _pictureWidth;
    protected int _pictureHeight;
    private int MaxSpeed;
    private int Weight;
    private Color MainColor;
    protected void setMaxSpeed(int maxSpeed){
        this.MaxSpeed = maxSpeed;
    }
    public int getMaxSpeed(){
        return this.MaxSpeed;
    }
    protected void setWeight(int weight){
        this.Weight = weight;
    }
    protected int getWeight(){
        return this.Weight;
    }
    protected void setMainColor(Color mainColor){
        this.MainColor = mainColor;
    }
    public Color getMainColor(){
        return this.MainColor;
    }
    public void SetPosition(int x, int y, int width, int height){
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    public abstract void DrawTransport(Graphics g);
    public abstract void MoveTransport(Directions directions);
}
