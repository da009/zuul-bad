import java.util.ArrayList;

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
    /**
     * Constructor for objects of class Player
     */
    public Player(int maxWeight)
    {
        inventory = new ArrayList<Item>();
        this.maxWeight = maxWeight;
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
        while (cont <= inventory.size() && !found)
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
        while (cont <= inventory.size() && !found)
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
