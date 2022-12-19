package en.bent07.MyAdventura;

import en.bent07.MyAdventura.Classes.*;
import en.bent07.MyAdventura.Commands.CommandList;
import en.bent07.MyAdventura.Commands.Load;
import en.bent07.MyAdventura.Rooms.RoomList;

import java.io.*;
import java.util.Scanner;
import java.lang.String;

import static java.lang.System.exit;

/**
 * Main class of the Adventure game.
 * Runs the initialization of new game instance and the core loop that executes the actions in the game.
 *
 * @author Tomáš Beneda (bent07)
 * @version ZS2022
 */
public class Start {

    private static Heroes heroes = new Heroes();
    /**
     * First lines of the game's story.
     */
    static Hero epilogue(){
        Scanner input = new Scanner(System.in);
        String in;
        new Print("You wake up in a dark, empty and cold room. You are wearing something that looks like an old rough potato sack.\n");
        new Print("Only thing with you in the room is some weird shiny stone.\nDo you want to touch it?\n");

        do {
            new Print("(type [touch] to touch the stone)");
            in = input.nextLine();
            if(in.equalsIgnoreCase("load")){
                Hero hero = new Load().load(heroes);
                if(hero.getKey() == -1){
                    new Print("There are no Heroes to load.");
                }else {
                    return hero;
                }
            }
        }while(!in.equalsIgnoreCase("touch"));

        new Print("\n*FLASH*");
        new Print("\n*BANG*\n");
        new Print("\n*FLASH*\n\n");
        new Print("Oh another one that touched the stone! Welcome!");
        return new Hero(-1);
    }

    /**
     * Class choice and initialization of new hero.
     * @return new Hero
     */
    static Hero classChoice() {
        Scanner input = new Scanner(System.in);
        String in;
        Fighter fighter = new Fighter();
        Mage mage = new Mage();
        Archer archer = new Archer();

        new Print("You need to choose a class!\n");

        new Print("You can choose one of three classes. Each class has different damage and health modifiers.");
        new Print("View info about the classes by typing *info*.");
        new Print("You can select your class by typing the name of the chosen class.");
        new Print("(fighter/mage/archer/info)");

        //class selection
        while(true){
            in = input.nextLine();
            if(in.equalsIgnoreCase("info")){
                fighter.getInfo();
                new Print("_____________________________________________\n");
                mage.getInfo();
                new Print("_____________________________________________\n");
                archer.getInfo();
                continue;
            }
            if(in.equalsIgnoreCase("fighter")){
                return new Hero(fighter);
            }
            if(in.equalsIgnoreCase("mage")){
                return new Hero(mage);
            }
            if(in.equalsIgnoreCase("archer")){
                return new Hero(archer);
            }
            new Print("(fighter/mage/archer/info)");
        }
    }

    /**
     * Initialization of armor and weapon of the newly created hero from method classChoice.
     * @param hero instance of hero
     */
    static void firstEquipment(Hero hero){
        new Print("\nLet me give you something for the start.\n");
        new Print("You obtained level 1 Armor and Weapon!\n");

        hero.upgradeArmor();
        hero.upgradeWeapon();
        new Print("Your stats are now:");
        hero.info();
    }

    /**
     * Introduction to the game's functionalities and optional guide to the whole game.
     */
    static void introduction(){
        Scanner input = new Scanner(System.in);
        String in;
        new Print("\n\nAre you familiar with how the game is played? (yes/no)");

        //skip the introduction
        while(true) {
            in = input.nextLine();
            if (in.equalsIgnoreCase("yes") || in.equalsIgnoreCase("y") || in.equalsIgnoreCase("z")) {
                new Print("Are you sure? This introduction is not available to see after you start the game. (yes/no)");
                if(new YesNo().isYes()){
                    new Print("Let's start playing then!");
                    return;
                }else{
                    break;
                }

            }
            if (!(in.equalsIgnoreCase("no") || in.equalsIgnoreCase("n"))){
                new Print("(yes/no)");
                continue;
            }
            break;
        }
        new Print("Let me introduce to the game and how you play then.");
        new Print("We are now in Main room, but I call it Home. This is the most important room of all.");
        new Print("When you are at Home, you can open Shop, go fight Boss or go Farm for resources and better gear.");
        new Print("In Boss room, you can fight strong monsters. If you kill enough of them, you WIN the game!");
        new Print("In Shop you can spend your gold to upgrade your equipment.");
        new Print("And then there are Farm rooms. There are 3 types of Farm rooms: Chest room, Fight room and Empty room.");
        new Print("In Chest room, you will find chest with gold or equipment pieces.");
        new Print("In Fight room, there will be variously difficult enemy. You need to kill these enemies to level up!");
        new Print("In Empty room, there is nothing. Or maybe there is, if you are lucky ;).");
        new Print("If you die, at any point, you will respawn back at Home.");
        new Print("The main action commands are: home/shop/farm/boss");
        new Print("If you ever feel lost and don't know what to do, type help");
        new Print("If you want information about the room you are in, type info");
        new Print("If you want to see your Hero stats, type stats");
        new Print("To manage your equipment, type equip");
        new Print("You can end the game anytime with the command end. Be cautious though, you will lose your progress unless you are in the endgame.");
        new Print("After you end the game, you will unlock the command PVP that allows you to fight with other heroes you previously won the game with.");
        new Print("\n");
        new Print("ALRIGHT, that should be everything you need to know! Let's get playing!");
    }

