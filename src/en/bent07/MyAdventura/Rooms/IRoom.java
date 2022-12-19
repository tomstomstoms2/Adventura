package en.bent07.MyAdventura.Rooms;

/**
 * Interface setting main methods for the game Rooms.
 * @author Tomáš Beneda
 * @version ZS2022
 */

public interface IRoom {

    public String getName();
    public void getInfo();

    //scrapped functionality solved differently in the game
    public void getCommands();


}
