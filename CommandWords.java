import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    // "go", "quit", "help", "look", "eat", "back", "take", "drop", "items"
    private HashMap<String, Option> validCommandsHM;
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        Option[] options = Option.values();
        validCommandsHM = new HashMap<>();
        for(Option option : options) {
            validCommandsHM.put(option.getCommand(), option);
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommandsHM.size(); i++) {
            if(validCommandsHM.keySet().equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String commList = "Your command words are:";
        for(String command : validCommandsHM.keySet()) {
            commList += "\n" + command;
        }
        System.out.print(commList);
    }

    public Option getCommandWord(String commandWord)
    {
        Option option = validCommandsHM.get(commandWord);
        if (option == null)
            option = Option.UNKNOWN;
        return option;
    }
}