    /**
     * Method executing calls to all actions in the game, basically the main engine.
     * @param hero instance of hero
     */
    static void action(Hero hero){
        Scanner input = new Scanner(System.in);
        String in;
        if(hero.isPrompt()){
            new Print("\nWhat do you want to do?");
        }
        in = input.nextLine();
        hero.getCommandList().performCommand(in, hero);


    }




    /*
     ***********************************************************************************
                    Functions used solely for testing purposes.
     ***********************************************************************************
     */

    /**
     * Overloaded method to be able to input commands from file
     * @param hero instance of hero
     * @param comm command loaded from file
     */
    static void action(Hero hero, String comm){
        hero.getCommandList().performCommand(comm, hero);
    }

    /**
     * Overloaded method for testing because im lazy doing it otherwise
     * @return new Hero
     */
    static Hero classChoice(String comm) {
        String in;
        Fighter fighter = new Fighter();
        Mage mage = new Mage();
        Archer archer = new Archer();

        new Print("You need to choose a class!\n");

        new Print("You can choose one of three classes. Each class has different damage and health modifiers.");
        new Print("View info about the classes by typing *info*.");
        new Print("You can select your class by typing the name of the chosen class.");
        new Print("(fighter/mage/archer/info)");

        //class selection
        while(true){
            in = comm;
            if(in.equalsIgnoreCase("info")){
                fighter.getInfo();
                new Print("_____________________________________________\n");
                mage.getInfo();
                new Print("_____________________________________________\n");
                archer.getInfo();
                continue;
            }
            if(in.equalsIgnoreCase("fighter")){
                return new Hero(fighter);
            }
            if(in.equalsIgnoreCase("mage")){
                return new Hero(mage);
            }
            if(in.equalsIgnoreCase("archer")){
                return new Hero(archer);
            }
            new Print("(fighter/mage/archer/info)");
        }
    }

    /**
     * Testing purposes overload
     */
    static Hero epilogue(String comm){
        String in;
        new Print("You wake up in a dark, empty and cold room. You are wearing something that looks like an old rough potato sack.\n");
        new Print("Only thing with you in the room is some weird shiny stone.\nDo you want to touch it?\n");

        do {
            new Print("(type [touch] to touch the stone)");
            in = comm;
            if(in.equalsIgnoreCase("load")){
                Hero hero = new Load().load(heroes);
                if(hero.getKey() == -1){
                    new Print("There are no Heroes to load.");
                }else {
                    return hero;
                }
            }
        }while(!in.equalsIgnoreCase("touch"));

        new Print("\n*FLASH*");
        new Print("\n*BANG*\n");
        new Print("\n*FLASH*\n\n");
        new Print("Oh another one touched the stone! Welcome!");
        return new Hero(-1);
    }

