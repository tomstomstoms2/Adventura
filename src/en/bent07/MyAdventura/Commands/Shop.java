package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Implements the command Shop.
 * Usable at home/MainRoom, "opens" the shop.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Shop implements ICommand{

    private String call = "shop";
    @Override
    public void action(Hero hero) {
        if(hero.getRoom() == hero.getRoomList().findRoom("home")){
            hero.setRoom("shop");
            new Print("You opened Shop.");
        }else {
            if(hero.getRoom() == hero.getRoomList().findRoom("shop")){
                new Print("You already are in the shop.");
            }else {
                new Print("You can use this command only in Main Room.");
            }
        }

    }

    @Override
    public String getCall() {
        return call;
    }

}
