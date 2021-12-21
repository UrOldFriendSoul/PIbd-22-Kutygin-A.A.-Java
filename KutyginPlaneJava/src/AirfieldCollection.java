import java.util.*;
import java.util.ArrayList;
import java.io.*;
public class AirfieldCollection {
    final HashMap<String, Airfield<Plane, Radar>> airfieldStages = new HashMap();
    Plane plane;
    private final int pictureHeight;
    private final int pictureWidth;
    private String separator = ":";
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
    public boolean SaveData (File fileName)
    {
        if(fileName.exists())
        {
            fileName.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            bw.write("AirfieldCollection" + System.lineSeparator());
            for(int i = 0; i< airfieldStages.size(); i++)
            {
                Airfield<Plane, Radar> airfield = airfieldStages.get(Keys().get(i));
                bw.write("Airfield" + separator + Keys().get(i) + System.lineSeparator());
                ITransport vehicle = null;
                for(int j = 0; (vehicle = airfield.getPlane(j)) != null; j++)
                {
                    if(vehicle != null)
                    {
                        if(vehicle.getClass().getSimpleName().equals("Plane"))
                        {
                            Plane plane = (Plane) vehicle;
                            bw.write("Plane" + separator);
                            bw.write(plane.getPlaneConfig() + System.lineSeparator());
                        }
                        if(vehicle.getClass().getSimpleName().equals("RadarPlane"))
                        {
                            RadarPlane radarPlane = (RadarPlane) vehicle;
                            bw.write("RadarPlane" + separator);
                            bw.write(radarPlane.GetRadarPlaneConfig() + System.lineSeparator());
                        }
                    }
                }
            }
            return true;
        }
        catch(IOException ex)
        {
            return false;
        }
    }

    public boolean LoadData (File fileName)
    {
        if(!fileName.exists())
        {
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            if((line = br.readLine()) != null)
            {
                if(line.contains("AirfieldCollection"))
                {
                    airfieldStages.clear();
                }
                else
                {
                    return false;
                }
                Plane vehicle = null;
                String key = "";
                while((line = br.readLine()) != null)
                {
                    if(line.contains("Airfield"))
                    {
                        key = line.split(String.valueOf(separator))[1];
                        airfieldStages.put(key, new Airfield<Plane, Radar>(pictureWidth, pictureHeight));
                        continue;
                    }
                    if(line.isEmpty())
                    {
                        continue;
                    }
                    if(Objects.equals(line.split(String.valueOf(separator))[0], "Plane"))
                    {
                        vehicle = new Plane(line.split(String.valueOf(separator))[1]);
                    }
                    else if (Objects.equals(line.split(String.valueOf(separator))[0], "RadarPlane"))
                    {
                        vehicle = new RadarPlane(line.split(String.valueOf(separator))[1]);
                    }
                    int res = airfieldStages.get(key).Plus(vehicle);
                    if(res == -1)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        catch(IOException ex)
        {
            return false;
        }
    }

    public boolean SaveAirfield (File fileName, String airfieldName)
    {
        if(!airfieldStages.containsKey(airfieldName))
        {
            return false;
        }
        if(fileName.exists())
        {
            fileName.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            Airfield<Plane, Radar> airfield = airfieldStages.get(airfieldName);
            bw.write("Airfield" + separator + airfieldName + System.lineSeparator());
            ITransport vehicle = null;
            for(int j = 0; (vehicle = airfield.getPlane(j)) != null; j++)
            {
                if(vehicle != null)
                {
                    if(vehicle.getClass().getSimpleName().equals("Plane"))
                    {
                        Plane plane = (Plane) vehicle;
                        bw.write("Plane" + separator);
                        bw.write(plane.getPlaneConfig() + System.lineSeparator());
                    }
                    if(vehicle.getClass().getSimpleName().equals("RadarPlane"))
                    {
                        RadarPlane radarPlane = (RadarPlane)vehicle;
                        bw.write("RadarPlane" + separator);
                        bw.write(radarPlane.GetRadarPlaneConfig() + System.lineSeparator());
                    }
                }
            }
            return true;
        }
        catch(IOException ex)
        {
            return false;
        }
    }

    public boolean LoadAirfield (File fileName)
    {
        if(!fileName.exists())
        {
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            if((line = br.readLine()) != null)
            {
                if(!line.contains("Airfield" + String.valueOf(separator)))
                {
                    return false;
                }
                else
                {
                    Plane vehicle = null;
                    String key = "";
                    key = line.split(String.valueOf(separator))[1];
                    if(airfieldStages.containsKey(key))
                    {
                        airfieldStages.get(key).clear();
                    }
                    else
                    {
                        airfieldStages.put(key, new Airfield<>(pictureWidth,pictureHeight));
                    }
                    while((line = br.readLine()) != null)
                    {
                        if(Objects.equals(line.split(String.valueOf(separator))[0], "Plane"))
                        {
                            vehicle = new Plane(line.split(String.valueOf(separator))[1]);
                        }
                        else if (Objects.equals(line.split(String.valueOf(separator))[0], "RadarPlane"))
                        {
                            vehicle = new RadarPlane(line.split(String.valueOf(separator))[1]);
                        }
                        int res = airfieldStages.get(key).Plus(vehicle);
                        if(res == -1)
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        catch(IOException ex)
        {
            return false;
        }
    }
}
