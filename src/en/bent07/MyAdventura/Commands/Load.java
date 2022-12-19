package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Heroes;
import en.bent07.MyAdventura.Print;
import en.bent07.MyAdventura.Rooms.RoomList;

import java.util.Scanner;

/**
 * Class implementing command load, managing selecting and properly initializing heroes loaded from previous games
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Load implements ICommand{
    //TODO implement TEST
    // EDIT: I'd rather not
    public String call = "load";

    @Override
    public void action(Hero hero) {
        new Print("You shouldn't be able to access this. Please contact the author.");
    }

    //show heroes to load
    //return selected hero
    public Hero load(Heroes heroes){
        Hero hero;
        if(heroes.getHeroes().size() == 0){
            new Print("There are no Heroes to load. Starting new game.");
            return new Hero(-1);
        }
        new Print("Which hero do you want to load? (number)");
        for(int i = 1; i <= heroes.getHeroes().size(); i++){
            new Print(i + ")");
            new Print("Name: " + heroes.getHeroes().get(i).getName());
            hero = new Hero(heroes.getHeroes().get(i).getProfession());
            hero.getBag().setOddity(heroes.getHeroes().get(i).getBag().getOddity().getKey());
            heroes.getHeroes().get(i).info();
            new Print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        Scanner input = new Scanner(System.in);
        String in;
        int set = 0;
        while(set == 0) {
            in = input.nextLine();
            try {
                set = Integer.parseInt(in);
            } catch (Exception e) {
                new Print("You need to input number from 1 to " + heroes.getHeroes().size() + ".");
                continue;
            }
            if(set < 1 || set > heroes.getHeroes().size()){
                new Print("You need to input number from 1 to " + heroes.getHeroes().size() + ".");
                set = 0;
            }
        }
        heroes.getHeroes().get(set).info();
        heroes.getHeroes().get(set).setCommandList(new CommandList());
        heroes.getHeroes().get(set).setRoomList(new RoomList());
        heroes.getHeroes().get(set).setRoom("home");
        heroes.getHeroes().get(set).setActive(true);

        return heroes.getHeroes().get(set);
    }

    @Override
    public String getCall() {
        return call;
    }
}
