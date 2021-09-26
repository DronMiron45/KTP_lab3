import java.util.*;

public class AStarState 
{
	private Map2D map;
    private Map<Location, Waypoint> openPoints = new HashMap<Location, Waypoint>();
    private Map<Location, Waypoint> closePoints = new HashMap<Location, Waypoint>();
    
    public AStarState(Map2D map) 
    {
        if (map == null)
        throw new NullPointerException("map cannot be null");
        this.map = map;
    }
    
    public Map2D getMap() 
    {
        return map;
    }
    
    public int numOpenWaypoints() 
    {
        return openPoints.values().size();
    }
    
    public Waypoint getMinOpenWaypoint() 
    {
        float min = Float.MAX_VALUE;
        Waypoint minWaypoint = null;
        for (Waypoint waypoint : openPoints.values()) {
            if (waypoint.getTotalCost() < min) {
                min = waypoint.getTotalCost();
                minWaypoint = waypoint;
            }
        }
        return minWaypoint;
    }
    
    public boolean addOpenWaypoint(Waypoint newWP) 
    {
        if (!openPoints.containsKey(newWP.getLocation())) 
        {
            openPoints.put(newWP.loc, newWP);
            return true;
        } 
        	else
        	if (openPoints.get(newWP.getLocation()).getPreviousCost() > newWP.getPreviousCost()) 
        	{
        		openPoints.put(newWP.getLocation(), newWP);
        	}
        return false;
    }
    
    public boolean isLocationClosed(Location loc)
    {
        return closePoints.containsKey(loc);
    }
    
    public void closeWaypoint(Location loc) 
    {
        if (openPoints.containsKey(loc)) 
        {
            Waypoint waypoint = openPoints.get(loc);
            openPoints.remove(loc);
            closePoints.put(loc, waypoint);
        }
    }
}
