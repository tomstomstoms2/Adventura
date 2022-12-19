import en.bent07.MyAdventura.Classes.Archer;
import en.bent07.MyAdventura.Classes.Fighter;
import en.bent07.MyAdventura.Classes.Mage;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Rooms.EmptyRoom;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {

    Archer archer = new Archer();
    Mage mage = new Mage();
    Fighter fighter = new Fighter();
    Hero heroA = new Hero(archer);
    Hero heroM = new Hero(mage);
    Hero heroF = new Hero(fighter);
    EmptyRoom oddities = new EmptyRoom();



    @org.junit.jupiter.api.Test
    public void bag(){
        heroA.getBag().addOwned(oddities.getItems().get(1).getKey());
        assertEquals(2,heroA.getBag().getOwned().size());
        heroA.getBag().addOwned(oddities.getItems().get(2).getKey());
        assertEquals(3,heroA.getBag().getOwned().size());

        heroA.getBag().getNecklace().upgrade();
        assertEquals(1, heroA.getBag().getNecklace().getLevel());
        heroA.getBag().getRing().upgrade();
        assertEquals(1, heroA.getBag().getRing().getLevel());
        heroA.getBag().getRing().upgrade();
        heroA.getBag().getRing().upgrade();
        heroA.getBag().getRing().upgrade();
        assertEquals(4, heroA.getBag().getRing().getLevel());

        heroA.getBag().setOddity(1);
        assertEquals(15,heroA.getBag().getDamage(heroA));

    }

    @org.junit.jupiter.api.Test
    public void speed(){
        assertEquals(7, heroA.getSpeed());
        assertEquals(6, heroM.getSpeed());
        assertEquals(6, heroF.getSpeed());

        heroA.getBag().addOwned(oddities.getItems().get(10).getKey());
        heroA.getBag().setOddity(10);
        assertEquals(6, heroA.getBag().getOddity().getSpeed());
        assertEquals(13, heroA.getSpeed());
        heroA.setLevel(100);
        assertEquals((100 + 5) * 1.2 + 6, heroA.getSpeed());

        heroM.setLevel(100);
        assertEquals(105, heroM.getSpeed());
        heroM.setWeapon(21);
        assertEquals(105-4*3, heroM.getSpeed());
        heroM.setArmor(55);
        assertEquals(105, heroM.getSpeed());
    }

    @org.junit.jupiter.api.Test
    public void stats(){
        heroM.setLevel(5);
        heroM.setWeapon(1);
        heroM.setArmor(1);

        assertEquals(35, heroM.getDamage());
        assertEquals(16, heroM.getHealth());

        heroM.setWeapon(10);
        heroM.setArmor(10);
        assertEquals(55, heroM.getDamage());
        assertEquals(25, heroM.getHealth());
    }


    @org.junit.jupiter.api.Test
    public void commands(){
    }

    @BeforeEach
    public void beforeEach(){
        archer = new Archer();
        mage = new Mage();
        fighter = new Fighter();
        heroA = new Hero(archer);
        heroM = new Hero(mage);
        heroF = new Hero(fighter);
        oddities = new EmptyRoom();
    }
}
