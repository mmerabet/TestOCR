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

        /** 1ere etape on demande à l'humain une combinaison */
        String code = HumanPlayer.askConbinaison();

       /** 2eme etape dès que nous l'avons, l'algo cherche celle-ci */
        boolean fin =false;
        int maxTry = getGconfig().getTryNum();
        List<String> retour = IAPlayer.init(fin, code);
        String proposition = retour.get(0);
        String userFeedback = retour.get(1);
        /** demande au joueur de vérifier ce que l'ordi a trouvé*/
        HumanPlayer.feedback(proposition, Collections.singletonList(userFeedback));

        /** Fonctionne bien jusqu'ici */

        /** 3eme etape, on rentre dans la boucle*/
        if (fin == false) {
            for (int j = 1; j < maxTry; j++) {
                proposition = IAPlayer.verifCode(j, fin, code);
                HumanPlayer.feedback(proposition, Collections.singletonList(userFeedback));
                if (fin == true) {
                    break;
                } else if (j == maxTry - 1) {
                    System.out.println("\n--------------------------------------------\n" +
                            "L'ordinateur n'a pas pu cracker votre code.\nFélicitations !!! ");
                }
            }
        } else {
            System.out.println("L'ordinateur à gagné du premier coup !");
        }
        return null;
    }
}