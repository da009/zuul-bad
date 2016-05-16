
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room antHab;
    private Player player;
    private String listExits;

    /**
     * Create the game and initialise its internal map.
     */
    public Game(int maxWeight) 
    {
        createRooms(maxWeight);
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(int maxWeight)
    {
        Room hallDelHotel, pasillo, habitacion2, habitacion3, tuHabitacion, wc, comedor;

        // create the rooms
        hallDelHotel = new Room("main entrance");
        hallDelHotel.addRndItem();

        pasillo = new Room("hall rooms");
        pasillo.addRndItem();

        habitacion2 = new Room("room number 2, there isn't your room");
        habitacion2.addMap(); // here it's the map.

        habitacion3 = new Room("room number 3, there isn't your room");
        habitacion3.addRndItem();

        tuHabitacion = new Room("your room");
        tuHabitacion.addRndItem();

        wc = new Room("your bathroom");
        wc.addRndItem();

        comedor = new Room("dinningroom");
        comedor.addRndItem();

        listExits = "";
        // initialise room exits
        // norte,   este,   sur,    oeste,  sureste,    noroeste
        // hallDelHotel.setExits(null, pasillo, null, null, comedor, null);
        hallDelHotel.setExits("east", pasillo);
        listExits += "east, pasillo, ";
        hallDelHotel.setExits("southEast", comedor);
        listExits += "southEast, comedor. \n\n";
        //pasillo.setExits(habitacion2, tuHabitacion, habitacion3, hallDelHotel, null, null);
        pasillo.setExits("north", habitacion2);
        listExits += "north, habitacion2, ";
        pasillo.setExits("east", tuHabitacion);
        listExits += "east, tuHabitacion, ";
        pasillo.setExits("south", habitacion3);
        listExits += "south, habitacion3, ";
        pasillo.setExits("west", hallDelHotel);
        listExits += "west, hallDelHotel. \n\n";
        // habitacion2.setExits(null, null, pasillo, null, null, null);
        habitacion2.setExits("south", pasillo);
        listExits += "south, pasillo. \n\n";
        // habitacion3.setExits(pasillo, null, null, null, null, null);
        habitacion3.setExits("north", pasillo);
        listExits += "north, pasillo. \n\n";
        // tuHabitacion.setExits(null, null, wc, pasillo, null, null);
        tuHabitacion.setExits("south", wc);
        listExits += "south, wc, ";
        tuHabitacion.setExits("west", pasillo);
        listExits += "west, pasillo. \n\n";
        // wc.setExits(tuHabitacion, null, null, null, null, null);
        wc.setExits("north", tuHabitacion);
        listExits += "north, tuHabitacion. \n\n";
        // comedor.setExits(pasillo, null, null, null, null, hallDelHotel);
        comedor.setExits("north", pasillo);
        listExits += "north, pasillo, ";
        comedor.setExits("northWest", hallDelHotel);
        listExits += "northWest, hallDelHotel. \n\n";

        player = new Player(maxWeight, hallDelHotel);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.getCurrentLocation().getDescription().equals("in your bathroom"))
            {
                finished = true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type " + Option.help.getCommand() + " if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch (commandWord){
            case help:
            printHelp();
            break;

            case go:
            goRoom(command);
            break;

            case quit:
            wantToQuit = quit(command);
            break;

            case look:
            System.out.println(player.getCurrentLocation().getLongDescription());
            break;

            case eat:
            System.out.println("You have eaten now and you are not hungry any more");
            break;

            case take:
            addInventory(command);
            break;

            case drop:
            removeInventory(command);
            break;

            case items:
            player.showItems();
            break;
        }

        if (commandWord.equals(Option.back)) {
            if (!player.isEmpty())
            {
                player.move(player.getLastRoom());
                System.out.println(player.getCurrentLocation().getLongDescription());
            }
            else
                System.out.println("Is not posible return to the location before this");
        }
        return wantToQuit;
    }

    /**
     * Print the rooms with the exits
     */
    private void printMap()
    {
        System.out.println(listExits);
    }

    // implementations of user commands:

    private void addInventory(Command command)
    {
        int pesoObj = player.getCurrentLocation().searchItem(command.getSecondWord()).getPeso();
        String descriptionObj = player.getCurrentLocation().searchItem(command.getSecondWord()).getDespription();
        if(!command.hasSecondWord()) {
            System.out.println("What do you want to catch?");
            return;
        }
        if (player.getCurrentLocation().searchItem(command.getSecondWord()).itsPosible())
        {
            if(player.getMaxWeight() >= pesoObj)
            {
                player.catchItem(player.getCurrentLocation().searchItem(command.getSecondWord()));
                player.getCurrentLocation().removeItem(descriptionObj);
                System.out.println("Object taken");
                if (descriptionObj.equals("Map"))
                {
                    printMap();
                }
            }
            else if (player.getMaxWeight() < pesoObj)
                System.out.println("Your body feels too heavy as take this object");
        }
        else
            System.out.println("This object can not be taken");
    }

    private void removeInventory(Command command)
    {
        int pesoObj = player.searchItemRetItem(command.getSecondWord()).getPeso();
        String descriptionObj = player.searchItemRetItem(command.getSecondWord()).getDespription();
        if(!command.hasSecondWord()) {
            System.out.println("What do you want to catch?");
            return;
        }
        else if(player.searchItemRetItem(descriptionObj).getDespription().equals(descriptionObj))
        {
            player.getCurrentLocation().addItem(descriptionObj, pesoObj, true);
            player.dropItem(player.searchItemRetItem(command.getSecondWord()));
            System.out.println("Object dropped");
        }
        else if(!player.searchItemRetItem(descriptionObj).getDespription().equals(descriptionObj))
            System.out.println("You can't drop an item which isn't in your inventori");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        player.addLastRoom();
        // Try to leave current room.
        Room nextRoom = player.getCurrentLocation().getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            player.move(nextRoom);
            System.out.println(player.getCurrentLocation().getLongDescription());
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        getValidsCommands();
        System.out.println();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }

    private void printLocationInfo()
    {
        System.out.println(player.getCurrentLocation().getLongDescription());
        System.out.println();
    }

    /**
     * Print all valids commands
     */
    public void getValidsCommands()
    {
        parser.getCommands();
    }
}
