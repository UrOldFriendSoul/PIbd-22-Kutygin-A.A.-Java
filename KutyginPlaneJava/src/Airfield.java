import java.awt.*;
import javax.swing.*;

public class Airfield <T extends ITransport, S extends IRadars> {
    private T []_places;
    private int pictureWidth;
    private int pictureHeight;
    private int _placeSizeWidth = 270;
    private int _placeSizeHeight = 90;
    private int width;
    private int height;
    public Airfield (int picWidth, int picHeight){
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        _places = (T[]) new ITransport[width*height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }
    public int Plus(T plane)
    {
        int i = 0;
        int j = 0;
        while (i < height)
        {
            j = 0;
            while (j < width)
            {
                if (_places[i*width + j] == null)
                {
                    _places[i*width + j] = plane;
                    plane.SetPosition(25 + j * _placeSizeWidth, 40 + i * _placeSizeHeight, pictureWidth, pictureHeight);
                    return i*width + j;
                }
                j++;
            }
            i++;
        }
        return -1;
    }
    public T Minus(int index)
    {
        if (index > -1 && index < _places.length && _places[index] != null)
        {
            T dop = _places[index];
            _places[index] = null;
            return dop;
        }
        return null;
    }
    public boolean More(Airfield<ITransport, IRadars> airfield, int x)
    {
        int count = 0;
        for (int i = 0; i < airfield._places.length; i++)
        {if(airfield._places != null){
            count++;
        }

        }
        return count < x;
    }
    public boolean Less(Airfield<ITransport, IRadars> airfield, int x)
    {
        int count = 0;
        for (int i = 0; i < airfield._places.length; i++)
        {if(airfield._places != null){
            count++;
        }

        }
        return count > x;
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.length; i++)
        {
            if(_places[i]!=null)
                _places[i].DrawTransport(g);
        }
    }
    public void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++)
        {
            for (int j = 0; j < 6; ++j)
            {//линия рамзетки места
                g.drawLine( i * _placeSizeWidth+10, j * _placeSizeHeight+35, i * _placeSizeWidth + 145, j * _placeSizeHeight+35);
            }
            g.drawLine( i * _placeSizeWidth+10, 35, i * _placeSizeWidth+10, 485);
        }
    }

}

