package en.bent07.MyAdventura.Rooms;

import java.util.HashMap;
import java.util.Map;

/**
 * Main room-related class.
 * Implements and initializes HashMap with all the rooms and their keys.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class RoomList {

    private Map<String, IRoom> roomMap;

    public RoomList(){
        roomMap = new HashMap<>();
        roomMap.put("home", new HomeRoom());
        roomMap.put("boss", new BossRoom());
        roomMap.put("shop", new Shop());
        roomMap.put("fight", new FightRoom());
        roomMap.put("chest", new ChestRoom());
        roomMap.put("empty", new EmptyRoom());
    }


    public IRoom findRoom(String roomName){
        return roomMap.get(roomName);
    }

}
