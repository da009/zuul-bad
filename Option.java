
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    go("go"), quit("quit"), help("help"), look("look"), eat("eat"), back("back"),
        take("take"), drop("drop"), items("items"), UNKNOWN("UNKNOWN");
    private String command;
    
    /**
     * Constructor for objects of class Option
     */
    private Option(String command)
    {
        this.command = command;
    }
    
    public String getCommand()
    {
        return command;
    }
}
