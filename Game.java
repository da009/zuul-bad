import java.util.Stack;

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
    private Room currentRoom;
    private Stack<Room> ant;
    private Room antHab;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game(int maxWeight) 
    {
        createRooms();
        parser = new Parser();
        ant = new Stack<>();
        player = new Player(maxWeight);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room hallDelHotel, pasillo, habitacion2, habitacion3, tuHabitacion, wc, comedor;

        // create the rooms
        hallDelHotel = new Room("main entrance");
        hallDelHotel.addItem("objPrueba1", 1, true);

        pasillo = new Room("hall rooms");
        pasillo.addItem("objPrueba2", 3, false);

        habitacion2 = new Room("room number 3, there isn't your room");
        habitacion2.addItem("objPrueba3", 5, true);

        habitacion3 = new Room("room number 3, there isn't your room");
        habitacion3.addItem("objPrueba4", 10, true);

        tuHabitacion = new Room("your room");
        tuHabitacion.addItem("objPrueba5", 7, true);

        wc = new Room("your bathroom");
        wc.addItem("objPrueba6", 1, true);

        comedor = new Room("dinningroom");
        comedor.addItem("objPrueba7", 30, true);

        // initialise room exits
        // norte,   este,   sur,    oeste,  sureste,    noroeste
        // hallDelHotel.setExits(null, pasillo, null, null, comedor, null);
        hallDelHotel.setExits("east", pasillo);
        hallDelHotel.setExits("southEast", comedor);
        //pasillo.setExits(habitacion2, tuHabitacion, habitacion3, hallDelHotel, null, null);
        pasillo.setExits("north", habitacion2);
        pasillo.setExits("east", tuHabitacion);
        pasillo.setExits("south", habitacion3);
        pasillo.setExits("west", hallDelHotel);
        // habitacion2.setExits(null, null, pasillo, null, null, null);
        habitacion2.setExits("south", pasillo);
        // habitacion3.setExits(pasillo, null, null, null, null, null);
        habitacion3.setExits("north", pasillo);
        // tuHabitacion.setExits(null, null, wc, pasillo, null, null);
        tuHabitacion.setExits("south", wc);
        tuHabitacion.setExits("west", pasillo);
        // wc.setExits(tuHabitacion, null, null, null, null, null);
        wc.setExits("north", tuHabitacion);
        // comedor.setExits(pasillo, null, null, null, null, hallDelHotel);
        comedor.setExits("north", pasillo);
        comedor.setExits("northWest", hallDelHotel);

        currentRoom = hallDelHotel;  // start game outside
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
            if (currentRoom.getDescription().equals("in your bathroom"))
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
        System.out.println("Type 'help' if you need help.");
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
        if (commandWord.equals(Option.help)) {
            printHelp();
        }
        else if (commandWord.equals(Option.go)) {
            goRoom(command);
        }
        else if (commandWord.equals(Option.quit)) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals(Option.look))
            System.out.println(currentRoom.getLongDescription());
        else if (commandWord.equals(Option.eat))
            System.out.println("You have eaten now and you are not hungry any more");
        else if (commandWord.equals(Option.take))
            addInventory(command);
        else if (commandWord.equals(Option.drop))
            removeInventory(command);
            else if (commandWord.equals(Option.items))
            player.showItems();
        else if (commandWord.equals(Option.back)) {
            if (!ant.empty())
            {
                currentRoom = ant.pop();
                printLocationInfo();
            }
            else
                System.out.println("Is not posible return to the location before this");
        }
        return wantToQuit;
    }

    // implementations of user commands:

    private void addInventory(Command command)
    {
        int pesoObj = currentRoom.searchItem(command.getSecondWord()).getPeso();
        String descriptionObj = currentRoom.searchItem(command.getSecondWord()).getDespription();
        if(!command.hasSecondWord()) {
            System.out.println("What do you want to catch?");
            return;
        }
        if (currentRoom.searchItem(command.getSecondWord()).itsPosible())
        {
            if(player.getMaxWeight() >= pesoObj)
            {
                player.catchItem(currentRoom.searchItem(command.getSecondWord()));
                currentRoom.removeItem(descriptionObj);
                System.out.println("Object taken");
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
            currentRoom.addItem(descriptionObj, pesoObj, true);
            player.dropItem(player.searchItemRetItem(command.getSecondWord()));
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
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            ant.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
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
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
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
