package en.bent07.MyAdventura.Commands;




import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

import java.util.Map;
import java.util.HashMap;

/**
 * Main command-related class.
 * Implements and initializes HashMap with all the commands and their keys.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class CommandList {

    private Map<String, ICommand> commandMap;

    /**
     * Allows usage of shortened command forms
     */
    public CommandList(){
        commandMap = new HashMap<>();
        commandMap.put("info", new Info());
        commandMap.put("i", commandMap.get("info"));
        commandMap.put("armor", new Armor());
        commandMap.put("a", commandMap.get("armor"));
        commandMap.put("weapon", new Weapon());
        commandMap.put("w", commandMap.get("weapon"));
        commandMap.put("boss", new Boss());
        commandMap.put("b", commandMap.get("boss"));
        commandMap.put("help", new Help());
        //commandMap.put("help", commandMap.get("help"));
        commandMap.put("farm", new Farm());
        commandMap.put("f", commandMap.get("farm"));
        commandMap.put("shop", new Shop());
        commandMap.put("s", commandMap.get("shop"));
        commandMap.put("stats", new Stats());
        //commandMap.put("stats", commandMap.get("info"));
        commandMap.put("home", new Home());
        commandMap.put("h", commandMap.get("home"));
        commandMap.put("main", commandMap.get("home"));
        commandMap.put("m", commandMap.get("home"));
        commandMap.put("equip", new Equip());
        commandMap.put("eq", commandMap.get("equip"));
        commandMap.put("pvp", new PVP());
        //commandMap.put("load", new Load());
        commandMap.put("end", new End());
        commandMap.put("max", new UpgradeMax());

    }

    /**
     * Method checking if the player input command is in the HashMap.
     * @param in name of the command
     * @return true for valid commands, false for invalid
     */
    public boolean isValid(String in){
        return commandMap.containsKey(in);
    }

    /**
     *
     * @param inCase case-sensitive player input
     * @param hero instance of hero
     */
    public void performCommand(String inCase, Hero hero){
        String in = inCase.toLowerCase();
        if(isValid(in)){
            commandMap.get(in).action(hero);
        }else{
            new Print("This is not a valid command.");
        }
    }

}
