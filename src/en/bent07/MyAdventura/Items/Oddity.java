package en.bent07.MyAdventura.Items;


import en.bent07.MyAdventura.Print;

/**
 * Creates instances of oddities.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Oddity implements IItem{
    private String name;
    private String lore;
    private int health;
    private int damage;
    private int level;
    private int speed;
    private int key;


    public Oddity(String name, int health, int damage, int level, String lore, int speed, int key){
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.level = level;
        this.lore = lore;
        this.speed = speed;
        this.key = key;
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

    public void upgrade(){
        new Print("There are no upgrades to Oddities.");
    }

    public String getLore(){
        return lore;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
