package en.bent07.MyAdventura.Rooms;

import en.bent07.MyAdventura.Print;

/**
 * Class that implements ChestRoom.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class ChestRoom implements IRoom{

    private String name = "chest";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void getInfo() {
        new Print("You are in the Chest Room.\nYou can Farm or return Home.");
    }

    @Override
    public void getCommands() {

    }

}
