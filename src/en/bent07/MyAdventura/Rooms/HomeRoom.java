package en.bent07.MyAdventura.Rooms;

import en.bent07.MyAdventura.Print;

/**
 * Class that implements HomeRoom.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class HomeRoom implements IRoom{

    private String name = "home";


    public String getName() {
        return name;
    }

    public void getInfo (){
        new Print("You are in the Main Room.\nYou can open Shop, challenge Boss or go Farm for gold and better equipment.");
    }

    public void getCommands() {
    }

}
