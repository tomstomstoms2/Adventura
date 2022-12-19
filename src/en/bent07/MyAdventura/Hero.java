package en.bent07.MyAdventura;

import en.bent07.MyAdventura.Classes.IClass;
import en.bent07.MyAdventura.Commands.CommandList;
import en.bent07.MyAdventura.Rooms.IRoom;
import en.bent07.MyAdventura.Rooms.RoomList;

/**
 * Class Hero implements hero that the player is playing as.
 * It holds most of the crucial info about the state of the game and the hero itself.
 * Initializes instances of RoomList CommandList and the hero.
 * @author Tomáš Beneda
 * @version ZS2022
 */

public class Hero {

    private IClass profession;
    private int health;
    private int damage;
    private int gold;
    private int weapon;
    private int armor;
    private int level;
    private IRoom room;
    private int bossLevel;
    private boolean prompt;
    private CommandList commandList;
    private RoomList roomList;
    private int criticalChance = 7; //1 from 7 attacks are critical, lower is stronger
    private int deaths;
    private int speed;
    private Bag bag;
    private int key;
    private String name = "DEFAULT";
    private boolean end = false;
    private boolean endgame = false;
    private Heroes heroes;
    private int oddityMult;
    private boolean active = true;


    public Hero(int key){
        this.key = key;
    }

    /**
     * Constructor of the Hero class called from the main function.
     * @param profession Class of hero
     */
    public Hero(IClass profession) {
        this.profession = profession;
        this.level = 1;
        this.health = (int)Math.round(10 * profession.getHealth());
        this.damage = (int)Math.round(10 * profession.getDamage());
        this.gold = 0;
        this.weapon = 0;
        this.armor = 0;
        this.bossLevel = 5;
        this.deaths = 0;
        //archer buff
        if(profession.getName().equalsIgnoreCase("archer")){
            this.criticalChance = 4;
        }
        //not used in the game
        this.prompt = true;
        this.bag = new Bag();
        this.speed = (int)((5*profession.getSpeed()) + this.level);
        this.getBag().addOwned(0);
        this.oddityMult = 1;
    }

    //GETTERS AND SETTERS
    public IClass getProfession(){
        return profession;
    }
    public void setProfession(IClass profession){
        this.profession = profession;
    }

    public int getHealth() {
        return health + getBag().getHealth(this);
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage + getBag().getDamage(this);
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed + getBag().getSpeed(this);
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Heroes getHeroes() {
        return heroes;
    }

    public void setHeroes(Heroes heroes) {
        this.heroes = heroes;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isEndgame() {
        return endgame;
    }

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getWeapon() {
        return weapon;
    }
    public void setWeapon(int weapon) {
        this.weapon = weapon;
        updateHealth();
        updateDamage();
        updateSpeed();
    }

    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        this.armor = armor;
        updateHealth();
        updateDamage();
        updateSpeed();
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        updateHealth();
        updateDamage();
        updateSpeed();
    }

    public IRoom getRoom() {
        return room;
    }
    public void setRoom(String room){
        this.room = this.getRoomList().findRoom(room);
    }

    public int getBossLevel() {
        return bossLevel;
    }

    public void setBossLevel(int bossLevel) {
        this.bossLevel = bossLevel;
    }

    public void setPrompt(boolean prompt) {
        this.prompt = prompt;
    }

    public boolean isPrompt() {
        return prompt;
    }

    public CommandList getCommandList() {
        return commandList;
    }

    public void setCommandList(CommandList commandList) {
        this.commandList = commandList;
    }

    public RoomList getRoomList() {
        return roomList;
    }

    public void setRoomList(RoomList roomList) {
        this.roomList = roomList;
    }

    public int getCriticalChance(){
        return this.criticalChance;
    }

    public int getDeaths(){
        return this.deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public Bag getBag(){
        return bag;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOddityMult() {
        return oddityMult;
    }

    public void setOddityMult(int oddityMult) {
        this.oddityMult = oddityMult;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //Functions

    /**
     * Prints the main info about hero
     */
    public void info(){
        new Print("Class: " + this.getProfession().getName());
        new Print("Deaths: " + this.getDeaths());
        new Print("Level: " + this.getLevel());
        new Print("Health: " + this.getHealth());
        new Print("Damage: " + this.getDamage());
        new Print("Gold: " + this.getGold());
        new Print("Weapon level: " + this.getWeapon());
        new Print("Armor level: " + this.getArmor());
        new Print("Speed: " + this.getSpeed());
        new Print("Oddity stats Multiplier: " + this.getOddityMult());
    }

    /**
     * Calculations for hero's health
     */
    public void updateHealth(){
        this.health = this.getProfession().updateHealth(this);
        //this.health = (int)Math.round((10 + this.getArmor() + this.getLevel()) * this.getProfession().getHealth());
    }

    /**
     * Calculations for hero's damage
     */
    public void updateDamage(){
        this.damage = this.getProfession().updateDamage(this);
        //this.damage = (int)Math.round((10 + this.getWeapon() + this.getLevel()) * this.getProfession().getDamage());
    }

    public void updateSpeed(){
        this.speed = this.getProfession().updateSpeed(this);
        /*
        this.speed = (int)((5 * this.getProfession().getSpeed()) + (int)(this.getLevel()*this.getProfession().getSpeed()));
        if(this.getProfession().getName().equals("Mage") && (this.getWeapon()-this.getArmor()) > 0){
            this.speed -= (int)(((this.getWeapon()-this.getArmor())/5)*3);//speed lowers by 3 for every 5lvl difference between weapon and armor
        }

         */
    }

    public void upgradeArmor(){
        this.armor = this.getArmor() + 1;
        updateHealth();
        updateSpeed();
        updateDamage();
    }
    public void upgradeWeapon(){
        this.weapon = this.getWeapon() + 1;
        updateDamage();
        updateSpeed();
        updateHealth();
    }
    public void upgradeLevel() {
        this.level = this.getLevel() + 1;
        updateHealth();
        updateDamage();
        updateSpeed();
    }
    public void addGold(int gold){
        this.gold = this.getGold() + gold;
    }

    public void updateBossLevel(){
        this.bossLevel += 5;
    }

    public void addDeath(){
        this.setDeaths(this.getDeaths()+1);
    }

    public void upgradeOddityMult(){
        this.oddityMult++;
    }

}
