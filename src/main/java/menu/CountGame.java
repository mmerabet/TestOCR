package menu;

import java.util.HashMap;
import java.util.Map;


public class CountGame {
    private static Map<Integer,GameMode> rowList = new HashMap<Integer, GameMode>();

    public static Map<Integer, GameMode> CountGame(int i, GameMode gameMode){
        rowList.put(i,gameMode);
        return rowList;
    }


}
