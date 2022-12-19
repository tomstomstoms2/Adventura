package en.bent07.MyAdventura;

import en.bent07.MyAdventura.Classes.Opponent;

/**
 * Class Battle executes instances of battles.
 * Battles are now only PVE. PVP support might be added in the future.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Battle {

    private int level;
    private String name;
    private int speed;
    private int criticalChance = 9;//critical hit once every 9(or 10?) hits


    public Battle(Hero hero1, Hero hero2){
        if(pvpFight(hero1,hero2)){
            new Print("You defeated " + hero2.getName() + "!");
        }else{
            new Print("You were defeated by " + hero2.getName() + ".");
        }

    }
    public Battle(int level, String name){
        this.level = level;
        this.name = name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Executes single attack in fights.
     * Prints info about attacks.
     * Implements criticalChance mechanic.
     * @param attacker instance of Opponent that attacks this turn
     * @param defender instance of Opponent that defends this turn
     * @return true on end of fight, false if fight continues
     */
    public boolean attack(Opponent attacker, Opponent defender){
        Num num = new Num();
        int cc = 1;
        if(num.chance(attacker.getCriticalChance())){
            new Print("Critical hit!");
            cc=2;
        }
        defender.setHealth((int)(defender.getHealth()-(attacker.getDamage()*cc)));
        new Print(attacker.getName() + " hit " + defender.getName() + " for " + (int)attacker.getDamage()*cc + " damage leaving " + defender.getName() + " on " + (int)defender.getHealth() + "HP!");
        return defender.getHealth() <= 0;
    }




    /**
     * Engine of the game's fight mechanic.
     * Initializes two instances of Opponents which fight against each other.
     * Prints stats before start of the fight.
     * Loops till one of the Opponents' HP <= 0.
     * @param heroOriginal instance of hero
     * @param multiplier added opponent strength, 3 for skeletons, 4 for bosses
     * @return true on Hero win, false on lose
     */
    public boolean fight(Hero heroOriginal, int multiplier){
        Opponent opponent = new Opponent(this.level, multiplier, this.criticalChance, this.name);
        Opponent hero = new Opponent(heroOriginal);
        new Print("You have " + (int)hero.getHealth() + "HP and you deal " + (int)hero.getDamage() + " damage with each hit.");
        new Print("The " + this.getName() + " has " + (int)opponent.getHealth() + "HP and it deals " + (int)opponent.getDamage() + " damage with each hit.");
        if(hero.getSpeed() >= opponent.getSpeed()){
            new Print("You outspeed the " + this.name + " with " + (int)hero.getSpeed() + " Speed against it's " + (int)opponent.getSpeed() + " Speed!");
        }else{
            new Print("The " + this.name + " outspeeds you with " + (int)opponent.getSpeed() + " Speed against your " + (int)hero.getSpeed() + " Speed!");
        }

        //small optimization with 1 if and 2 while loops possible
        while(true){
            if(hero.getSpeed() >= opponent.getSpeed()){
                if(attack(hero, opponent)){

                    return true;
                }
                if(attack(opponent, hero)){
                    return false;
                }
            }else {
                if(attack(opponent, hero)){
                    return false;
                }
                if(attack(hero, opponent)){
                    return true;
                }
            }
        }
    }

    public boolean pvpFight(Hero hero1, Hero hero2){
        Opponent orig = new Opponent(hero1);
        Opponent opp = new Opponent(hero2);
        new Print("You have " + (int)orig.getHealth() + "HP and you deal " + (int)orig.getDamage() + " damage with each hit.");
        new Print(opp.getName() + " has " + (int)opp.getHealth() + "HP and it deals " + (int)opp.getDamage() + " damage with each hit.");
        if(orig.getSpeed() >= opp.getSpeed()){
            new Print("You outspeed " + opp.getName() + " with " + (int)orig.getSpeed() + " Speed against it's " + (int)opp.getSpeed() + " Speed!");
        }else{
            new Print(opp.getName() + " outspeeds you with " + (int)opp.getSpeed() + " Speed against your " + (int)orig.getSpeed() + " Speed!");
        }

        while(true){
            if(orig.getSpeed() >= opp.getSpeed()){
                if(attack(orig, opp)){

                    return true;
                }
                if(attack(opp, orig)){
                    return false;
                }
            }else {
                if(attack(opp, orig)){
                    return false;
                }
                if(attack(orig, opp)){
                    return true;
                }
            }
        }
    }

}
