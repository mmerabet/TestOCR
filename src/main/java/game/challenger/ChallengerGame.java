package game.challenger;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;
import player.ia.IAPlayer;


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
        String proposition = IAPlayer.init(fin);
        HumanPlayer.feedback(proposition);
        if (fin == false) {
            for (int j = 1; j < maxTry; j++) {
                proposition = IAPlayer.verifCode(j, fin);
                System.out.println(proposition);
                HumanPlayer.feedback(proposition);
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
