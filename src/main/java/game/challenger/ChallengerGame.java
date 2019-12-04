package game.challenger;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;

public class ChallengerGame extends AbstractGame {


    public ChallengerGame(GameConfig gconfig) {
        super(gconfig);
    }

    @Override
    public GameResult playGame(AbstractPlayer player1, AbstractPlayer player2) {
        return null;
    }
}