    /**
     * Introduction to the game's functionalities and optional guide to the whole game.
     */
    static void introduction(String comm){
        String in;
        new Print("\n\nAre you familiar with how the game is played? (yes/no)");

        //skip the introduction
        while(true) {
            in = comm;
            if (in.equalsIgnoreCase("yes") || in.equalsIgnoreCase("y") || in.equalsIgnoreCase("z")) {
                System.out.println("You are in testing, you can't say yes.");//Test
                exit(0);//Test
                new Print("Are you sure? This introduction is not available to see after you start the game. (yes/no)");
                if(new YesNo().isYes()){
                    new Print("Let's start playing then!");
                    return;
                }else{
                    break;
                }

            }
            if (!(in.equalsIgnoreCase("no") || in.equalsIgnoreCase("n"))){
                new Print("(yes/no)");
                continue;
            }
            break;
        }
        new Print("Let me introduce you to the game and how you play then.");
        new Print("We are now in Main room, but I call it Home. This is the most important room of all.");
        new Print("When you are at Home, you can open Shop, go fight Boss or go Farm for resources and better gear.");
        new Print("In Boss room, you can fight strong monsters. If you beat lvl " + Global.getBossWinLevel() + " boss, you WIN the game!");
        new Print("In Shop you can spend your gold to upgrade your equipment.");
        new Print("And then there are Farm rooms. There are 3 types of Farm rooms: Chest room, Fight room and Empty room.");
        new Print("In Chest room, you will find chest with gold or equipment pieces.");
        new Print("In Fight room, there will be variously difficult enemy. You need to kill these enemies to level up!");
        new Print("In Empty room, there is nothing. Or maybe there is, if you are lucky ;).");
        new Print("If you die, at any point, you will respawn back at Home.");
        new Print("The main action commands are: home/shop/farm/boss");
        new Print("If you ever feel lost and don't know what to do, type help");
        new Print("If you want information about the room you are in, type info");
        new Print("If you want to see your Hero stats, type stats");
        new Print("To manage your equipment, type equip");
        new Print("You can end the game anytime with the command end. Be cautious though, you will lose your progress unless you are in the endgame.");
        new Print("After you end the game, you will unlock the command PVP that allows you to fight with other heroes you previously won the game with.");
        new Print("\n");
        new Print("ALRIGHT, that should be everything you need to know! Let's get playing!");
    }

