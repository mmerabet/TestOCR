package game.challenger;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;
import player.ia.IAPlayer;
import java.util.Collections;
import java.util.List;


public class ChallengerGame extends AbstractGame {


    public ChallengerGame(GameConfig gconfig) {
        super(gconfig);
    }


    @Override
    public GameResult playGame(AbstractPlayer attaquant, AbstractPlayer HumanPlayer) {
        GameResult result = null;

        /** 1ere etape on demande à l'humain une combinaison */
        String code = HumanPlayer.askConbinaison();
        System.out.println(code);
        /** 2eme etape dès que nous l'avons, l'algo cherche celle-ci */

        int j = 1;
        int size = code.length();
        int maxTry = getGconfig().getTryNum();
        List<String >retour = IAPlayer.init(code, size);
        String proposition = retour.get(0);
        String userFeedback = retour.get(1);

        /** demande au joueur de vérifier ce que l'ordi a trouvé   */
        HumanPlayer.feedback(proposition, Collections.singletonList(userFeedback), code);
        if (isFeedbackWin(userFeedback)) {
            System.out.println("Perdu !!!\nLa machine a craqué votre code en " + j + " tours !\n--------------------------------------------------------------------");
            result = GameResult.PLAYER1_WIN;
        }

        /** 3eme etape, on rentre dans la boucle problème sur la lecture de la prop*/
        else {
            for (j = 2/**remettre j à 2 lorsque le bug du retour sur la première itération sera corrigé*/; j < maxTry; j++) {
                retour = IAPlayer.verifCode(code, size);
                proposition = retour.get(0);
                userFeedback = retour.get(1);
                HumanPlayer.feedback(proposition, Collections.singletonList(userFeedback), code);
                if (isFeedbackWin(userFeedback)) {
                    System.out.println("Perdu !!!\nLa machine a craqué votre code " + j + " tours !\n--------------------------------------------------------------------");
                    result = GameResult.PLAYER1_WIN;
                    break;
                } else if (j == maxTry - 1) {
                    System.out.println("\n--------------------------------------------\n" +
                            "L'ordinateur n'a pas pu cracker votre code.\nFélicitations !!! ");
                    result = GameResult.PLAYER2_WIN;
                    break;

                }
            }
        }
        return result;
    }
}