package game.duel;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;

public class DuelGame extends AbstractGame {

    public DuelGame(GameConfig gconfig) {
        super(gconfig);
    }

    /**
     * Les joueurs sont à la fois attaquant et defenseur.
     * @param player1
     * @param player2
     * @return
     */
    @Override
    public GameResult playGame(AbstractPlayer player1, AbstractPlayer player2) {
        return GameResult.DRAW;
    }
}
