package en.bent07.MyAdventura.Rooms;

import en.bent07.MyAdventura.Print;

/**
 * Class that implements Shop room.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Shop implements IRoom{

    private String name = "shop";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void getInfo() {
        new Print("You are in the Shop.\nYou can upgrade Armor, Weapon or return Home.");
    }

    @Override
    public void getCommands() {

    }

}
