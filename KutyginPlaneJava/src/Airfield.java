import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Airfield <T extends ITransport, S extends IRadars> {
    private final List<T> _places;
    private int pictureWidth;
    private int pictureHeight;
    private int _placeSizeWidth = 310;
    private int _placeSizeHeight = 100;
    private int width;
    private int height;
    private int _maxCount;
    public T getPlane(int i )
    {
        if (i > -1 && i < _places.size())
        {
            return _places.get(i);
        }
        return null;
    }
    public Airfield (int picWidth, int picHeight){
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        _maxCount = width*height;
        _places = new ArrayList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }
    public T getter(int index)
    {
        if(index > -1 && index <_places.size())
        {
            return _places.get(index);
        }
        return null;
    }
    public void setter(int index, T value){
        if(_places.size()> index && index >=0){
            _places.add(index, value);
        }
    }
    public int Plus(T plane)
    {
        if (_places.size() < _maxCount)
        {
            _places.add(plane);
            return _places.size();
        }
        return -1;
    }
    public T Minus(int index)
    {
        if (index > -1 && index < _maxCount)
        {
            T dop = _places.get(index);
            _places.remove(index);
            return dop;
        }
        return null;
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);

        int x = 30, y = 45;
        for(int i = 0; i < _places.size(); ++i)
        {
            if(i % (pictureWidth / _placeSizeWidth) == 0 && i != 0)
            {
                y += 100;
                x = 30;
            }
            if(_places.get(i) != null) {
                _places.get(i).SetPosition(x, y, 1, 1);
                _places.get(i).DrawTransport(g);
            }
            x += 320;
        }
    }
    public void DrawMarking(Graphics g)
    {
        g.setColor(Color.BLACK);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < 6; j++)
            {//линия рамзетки места
                g.drawLine( i * _placeSizeWidth+10, j * _placeSizeHeight+35, i * _placeSizeWidth + 145, j * _placeSizeHeight+35);
            }
            g.drawLine( i * _placeSizeWidth+10, 35, i * _placeSizeWidth+10, 435);
        }
    }

}

