import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import game.challenger.ChallengerGame;
import game.defenseur.DefenseurGame;
import game.duel.DuelGame;
import jdk.swing.interop.DropTargetContextWrapper;
import menu.*;
import player.AbstractPlayer;
import player.human.HumanPlayer;
import player.ia.IAPlayer;


public class Main {

    public static void main(String[] args) throws InterruptedException {



        boolean b = false;
        int i=1;
        /**
         * 1ere étape
         * On charge la configuration du jeu.
         */
        GameConfig gameConfig = GameConfig.build();

        /**
         * 2eme étape
         * On affiche les modes.
         * On demande au joueur le mode de jeu qu'il souhaite jouer.
         */
        do {
            ChoixMode.afficherModes();
            GameMode gameMode = ChoixMode.demanderMode();

            /**
             * 3eme étape
             * On initialise le jeu en fonction du choix de l'utilisateur.
             */
            AbstractGame game = null;
            switch (gameMode) {
                case DEFENSEUR:
                    game = new DefenseurGame(gameConfig);
                    break;
                case CHALLENGER:
                    game = new ChallengerGame(gameConfig);
                    break;
                case DUEL:
                    game = new DuelGame(gameConfig);
                    break;
            }

            /**
             * 4eme étape
             * On initialise deux joueurs: un HumanPLayer et un IAPlayer
             * On execute le jeu car maintenant on connait la config et le mode de jeu souhaité.
             * On donne à result la valeur retournée par les différents mode de jeu
             */

            AbstractPlayer player1 = new IAPlayer(gameConfig.getSizeCombi());
            AbstractPlayer player2 = new HumanPlayer(gameConfig.getSizeCombi());
            GameResult result = game.playGame(player1, player2);

            /**
             * 5eme étape
             * Fin du jeu, on demande à l'utilisateur si il souhaite rejouer ou quitter
             * On ajoute une partie à l'historique des parties en précisant le mode de jeu et le résultat.
             * On demande à l'user s'il veut regarder le tableau des scores
             */
            CountGame.CountGame(gameMode, result);
            Encore.askReboot();
            ChoixEncore choixEncore = Encore.reboot();
            switch (choixEncore) {
                case O:
                    b = true;
                    i++;
                    break;
                case N:
                    b = false;
                    break;
            }
            ChoixEncore choixTableau = CountGame.scoreboard();
            switch (choixTableau) {
                case O:
                    CountGame.showMe();
                    break;
                case N:
                    break;
            }


        } while (b == true);

    }
}
