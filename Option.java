
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    go("andare"), quit("smettere"), help("auito"), look("guarda"), eat("mangiare"), back("indierto"),
        take("prendere"), drop("candere"), items("elementi"), UNKNOWN("UNKNOWN");
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
