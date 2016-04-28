
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

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso, boolean posible)
    {
        descripcionObj = descripcion;
        pesoObj = peso;
        this.posible = posible;
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
