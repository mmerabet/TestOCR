package game.challenger;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;

public class ChallengerGame extends AbstractGame {

    public ChallengerGame(GameConfig gconfig) {
        super(gconfig);
    }

    /**
     * Décrit comment le mode Challenger fonctionne entre les deux joueurs.
     * @param player1
     * @param player2
     * @return
     */
    @Override
    public GameResult playGame(AbstractPlayer attaquant, AbstractPlayer defenseur) {
        //essaye de remettre ton do while dans ta classe VerifcodeAuto méthode Init
        return GameResult.PLAYER1_WIN;
    }
}
