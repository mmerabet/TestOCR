package game;

import configuration.GameConfig;
import player.AbstractPlayer;

/**
 * Classe parent de tout type de jeu qui va contenir la logique des régles du jeu.
 */
public abstract class AbstractGame {

    //ici en gros, un jeu a forcement une configuration, on le force.
    private final GameConfig gconfig;

    //contructeur qui va initialiser un jeu avec la config
    public AbstractGame(GameConfig gconfig) {
        this.gconfig = gconfig;
    }


    //ici on lance une partie entre deux joueurs (IA et humain)
    public abstract GameResult playGame(AbstractPlayer player1, AbstractPlayer player2) throws InterruptedException;

    public GameConfig getGconfig() {
        return gconfig;
    }

    public Boolean isFeedbackWin(String feedback){
        for (char c:feedback.toCharArray()){
            if (c != '='){
                return false;
            }
        }
        return true;
    }
}