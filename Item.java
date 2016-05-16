import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String descripcionObj;
    private int pesoObj;
    private boolean posible;
    private ArrayList<String> objNames;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso, boolean posible)
    {
        descripcionObj = descripcion;
        pesoObj = peso;
        this.posible = posible;
    }
    
    public void setItemToMap()
    {
        descripcionObj = "Map";
        pesoObj = 1;
        posible = true;
    }
    
    /**
     * Random constructor for objects of class Item
     */
    public Item()
    {
        Random rnd = new Random();
        int val = rnd.nextInt(7);
        
        descripcionObj = names();
        pesoObj = val;
        posible = genRandomBoolean();
    }
    
    /**
     * ArrayList to the objects names
     */
    public String names()
    {
        objNames = new ArrayList<String>();
        objNames.add("plant");
        objNames.add("table");
        objNames.add("chair");
        objNames.add("carpet");
        objNames.add("lamp");
        objNames.add("bag");
        objNames.add("backpack");
        return objNames.get(genRandom());
    }
    
    /**
     * Generate a random boolean
     */
    public boolean genRandomBoolean()
    {
        return Math.random() < 0.5;
    }
    
    /**
     * Generate a random number contains in the interval of 0, 6
     */
    public int genRandom()
    {
        Random rnd = new Random();
        int val = rnd.nextInt(7);
        return val;
    }
    
    /**
     * Print the item description
     */
    public String getItemDescription()
    {
        String objDescription = "\n";
        objDescription += descripcionObj + "\n" + "Pesa: " + pesoObj + "kg";
        return objDescription;
    }
    
    public String getDespription()
    {
        return descripcionObj;
    }
    
    public int getPeso()
    {
        return pesoObj;
    }
    
    public boolean itsPosible()
    {
        return posible;
    }
}
