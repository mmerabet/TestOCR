import configuration.ConfigReader;
import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import game.challenger.ChallengerGame;
import player.AbstractPlayer;
import player.human.HumanPlayer;
import player.ia.IAPlayer;

public class Main {
    public static void main(String[] args) {
        /**
         * Ici on lit une configuration
         */
        GameConfig gameConfig = new ConfigReader().read();

        /**
         * Ici, il faut créer le bon mode de jeu en fonction du choix de l'utilisateur.
         */
        AbstractGame game = new ChallengerGame(gameConfig);

        AbstractPlayer player1 = new HumanPlayer(gameConfig.getSizeCombi());
        AbstractPlayer player2 = new IAPlayer(gameConfig.getSizeCombi());
        GameResult gameResult = game.playGame(player1, player2);

    }
}
