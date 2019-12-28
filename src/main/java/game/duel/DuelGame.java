package game.duel;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;
import player.ia.IAPlayer;

import java.util.Collections;
import java.util.List;

public class DuelGame extends AbstractGame {

    public DuelGame(GameConfig gconfig) {
        super(gconfig);
    }

    /**
     * Les joueurs sont à la fois attaquant et defenseur.
     * @param iaPlayer
     * @param HumanPlayer
     * @return
     */
    @Override
    public GameResult playGame(AbstractPlayer iaPlayer, AbstractPlayer HumanPlayer) {

        /** on demande le code aux différentes parties*/
        String codeOfHuman = HumanPlayer.askConbinaison();
        String codeOfIA = iaPlayer.askConbinaison();
        Integer i = 2;
        Integer maxTry = getGconfig().getTryNum();
        int j = 1;
        int size = codeOfHuman.length();
        if (getGconfig().getDevMod() == true){
            System.out.println("DevModeActivé:"+codeOfIA);
        }
        /**Les deux parties cherchent à craquer le code*/
        List<String> retour = IAPlayer.init(codeOfHuman, size);
        String propositionIa = retour.get(0);
        String userFeedback = retour.get(1);
        System.out.println("Vous devez cracker un code long de " + getGconfig().getSizeCombi() + " caractères avant la machine.\nNombre d'essais : " + i + "/" + maxTry + ".");
        String proposition = HumanPlayer.askConbinaison();

        /**Vérification de ce qu'elles ont rentré*/
        HumanPlayer.feedback(propositionIa, Collections.singletonList(userFeedback));
        String userFeedbacks = iaPlayer.feedback(proposition, Collections.singletonList(userFeedback));

        /** Premet de savoir si la proposition correspond aux codes*/
        if (isFeedbackWin(userFeedback)) {
            System.out.println("Perdu !!!\nLa machine a craqué votre code en " + j + " tours !\n--------------------------------------------------------------------");
            return GameResult.PLAYER1_WIN;
        } if (isFeedbackWin(userFeedbacks)){
            System.out.println("Gagné !!!\nLe code était bien " + codeOfIA + "\n--------------------------------------------------------------------");
            return GameResult.PLAYER2_WIN;
        } else {
            System.out.println("Mauvaise combinaison :/");
            System.out.println("Voici le rendu de votre proposition : " + userFeedbacks
                    + "\n--------------------------------------------------------------------");
            i++;
            if (i == maxTry - 1) {
                System.out.println("Ceci est votre dernier essai." +
                        "\n--------------------------------------------------------------------");
            }
        }

        while (i <= maxTry) {
            System.out.println("Vous devez cracker un code long de " + getGconfig().getSizeCombi() + " caractères avant la machine.\nNombre d'essais : " + i + "/" + maxTry + ".");
            proposition = HumanPlayer.askConbinaison();
            userFeedbacks = iaPlayer.feedback(proposition, Collections.singletonList(userFeedback));

            retour = IAPlayer.verifCode(codeOfHuman, size);
            proposition = retour.get(0);
            userFeedback = retour.get(1);
            HumanPlayer.feedback(proposition, Collections.singletonList(userFeedback));

            if (isFeedbackWin(userFeedbacks)){
                System.out.println("Gagné !!!\nLe code était bien " + codeOfIA + "\n--------------------------------------------------------------------");
                return GameResult.PLAYER2_WIN;
            } else {
                System.out.println("Mauvaise combinaison :/");
                System.out.println("Voici le rendu de votre proposition : " + userFeedbacks
                        + "\n--------------------------------------------------------------------");
                i++;
                if (i == maxTry - 1) {
                    System.out.println("Ceci est votre dernier essai." +
                            "\n--------------------------------------------------------------------");
                }
            }
            if (isFeedbackWin(userFeedback)) {
                System.out.println("Perdu !!!\nLa machine a craqué votre code " + j + " tours !\n--------------------------------------------------------------------");
                return GameResult.PLAYER1_WIN;
            } else if (j == maxTry - 1) {
                System.out.println("\n--------------------------------------------\n" +
                        "L'ordinateur n'a pas pu cracker votre code.\nFélicitations !!! ");
                return GameResult.PLAYER2_WIN;

            }
        }
        return GameResult.PLAYER1_WIN;
    }

}
