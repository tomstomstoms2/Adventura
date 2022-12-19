package en.bent07.MyAdventura.Items;

/**
 * Interface setting main methods for the Items.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public interface IItem {
    public String getName();
    public String toString();
    public int getHealth();
    public int getDamage();
    public int getLevel();
    public void upgrade();
    public int getSpeed();

}
