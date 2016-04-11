import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    private HashMap<String, Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {
        if(north != null)
            salidas.put("north", north);
        if(east != null)
            salidas.put("east", east);
        if(south != null)
            salidas.put("south", south);
        if(west != null)
            salidas.put("west", west);
        if (southEast != null)
            salidas.put("southEast", southEast);
        if (northWest != null)
            salidas.put("northWest", northWest);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return The exits of the room.
     */
    public Room getExit(String salida)
    {
        Room listaSalidas = null;

        if (salida.equals("north"))
            listaSalidas = salidas.get("north");
        if (salida.equals("east"))
            listaSalidas = salidas.get("east");
        if (salida.equals("south"))
            listaSalidas = salidas.get("south");
        if (salida.equals("west"))
            listaSalidas = salidas.get("west");
        if (salida.equals("southEast"))
            listaSalidas = salidas.get("southEast");
        if (salida.equals("northWest"))
            listaSalidas = salidas.get("northWest");
        return listaSalidas;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String listaSalidas = "Exits: ";
        if(salidas.get("north") != null)
            listaSalidas += " north ";
        if(salidas.get("east") != null)
            listaSalidas += "east ";
        if(salidas.get("south") != null)
            listaSalidas += "south ";
        if(salidas.get("west") != null)
            listaSalidas += "west ";
        if(salidas.get("southEast") != null)
            listaSalidas += "southEast ";
        if(salidas.get("northWest") != null)
            listaSalidas += "northWest ";
        return listaSalidas;
    }
}