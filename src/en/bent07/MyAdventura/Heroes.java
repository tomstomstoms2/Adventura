package en.bent07.MyAdventura;

import en.bent07.MyAdventura.Classes.Archer;
import en.bent07.MyAdventura.Classes.Fighter;
import en.bent07.MyAdventura.Classes.IClass;
import en.bent07.MyAdventura.Classes.Mage;
import en.bent07.MyAdventura.Items.Necklace;
import en.bent07.MyAdventura.Items.Ring;
import en.bent07.MyAdventura.Rooms.EmptyRoom;
import en.bent07.MyAdventura.Rooms.RoomList;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that takes care of loading and saving heroes to the text file to keep them between games.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Heroes {

    private Map<Integer, Hero>heroes = new HashMap<>();
    private BufferedReader reader;

    /**
     * Constructor that loads all saved heroes from txt database into hashmap
     * Large parts of the constructor code are based on StackOverflow answers
     * @author Stackoverflow user "Jitendra", Tomáš Beneda
     */
    public Heroes(){
        // The name of the file to open.
        String fileName = "PVP.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            reader =
                    new BufferedReader(fileReader);

            loadHeroes();

            // Always close files.
            reader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    /**
     * Method implementing loading and saving all heroes into hashmap.
     */
    public void loadHeroes(){
        while(true) {
            //System.out.println("Load heroes loop");
            Hero hero = loadHero();
            //System.out.println(hero.getKey());
            if(hero.getKey() == -1){
                System.out.println("Heroes Loaded");
                return;
            }
            if(hero.getKey() == -2){
                System.out.println("There was a problem loading heroes.");
                return;
            }
            hero.setEndgame(true);
            hero.setRoomList(new RoomList());
            hero.setRoom("home");
            heroes.put(hero.getKey(),hero);
        }
    }

    /**
     * Method reading through the PVP.txt file and loading single instance of hero
     * @return loaded single instance of hero
     */
    public Hero loadHero(){
        String line;
        Hero hero = new Hero(-2);
        try {
            line = reader.readLine();
            if(line != null && line.equalsIgnoreCase("HERO BEGIN FORMAT")){
                reader.readLine();
                reader.readLine();
                reader.readLine();
                reader.readLine();
                line = reader.readLine();
                if(line.equalsIgnoreCase("HERO END FORMAT")){
                    System.out.println("Format test successful.");
                    reader.readLine();
                }
            }else if(line == null){
                //System.out.println("load hero??");
                return new Hero(-1);
            }


            //START
            line = reader.readLine();
            //System.out.println(line);
            if(line.equals("HERO BEGIN")){
                System.out.println("Hero load begin");
            }else {
                System.out.println("There were some problems loading hero. Trying to find another valid hero.");
                while(!line.equals("HERO BEGIN")){
                    line = reader.readLine();
                    if(line == null){
                        System.out.println("EOF reached");
                        hero.setKey(-2);
                        return hero;
                    }
                }
            }

            //CLASS
            line = reader.readLine();
            //System.out.println(line);
            if(line.equalsIgnoreCase("archer")){
                IClass archer = new Archer();
                hero = new Hero(archer);
            } else if(line.equalsIgnoreCase("mage")){
                IClass mage = new Mage();
                hero = new Hero(mage);
            }else if(line.equalsIgnoreCase("fighter")){
                IClass fighter = new Fighter();
                hero = new Hero(fighter);
            }

            //ODDITIES
            EmptyRoom room = new EmptyRoom();
            line = reader.readLine();
            for(String odd : line.split(";")){
                hero.getBag().addOwned(room.getItems().get(Integer.parseInt(odd)).getKey());
            }

            //ATTRIBUTES
            line = reader.readLine();
            String[] attributes = line.split(";");
            hero.setKey(Integer.parseInt(attributes[0]));
            hero.setGold(Integer.parseInt(attributes[1]));
            hero.setWeapon(Integer.parseInt(attributes[2]));
            hero.setArmor(Integer.parseInt(attributes[3]));
            hero.setLevel(Integer.parseInt(attributes[4]));
            hero.setBossLevel(Integer.parseInt(attributes[5]));
            hero.setDeaths(Integer.parseInt(attributes[6]));
            hero.getBag().setRing(new Ring(Integer.parseInt(attributes[7])));
            hero.getBag().setNecklace(new Necklace(Integer.parseInt(attributes[8])));
            hero.getBag().setOddity(room.getItems().get(Integer.parseInt(attributes[9])).getKey());
            hero.setOddityMult(Integer.parseInt(attributes[10]));

            //NAME
            line = reader.readLine();
            hero.setName(line);

            //END
            line = reader.readLine();
            if(line.equalsIgnoreCase("HERO END")){
                System.out.println("Hero loaded successfully.");
            }
            hero.updateDamage();
            hero.updateHealth();
            hero.updateSpeed();
            hero.setActive(false);

        }catch(IOException ex) {
            System.out.println("Error reading the file while loading hero.");
        }catch(NullPointerException e){
            System.out.println("Null Pointer Exception while loading hero.");
        }
        return hero;
    }

    public Map<Integer, Hero> getHeroes(){
        return this.heroes;
    }

    /**
     * Method that writes heroes info to the PVP.txt file.
     */
    public void saveHeroes() {
        File file = null;
        try {
            file = new File("PVP.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File found");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("There was an error creating the file. The program possibly does not have the rights to create the file.");
        }
        FileWriter writerf = null;
        BufferedWriter writer = null;
        try {
            writerf = new FileWriter(file);
        } catch (Exception e) {
            System.out.println("Error assigning file.");
        }
        if (writerf == null) {
            System.out.println("Writer is null.");
        } else {
            writer = new BufferedWriter(writerf);
        }

        try {

            writer.write("HERO BEGIN FORMAT\nCLASS\nODDITY1;ODDITY2;...\nKEY;GOLD;WEAPON;ARMOR;LEVEL;bossLevel;DEATHS;RING;NECKLACE;ODDITY;ODDITYMULT\nNAME\nHERO END FORMAT\n");
            for (int i = 1; i <= heroes.size(); i++) {
                writer.write("\nHERO BEGIN\n");
                writer.write(heroes.get(i).getProfession().getName() + "\n");
                int sum = 0;
                for (Integer j : heroes.get(i).getBag().getOwned()) {
                    sum++;
                    writer.write((String.valueOf(j)));
                    if (sum < heroes.get(i).getBag().getOwned().size()) {
                        writer.write(";");
                    } else {
                        writer.write("\n");
                    }
                }
                writer.write(heroes.get(i).getKey() + ";" + heroes.get(i).getGold() + ";" + heroes.get(i).getWeapon() + ";" + heroes.get(i).getArmor() + ";" + heroes.get(i).getLevel() + ";" + heroes.get(i).getBossLevel() + ";" + heroes.get(i).getDeaths() + ";" + heroes.get(i).getBag().getRing().getLevel() + ";" + heroes.get(i).getBag().getNecklace().getLevel() + ";" + heroes.get(i).getBag().getOddity().getKey() + ";" + heroes.get(i).getOddityMult() + "\n");
                writer.write(heroes.get(i).getName() + "\n");
                writer.write("HERO END\n");
                System.out.println("Heroes saved.");
            }
        } catch (NullPointerException ex) {
            System.out.println("The file was null.");
        }catch (Exception e) {
            System.out.println("There was an error writing to file.");
        }
        try {
            writer.close();
        } catch (NullPointerException exe) {
            System.out.println("The file was null.");
        } catch (Exception exe) {
            System.out.println("There was a problem closing the file.");
        }

    }

    public void updateHero(Hero hero){
        heroes.replace(hero.getKey(), hero);
    }

    public void addHero(Hero hero){
        heroes.put(hero.getKey(), hero);
    }


}
