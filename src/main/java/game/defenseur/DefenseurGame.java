package game.defenseur;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;

public class DefenseurGame extends AbstractGame {

    public DefenseurGame(GameConfig gconfig) {
        super(gconfig);
    }

    @Override
    public GameResult playGame(AbstractPlayer defenseur, AbstractPlayer attaquant) {
        return GameResult.PLAYER1_WIN;
    }
}
