package en.bent07.MyAdventura.Classes;

import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Creates instances of opponents for the Fight mechanic of the game.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Opponent implements IClass{

    private String name;
    private int damage;
    private int health;
    private double speed;
    private int criticalChance;


    public Opponent(int level, int multiplier, int cc, String name){
        if(level <= 0){
            level = 1;
        }
        this.name = name;
        this.damage = level*multiplier;
        this.health = level*multiplier*2;
        if(level < 4){
            this.speed = level;
        }else {
            this.speed = (int)((level+4)*((double)multiplier/3));//4 derived from battle level calculation, multiplier/3 to buff bosses with multiplier 4 - normal opponents have multiplier 3
        }
        this.criticalChance = cc;
    }

    public Opponent(Hero hero){
        if(hero.isActive()){
            this.name = "You";
        }else {
            this.name = hero.getName();
        }

        this.damage = hero.getDamage();
        this.health = hero.getHealth();
        this.speed = hero.getSpeed();
        this.criticalChance = hero.getCriticalChance();
    }

    public void getInfo(){
        new Print("Name: " + this.name);
        new Print("Stats:\nDamage x" + this.damage + "\nHealth x" + this.health);
    }

    public String getName(){
        return this.name;
    }

    public double getDamage(){
        return this.damage;
    }

    public double getHealth(){
        return this.health;
    }

    public double getSpeed(){
        return this.speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCriticalChance(){
        return this.criticalChance;
    }



    /********************************************
    *Never Called functions
    ********************************************/
    @Override
    public int updateDamage(Hero hero) {
        return -1;
    }
    @Override
    public int updateHealth(Hero hero) {
        return -1;
    }
    @Override
    public int updateSpeed(Hero hero) {
        return -1;
    }
}
