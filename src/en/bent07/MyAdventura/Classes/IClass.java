package en.bent07.MyAdventura.Classes;

import en.bent07.MyAdventura.Hero;

/**
 * Interface setting main functions for the 3 game hero classes/professions.
 * @author Tomáš Beneda
 * @version ZS2022
 */

public interface IClass {

    public void getInfo();
    public String getName();
    //public int getDamage();
    public double getDamage();
    public double getHealth();
    public double getSpeed();

    public int updateDamage(Hero hero);
    public int updateHealth(Hero hero);
    public int updateSpeed(Hero hero);

}
