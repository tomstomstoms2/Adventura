package en.bent07.MyAdventura.Items;

import en.bent07.MyAdventura.Print;

/**
 * Implements instance of Ring item and its upgrade function
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Ring implements IItem{
    private String name;
    private int health;
    private int damage;
    private int level;
    private int speed;


    public Ring(int level){
        this.name = "Ring";
        this.level = level;
        this.damage = 0;
        for(int i = 0; i <= level; i++){
            this.damage += i;
        }
        this.health = this.damage;
        this.speed = level;
    }
    public Ring(String name, int health, int damage, int level, int speed){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.level = level;
        this.speed = speed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public int getSpeed(){
        return speed;
    }

    /**
     *Updates Item stats on levelup
     */
    public void upgrade(){
        new Print("There was a Ring!");
        this.level = this.level + 1;
        this.health = this.health + this.level;
        this.damage = this.damage + this.level;
        this.speed += 1;
        new Print("You now have level " + this.getLevel() + " Ring!");
    }
}
