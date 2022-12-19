package en.bent07.MyAdventura.Classes;

import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Class that implements Mage profession.
 * @author Tomáš Beneda
 * @version ZS2022
 */

public class Mage implements IClass{

    private final String name = "Mage";
    private final double damage = 2.4;
    private final int health = 1;
    private double speed = 1;


    public void getInfo(){
        new Print("Name: " + name);
        new Print("Stats:\nDamage x" + damage + "\nHealth x" + health);
        //Mage was too strong, nerfing him
        new Print("Special: If levels of Mages weapon is greater than armor, he will become slower and slower, the bigger difference in levels there is.");
    }

    public String getName(){
        return name;
    }

    public double getDamage(){
        return damage;
    }

    public double getHealth(){
        return health;
    }

    public double getSpeed(){
        return this.speed;
    }

    @Override
    public int updateDamage(Hero hero) {
        return (int)Math.round((10 + hero.getWeapon() + hero.getLevel()) * this.getDamage());
    }

    @Override
    public int updateHealth(Hero hero) {
        return (int)Math.round((10 + hero.getArmor() + hero.getLevel()) * this.getHealth());
    }

    @Override
    public int updateSpeed(Hero hero) {
        int speed = (int)((5 * this.getSpeed()) + (int)(hero.getLevel()*this.getSpeed()));

        //speed lowers by 3 for every 5lvls that weapon is higher than armor
        if (hero.getWeapon() - hero.getArmor() >= 5) {
            speed -= (((hero.getWeapon() - hero.getArmor()) / 5) * 3);
        }
        return speed;
    }
}
