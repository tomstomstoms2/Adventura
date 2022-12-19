package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.*;


/**
 * Implements the command Boss.
 * Usable at home/MainRoom, asks player for confirmation, then starts fight with Boss.
 * Prints the results of the battle.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Boss implements ICommand{

    private String call = "boss";

    @Override
    public void action(Hero hero) {
        if(hero.getRoom() == hero.getRoomList().findRoom("home")){
            new Print("You are facing level " + hero.getBossLevel() + " Boss. Do you want to fight it? (yes/no)");
            //TEST MODE
            if(Global.isTest()){
                Battle boss = new Battle(hero.getBossLevel(), "Boss");
                if(boss.fight(hero, 4)){
                    new Print("Congratulations, you defeated lvl" + hero.getBossLevel() + " Boss!");
                    hero.updateBossLevel();
                    new Print("You earned " + Math.round(hero.getBossLevel()*3) + " Gold!");
                    hero.addGold(Math.round(hero.getBossLevel()*3));
                    new Print("You leveled up!");
                    hero.upgradeLevel();
                    new Print("You returned back to Main Room.");
                    hero.setRoom("home");
                }else {
                    hero.addDeath();
                    new Print("You were defeated by lvl" + hero.getBossLevel() + " Boss.");
                    new Print("You returned to the Main Room.");
                    hero.setRoom("home");
                }
            }else if(new YesNo().isYes()){
                Battle boss = new Battle(hero.getBossLevel(), "Boss");
                if(boss.fight(hero, 4)){
                    new Print("Congratulations, you defeated lvl" + hero.getBossLevel() + " Boss!");
                    hero.updateBossLevel();
                    new Print("You earned " + Math.round(hero.getBossLevel()*3) + " Gold!");
                    hero.addGold(Math.round(hero.getBossLevel()*3));
                    new Print("You leveled up!");
                    hero.upgradeLevel();
                    new Print("You returned back to Main Room.");
                    hero.setRoom("home");
                }else {
                    hero.addDeath();
                    new Print("You were defeated by lvl" + hero.getBossLevel() + " Boss.");
                    new Print("You returned to the Main Room.");
                    hero.setRoom("home");
                }
            }else {
                new Print("You returned to the Main Room.");
                hero.setRoom("home");
            }
        }else{
            new Print("You can use this command only in Main Room.");
        }
    }

    @Override
    public String getCall() {
        return call;
    }

}
