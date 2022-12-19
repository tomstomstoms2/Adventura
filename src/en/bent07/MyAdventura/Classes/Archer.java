package en.bent07.MyAdventura.Classes;


import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Class that implements Archer profession.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Archer implements IClass{

    private String name = "Archer";
    private double damage = 1.5;
    private double health = 1.5;
    private double speed = 1.2;



    public void getInfo(){
        new Print("Name: " + this.name);
        new Print("Stats:\nDamage x" + this.damage + "\nHealth x" + this.health);
        //archer was too weak, buffing him
        new Print("Special: Archer can critical strike almost two times as often as other classes.");
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
        return (int)((5 * this.getSpeed()) + (int)(hero.getLevel()*this.getSpeed()));
    }
}
