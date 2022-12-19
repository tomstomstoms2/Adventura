package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Battle;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Items.Oddity;
import en.bent07.MyAdventura.Num;
import en.bent07.MyAdventura.Print;
import en.bent07.MyAdventura.Rooms.EmptyRoom;

import java.util.Map;



/**
 * Implements the command Farm.
 * Usable at home/MainRoom or in any of the farm rooms (FightRoom, EmptyRoom, ChestRoom).
 * Randomly chooses between the farm rooms and executes their actions.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Farm implements ICommand{
    static final int CHANCE = 25;//percentage of something happening. 0-100
    static final double GOLD = 0.75;
    private String call = "farm";

    /**
     * Sets hero's room to EmptyRoom.
     * There is a chance to find Oddity
     * Prints EmptyRoom prompt.
     * @param hero instance of hero
     */
    public static void empty(Hero hero){
        hero.setRoom("empty");
        Num num = new Num();
        if(num.percentage(CHANCE)){
            EmptyRoom room = new EmptyRoom();
            Map<Integer, Oddity> items = room.getItems();
            Oddity item = items.get(num.rand(1, room.getItems().size()-1));
            new Print("There is something lying on the ground.");
            new Print("It's " + item.getName() + ".");
            new Print("\"" + item.getLore() + "\"");
            new Print("You can equip it by typing [equip].");

            //not sure what this for loop does, but am too scared to remove it.
            for(int i = 0; i < hero.getBag().getOwned().size(); i++){
                if(hero.getBag().getOwned().contains(item.getKey())){
                    new Print("Oh! You already have the same thing!?! It's weird enough to find one item this obscure, but two of them, IDENTICAL?");
                    if(item.getHealth() + item.getSpeed() + item.getDamage() <= 0){
                        new Print("You tried to sell this item to the shop, but it's apparently worthless.");
                    }else{
                        new Print("You tried to sell the item to the shop and got " + (((item.getHealth() + item.getSpeed() + item.getDamage()) * (5+hero.getOddityMult())) + hero.getLevel()) + " gold!" );
                        hero.addGold((((item.getHealth() + item.getSpeed() + item.getDamage()) * (5+hero.getOddityMult())) + hero.getLevel()));
                    }
                    return;
                }
            }
            if(hero.getBag().addOwned(item.getKey())){
                new Print("You put " + item.getName() + " into your bag.");
            }else{
                new Print("Something went wrong and the item was not added. That should not happen. Please contact the author.");
            }
            return;
        }
        new Print("Empty room. You should try another one.");
    }

    /**
     * Sets hero's room to ChestRoom.
     * Rolls for item drop. If successful, executes item upgrade and exits the function early.
     * Gives semi-random amount of gold to the hero, depending on hero level.
     * @param hero instance of hero
     */
    public static void chest(Hero hero){
        hero.setRoom("chest");
        new Print("You found a Chest!");
        Num num = new Num();

        if(num.chance(2)){
            //Thoroughly tested math function that calculates the chance to upgrade Ring/Necklace. 50*((5*x)/(x^3))
            if(num.percentage(50*((5*(hero.getBag().getRing().getLevel() + 1))/(Math.pow((hero.getBag().getRing().getLevel() + 1),3))))){
                hero.getBag().getRing().upgrade();
                return;
            }

        }else if(num.percentage(50*((5*(hero.getBag().getNecklace().getLevel() + 1))/(Math.pow((hero.getBag().getNecklace().getLevel() + 1),3))))) {
            hero.getBag().getNecklace().upgrade();
            return;
        }

        int tmpGold = (int)Math.round(Math.random()*10 + hero.getLevel()*GOLD);
        new Print("There was " + tmpGold + " Gold in it!");
        hero.addGold(tmpGold);
        new Print("You now have " + hero.getGold() + " Gold.");
    }

    /**
     * Sets hero's room to FightRoom.
     * Calculates semi-random level of opponent depending on hero level.
     * Initializes new opponent and starts fight with him.
     * On win gives semi-random amount of gold to the hero or upgrades hero level.
     * On lose changes hero room to home/MainRoom.
     * @param hero instance of hero
     */
    public static void fight(Hero hero){
        hero.setRoom("fight");
        new Print("You found angry Skeleton that wants to Fight!");
        int oponentLevel = Math.abs(hero.getLevel()-2+(int)((Math.random()*10)/1.5));
        Battle skeleton = new Battle(oponentLevel, "Skeleton");
        if(skeleton.fight(hero, 3)){
            new Print("You took out the Skeleton!");
            if(new Num().percentage(35)){
                new Print("You leveled up!");
                hero.upgradeLevel();
                new Print("You are now level " + hero.getLevel() + ".");
            }else{
                new Print("You got " + (int)((Math.abs(oponentLevel - 3)+1)*1.5) + " Gold!");
                hero.addGold((int)((Math.abs(oponentLevel - 3)+1)*1.5));
                new Print("You now have " + hero.getGold() + " Gold.");
            }
        }else {
            hero.addDeath();
            new Print("You got beaten by the Skeleton. \nYou returned to the Main Room.");
            hero.setRoom("home");
        }
    }



    @Override
    public void action(Hero hero) {
        if(hero.getRoom() == hero.getRoomList().findRoom("home") || hero.getRoom() == hero.getRoomList().findRoom("chest")
                || hero.getRoom() == hero.getRoomList().findRoom("empty") || hero.getRoom() == hero.getRoomList().findRoom("fight")){
            int choose = (int)((Math.random()*10)%3);
            switch (choose){
                case 0: fight(hero); break;
                case 1: chest(hero); break;
                case 2: empty(hero);
            }
        }else {
            new Print("You can use this command only in Main Room and Farm rooms.");
        }

    }

    @Override
    public String getCall() {
        return call;
    }

}
