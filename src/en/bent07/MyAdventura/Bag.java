package en.bent07.MyAdventura;

import en.bent07.MyAdventura.Items.Necklace;
import en.bent07.MyAdventura.Items.Oddity;
import en.bent07.MyAdventura.Items.Ring;
import en.bent07.MyAdventura.Rooms.EmptyRoom;

import java.util.HashSet;
import java.util.Set;

/**
 * Implements Bag mechanic for Hero.
 * Stores 3 types of items.
 * Implements basis of Oddity management.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Bag {
    private Ring ring;
    private Necklace necklace;
    private int oddity = 0;
    private Set<Integer> owned;

    private EmptyRoom room = new EmptyRoom();

    public Bag(){
        this.ring = new Ring("Ring", 0, 0, 0, 0);
        this.necklace = new Necklace("Necklace", 0 ,0, 0, 0);
        this.oddity = 0;
        owned = new HashSet<>();
    }

    public Ring getRing() {
        return ring;
    }

    public Necklace getNecklace() {
        return necklace;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public void setNecklace(Necklace necklace) {
        this.necklace = necklace;
    }

    public Oddity getOddity() {
        return room.getItems().get(this.oddity);
    }
    public void setOddity(int oddity){
        this.oddity = oddity;
    }

    public int getHealth(Hero hero){
        return getRing().getHealth() + getNecklace().getHealth() + getOddity().getHealth()*hero.getOddityMult();
    }

    public int getDamage(Hero hero){
        return getRing().getDamage() + getNecklace().getDamage() + getOddity().getDamage()*hero.getOddityMult();
    }

    public int getSpeed(Hero hero){
        return getRing().getSpeed() + getNecklace().getSpeed() + getOddity().getSpeed()*hero.getOddityMult();
    }

    public Set<Integer> getOwned(){
        return owned;
    }

    /**
     * Manages adding newfound items to the owned hashmap
     * THIS METHOD CAN ONLY GET UNIQUE ITEMS, IT DOES NOT CHECK FOR IDENTICAL ITEMS ITSELF
     * @param item - UNIQUE item to be added to owned hashmap
     * @return true on successful addition to owned hashmap, false if the key is already in hashmap.
     */
    public boolean addOwned(int item){
        return owned.add(item);
    }

    public void ownedToString(Hero hero){
        for(Integer i : owned){
            new Print(i + ": " + room.getItems().get(i).getName() + ", " + room.getItems().get(i).getHealth()*hero.getOddityMult() + " Health, " + room.getItems().get(i).getDamage()*hero.getOddityMult() + " Damage, " + room.getItems().get(i).getSpeed()*hero.getOddityMult() + " Speed");
        }
    }

}
