import java.util.ArrayList;

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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northWestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if (southEast != null)
            southEastExit = southEast;
        if (northWest != null)
            northWestExit = northWest;
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
        Room salidas = null;

        if (salida.equals("north"))
            salidas = northExit;
        if (salida.equals("east"))
            salidas = eastExit;
        if (salida.equals("south"))
            salidas = southExit;
        if (salida.equals("west"))
            salidas = westExit;
        if (salida.equals("southEast"))
            salidas = southEastExit;
        if (salida.equals("northWest"))
            salidas = northWestExit;
        return salidas;
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
        if(northExit != null)
            listaSalidas += " north ";
        if(eastExit != null)
            listaSalidas += "east ";
        if(southExit != null)
            listaSalidas += "south ";
        if(westExit != null)
            listaSalidas += "west ";
        if(southEastExit != null)
            listaSalidas += "southEast ";
        if(northWestExit != null)
            listaSalidas += "northWest ";
        return listaSalidas;
    }
}