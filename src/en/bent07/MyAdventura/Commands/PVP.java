package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Battle;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

import java.util.Scanner;

/**
 * Class that implements PVP command, that allows fights between two player-made characters in the endgame
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class PVP implements ICommand{
    private String call = "PVP";

    @Override
    public void action(Hero hero) {
        //TODO implement TEST
        // EDIT: Just test this manually...
        if(!hero.isEndgame()){
            new Print("You can do this after you reach the end of the game!");
            return;
        }
        new Print("Which hero do you want to fight?");
        new Print("0)");
        new Print("No Fight");
        for(int i = 1; i <= hero.getHeroes().getHeroes().size(); i++){
            if(i == hero.getKey()){
                continue;
            }
            new Print(i + ")");
            hero.getHeroes().getHeroes().get(i).info();
            new Print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        Scanner input = new Scanner(System.in);
        String in;
        while(true){
            in = input.nextLine();
            int set;
            try {
                set = Integer.parseInt(in);
            }catch (Exception e){
                new Print("You need to input number.");
                continue;
            }
            if(set == 0){
                new Print("You did not fight anybody.");
                break;
            }
            if(!(set < 1 || set > hero.getHeroes().getHeroes().size())){
                if(set == hero.getKey()){
                    new Print("You can't fight yourself.");
                    new Print("You need to choose number between 0 and " + hero.getHeroes().getHeroes().size() + ", except " + hero.getKey() + ".");
                }else {
                    new Battle(hero, hero.getHeroes().getHeroes().get(set));
                    break;
                }
            }else{
                new Print("You need to choose number between 0 and " + hero.getHeroes().getHeroes().size() + ", except " + hero.getKey() + ".");
            }
        }

    }

    @Override
    public String getCall() {
        return call;
    }
}
