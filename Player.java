import java.util.ArrayList;
import java.util.Stack;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private ArrayList<Item> inventory;
    private int maxWeight;
    private Room currentLocation;
    private Stack<Room> ant;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(int maxWeight, Room start)
    {
        inventory = new ArrayList<Item>();
        this.maxWeight = maxWeight;
        currentLocation = start;
        ant = new Stack<>();
    }
    
    /**
     * move the player to an other location
     */
    public void  move(Room room)
    {
        currentLocation = room;
    }
    
    /**
     * Set the player location
     */
    public void setLocalizacion(Room location)
    {
        currentLocation = location;
    }
    
    /**
     * Returns the currentLocation
     */
    public Room getCurrentLocation()
    {
        return currentLocation;
    }
    
    /**
     * Return the last location for this player
     */
    public Room getLastRoom()
    {
        return ant.pop();
    }
    
    /**
     * returns if the player have a valid location before
     */
    public boolean isEmpty()
    {
        return ant.empty();
    }
    
    /**
     * adds the previous room
     */
    public void addLastRoom()
    {
        ant.push(currentLocation);
    }

    /**
     * Add the item to the inventory
     */
    public void catchItem(Item obj)
    {
        inventory.add(obj);
        reduceMaxWeight(obj.getPeso());
    }
    
    /**
     * Drop the item to the inventory 
     */
    public void dropItem(Item obj)
    {
        addMaxWeight(obj.getPeso());
        inventory.remove(obj);
    }
    
    /**
     * return the maximum weight for the player
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
    
    public void reduceMaxWeight(int pesoItem)
    {
        maxWeight = maxWeight - pesoItem;
    }
    
    public void addMaxWeight(int pesoItem)
    {
        maxWeight = maxWeight + pesoItem;
    }
    
    /**
     * Search the objet in the ArrayList
     */
    public boolean searchItem(String descripcionObj)
    {
        int cont = 0;
        boolean found = false;
        while (cont < inventory.size() && !found)
        {
            if (inventory.get(cont).getDespription().equals(descripcionObj))
                found = true;
            else
            {
                found = false;
            }
            cont ++;
        }
        return found;
    }
    
        /**
     * Search the objet in the ArrayList
     */
    public Item searchItemRetItem(String descripcionObj)
    {
        int cont = 0;
        boolean found = false;
        while (cont < inventory.size() && !found)
        {
            if (inventory.get(cont).getDespription().equals(descripcionObj))
                found = true;
            else
            {
                System.out.println("The Item not exist");
            }
            cont ++;
        }
        return inventory.get(cont-1);
    }
    
    /**
     * Show the items in the inventory
     */
    public void showItems()
    {
        System.out.println("You have the next item/s: ");
        for (Item item : inventory)
        {
            System.out.println(item.getDespription() + ", " + item.getPeso());
        }
    }
}
