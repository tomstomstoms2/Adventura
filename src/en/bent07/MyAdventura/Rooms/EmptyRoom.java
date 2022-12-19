package en.bent07.MyAdventura.Rooms;

import en.bent07.MyAdventura.Items.Oddity;
import en.bent07.MyAdventura.Print;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that implements EmptyRoom and initializes all unique oddities.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class EmptyRoom implements IRoom{

    private String name = "empty";
    private Map<Integer, Oddity> items;

    public EmptyRoom(){
        items = new HashMap<>();
        items.put(0, new Oddity("No Oddity", 0, 0, 0, "", 0, 0));
        items.put(1, new Oddity("Rusty Dagger", 0, 4, 1, "Can skeletons die by infection?", 0,1));
        items.put(2, new Oddity("Ominous Diary", -2, 6, 1, "You feel murderous rage when reading it. Much like when you had to read Babička.", 0,2));
        items.put(3, new Oddity("Battered Crown", 4, 0, 1, "Kings are generally hard to kill, right?", 0,3));
        items.put(4, new Oddity("Empty Barrel", 8, -2, 1, "You can hide in it, but swing a sword? Not really.", -2,4));
        items.put(5, new Oddity("Dirty Cloth", 1, 0, 1, "It helps a little, but fresh one would be better.", 0,5));
        items.put(6, new Oddity("Message in a bottle", 0, 2, 1, "It's gibberish. Though you can probably hit someone with the bottle.", 0,6));
        items.put(7, new Oddity("Broken Mirror", -1, -1, 1, "13 years of bad luck? Maybe. Will it cut you when you attack too furiously? Definitely.", -1,7));
        items.put(8, new Oddity("Trinity Force", 2, 2, 1, "Tons of damage.", 2,8));
        items.put(9, new Oddity("Fake Jordans", 0, 0, 1, "They may be fake, but you can still flex them on skeletons.", 4,9));
        items.put(10, new Oddity("Ancient Walking Cane", 0, -2, 1, "With this, it's always flight and never fight.", 6,10));
        //items.put(11, new Oddity("Glass Cannon", , -2, 1, "With this, it's always flight and never fight.", 6));

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void getInfo() {
        new Print("You are in Empty Room.\nYou can Farm or return Home.");
    }

    @Override
    public void getCommands() {

    }

    public Map<Integer, Oddity> getItems(){
        return items;
    }


}
