
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

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso)
    {
        descripcionObj = descripcion;
        pesoObj = peso;
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
}
