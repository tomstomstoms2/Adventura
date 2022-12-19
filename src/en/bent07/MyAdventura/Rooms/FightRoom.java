package en.bent07.MyAdventura.Rooms;

import en.bent07.MyAdventura.Print;

/**
 * Class that implements FightRoom.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class FightRoom implements IRoom{

    private String name = "fight";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void getInfo() {
        new Print("You are in the Fight Room.\nYou can Farm or return Home.");
    }

    @Override
    public void getCommands() {

    }

}