    /**
     * Main method initializating and running the game.
     *
     * @param args Command line parameters
     */
    public static void main(String[] args) {
        //Sets delay between output messages
        if(args.length >= 1){
            try{
                int delay = Integer.parseInt(args[0]);
                if(delay >= 0){
                    Global.setDelay(delay);
                }
                System.out.println("Delay set to " + Global.getDelay());
            } catch (Exception e){
                System.out.println("You need to input number in the first parameter.");
                System.out.println("The delay was left at default value.(" + Global.getDelay() + ")");
            }
        }
        //Sets target boss level to win the game
        if(args.length >= 2){
            try{
                int bossLevel = Integer.parseInt(args[1]);
                if(bossLevel > 0){
                    if(bossLevel%5 != 0) {
                        bossLevel += 5 - (bossLevel % 5);// rounds up to multiple of 5
                    }
                    Global.setBossWinLevel(bossLevel);
                }
                System.out.println("BossLevel set to " + Global.getBossWinLevel());

            } catch (Exception e){
                System.out.println("You need to input number in the second parameter.");
                System.out.println("The boss level to win was left at default value.(" + Global.getBossWinLevel() + ")");
            }
        }
        //if statement just to be able to use overloaded functions for testing
        if(args.length >= 3){
            Global.setTest(true);
            File file = new File(args[2]);
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                Hero hero = epilogue(line);

                if(hero.getKey() == -1) {

                    line = reader.readLine();
                    hero = classChoice(line);

                    new Print("You chose " + hero.getProfession().getName() + "!");
                    new Print("Your stats are now:");
                    hero.info();

                    firstEquipment(hero);

                    line = reader.readLine();
                    introduction(line);

                    //initialization
                    hero.setCommandList(new CommandList());
                    hero.setRoomList(new RoomList());
                    hero.setRoom("home");

                    //Play until the set level of boss is beaten. Boss levels go up in a multiples of 5.
                    while (hero.getBossLevel() <= Global.getBossWinLevel()) {
                        line = reader.readLine();
                        action(hero, line);
                    }

                    //End game sequence and choice to continue playing infinitely
                    new Print("\nWoah, You killed all the bosses\n");
                    new Print(".\n.\n.\n.");
                    new Print("YOU WON THE GAME!!!!!\n\n\n\n");
                    new Print("You died a total of " + hero.getDeaths() + " times.");
                    new Print("You can end the game, or continue playing infinitely.");
                    new Print("Your hero will saved after you choose a name., so you can continue playing whenever you want, right where you stopped.");
                    new Print("To do this, just start the game and instead of touching the stone, type [load].");
                    new Print("You also unlocked option to battle other saved heroes with the command [PVP].");

                    new Print("Do you want to continue playing? (yes/no)");
                    Scanner input = new Scanner(System.in);
                    String in;
                    if (Global.isTest()) {
                        new Print("Aright, to continue playing you need to choose a Name for your Hero!");
                        new Print("The name can be up to 50 ASCII characters long.");
                        while (hero.getName().equalsIgnoreCase("DEFAULT")) {
                            in = reader.readLine();
                            if (in.length() > 50) {
                                new Print("The name can't be more than 50 characters long.");
                                continue;
                            }
                            if (!in.matches("\\A\\p{ASCII}*\\z")) {
                                new Print("Please use only ASCII characters.");
                                continue;
                            }
                            if (in.trim().length() == 0) {
                                new Print("You can't use only whitespace characters.");
                                continue;
                            }
                            hero.setName(in);
                        }

                    } else {
                        new Print("Come play again if you want! \nBye!");
                        return;
                    }

                    hero.setKey(heroes.getHeroes().size() + 1);
                    heroes.addHero(hero);
                    hero.setEndgame(true);
                }
                hero.setHeroes(heroes);
                while(!hero.isEnd()){
                    line = reader.readLine();
                    action(hero, line);
                    //Automatically saves hero after every action
                    heroes.updateHero(hero);
                }
                heroes.saveHeroes();
            } catch (FileNotFoundException e) {
                System.out.println("Test source file not found.");
                System.out.println("The game will run in non-test mode");
                Global.setTest(false);
            } catch (IOException e) {
                System.out.println(e.toString());
                System.out.println("The game will run in non-test mode");
                Global.setTest(false);
            }
        }
        //Start of non-test engine of the game
        if(!Global.isTest()){

            Hero hero = epilogue();

            if (hero.getKey() == -1) {

                hero = classChoice();

                new Print("You chose " + hero.getProfession().getName() + "!");
                new Print("Your stats are now:");
                hero.info();

                firstEquipment(hero);

                introduction();

                //initialization
                hero.setCommandList(new CommandList());
                hero.setRoomList(new RoomList());
                hero.setRoom("home");

                //Play until the set level of boss is beaten. Boss levels go up in a multiples of 5.
                while (hero.getBossLevel() <= Global.getBossWinLevel()) {
                    action(hero);
                }

                //End game sequence and choice to continue playing infinitely
                new Print("\nWoah, You killed all the bosses\n");
                new Print(".\n.\n.\n.");
                new Print("YOU WON THE GAME!!!!!\n\n\n\n");
                new Print("You died a total of " + hero.getDeaths() + " times.");
                new Print("You can end the game, or continue playing infinitely.");
                new Print("Your hero will saved after you choose a name, so you can continue playing whenever you want, right where you stopped.");
                new Print("In order to do save your hero, you have to continue playing for just a little bit.");
                new Print("To load your saved hero, just start the game and instead of touching the stone, type [load].");
                new Print("You also unlocked option to battle other saved heroes with the command [PVP].");

                new Print("Do you want to continue playing? (yes/no)");
                Scanner input = new Scanner(System.in);
                String in;
                if (!new YesNo().isYes()) {
                    new Print("Are you sure you want to end the game without saving your hero? (yes/no)");
                    if(new YesNo().isYes()){
                        new Print("Come play again if you want! \nBye!");
                        try {
                            Thread.sleep(1000);
                        }catch(Exception e){
                            System.out.println("There was a problem with waiting.");
                        }
                        return;
                    }
                }
                new Print("Aright, to save your character and continue playing you need to choose a Name for your Hero!");
                new Print("The name can be up to 50 ASCII characters long.");
                while (hero.getName().equalsIgnoreCase("DEFAULT")) {
                    in = input.nextLine();
                    if (in.length() > 50) {
                        new Print("The name can't be more than 50 characters long.");
                        continue;
                    }
                    if (!in.matches("\\A\\p{ASCII}*\\z")) {
                        new Print("Please use only ASCII characters.");
                        continue;
                    }
                    if (in.trim().length() == 0) {
                        new Print("You can't use only whitespace characters.");
                        continue;
                    }
                    new Print("The name you've chosen is \"" + in + "\"!");
                    new Print("Do you want to keep this name? (yes/no)");
                    if(new YesNo().isYes()){
                        hero.setName(in);
                        new Print("The name of your hero is \"" + in + "\"!");
                        new Print("Now you can get back to playing!");
                    }else {
                        hero.setName("DEFAULT");
                        new Print("Alright, choose a different name then.");
                    }

                }

                hero.setKey(heroes.getHeroes().size() + 1);
                heroes.addHero(hero);
                hero.setEndgame(true);
            }
            hero.setHeroes(heroes);
            while (!hero.isEnd()) {
                action(hero);
                //Automatically saves hero after every action
                heroes.updateHero(hero);
            }
            heroes.saveHeroes();
        }
    }
}