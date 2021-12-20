import java.util.*;
import java.util.ArrayList;

public class AirfieldCollection {
    final HashMap<String, Airfield<Plane, Radar>> airfieldStages = new HashMap();
    private final int pictureHeight;
    private final int pictureWidth;
    public ArrayList<String> Keys()
    {
        return new ArrayList<>(airfieldStages.keySet());
    }

    public AirfieldCollection(int pictureHeight, int pictureWidth)
    {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public void AddAirfield(String name) {
        if (!airfieldStages.containsKey(name)) {
            airfieldStages.put(name, new Airfield<Plane, Radar>(pictureWidth, pictureHeight));
        }
    }

    public void DeleteAirfield(String name) {
        if(airfieldStages.containsKey(name))
        {
            airfieldStages.remove(name);
        }
    }

    public Airfield<Plane, Radar> get(String name){
        if(airfieldStages.containsKey(name)){
            return airfieldStages.get(name);
        }
        return null;
    }
    public Plane getAirfieldAndPlane(String name, int planeIndex)
    {
        if(airfieldStages.containsKey(name) && airfieldStages.get(planeIndex) != null)
        {
            return airfieldStages.get(name).getPlane(planeIndex);
        }
        return null;
    }
}
