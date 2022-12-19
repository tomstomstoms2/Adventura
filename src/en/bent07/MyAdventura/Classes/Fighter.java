package en.bent07.MyAdventura.Classes;


import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Class that implements Fighter profession.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Fighter implements IClass{

    private String name = "Fighter";
    private int damage = 1;
    private double health = 2.5;
    private double speed = 1;
    private double damagePunish = 0.02;



    public void getInfo(){
        new Print("Name: " + this.name);
        new Print("Stats:\nDamage x" + this.damage + "\nHealth x" + this.health);
        new Print("Special: Fighter will deal 2% less damage for every level of his armor that is greater than his weapon level, starting at 5 level difference.");
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
        double dmg = (int)Math.round((10 + hero.getWeapon() + hero.getLevel()) * this.getDamage());
        if(((1-(((hero.getArmor()-hero.getWeapon())+5)*this.damagePunish))*100)%10 == 0 && hero.getArmor() - hero.getWeapon() > 5){
            new Print("If your armor is more than 5 levels higher than your weapon, for every level you will lose 2% of your damage.");
            new Print("You are now dealing " + (1-(((hero.getArmor()-hero.getWeapon())-5)*this.damagePunish))*100 + "% your normal damage.");
        }
        //For every level of armor that is higher than level of weapon+5 lower total damage by 2% up to 90% reduction
        if(hero.getArmor() - hero.getWeapon() <= 5){
            return (int)dmg;
        }else if(1-(((hero.getArmor()-hero.getWeapon())-5)*this.damagePunish) <= 0.1){
            return (int)(dmg * 0.1);
        }else {
            return (int)(dmg * (1-(((hero.getArmor()-hero.getWeapon())-5)*this.damagePunish)));
        }
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
