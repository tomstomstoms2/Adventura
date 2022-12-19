package en.bent07.MyAdventura.Rooms;

import en.bent07.MyAdventura.Print;

/**
 * Class that implements BossRoom.
 * @author Tomáš Beneda
 * @version ZS2022
 */

public class BossRoom implements IRoom{
    private String name = "boss";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void getInfo() {
        new Print("You are in the Boss Room.\nYou can fight Boss or return Home.");
    }

    @Override
    public void getCommands() {

    }
}
