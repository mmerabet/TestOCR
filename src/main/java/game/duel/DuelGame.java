package game.duel;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;
import player.ia.IAPlayer;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public GameResult playGame(AbstractPlayer iaPlayer, AbstractPlayer HumanPlayer) throws InterruptedException {
        GameResult result = null;

        /** on demande le code aux différentes parties*/
        System.out.println("Tour du joueur humain, afin de défier la machine, veuillez suivre les instructions suivantes : ");

        String codeOfHuman = HumanPlayer.askConbinaison(); // ici le code entré par le joueur en debut de partie, ce code sera soumis à l'algorithme de résolution automatique
        String codeOfIA = iaPlayer.askConbinaison(); // ici, le code créé automatiquement par la machine
        Integer i = 1;
        Integer maxTry = getGconfig().getTryNum();
        int j = 1;
        int size = codeOfHuman.length();
        if (getGconfig().getDevMod() == true){
            System.out.println("DevModeActivé:"+codeOfIA);
        }
        /**Les deux parties cherchent à craquer le code tour à tour*/

       /** L'IA , initialisation*/
        System.out.println("Tour de l'IA");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("...\n");
        TimeUnit.SECONDS.sleep(1);
        /**  */
        List<String> retour = IAPlayer.init(codeOfHuman, size);
        String propositionIa = retour.get(0);
        String userFeedback = retour.get(1);

        /** L'humain, premier input */
        System.out.println("Tour de l'humain\nVous devez trouver la solution d'un code long de " + getGconfig().getSizeCombi() + " caractères avant la machine.\nNombre d'essais : " + i + "/" + maxTry + ".");
        String propositionHu = HumanPlayer.askConbinaison();

        System.out.println("test");     /** Le programme prend la valeur précédemment entrée et l'établi comme code à cracker par la machine, le problème vient de la classe HumanPlayer qui induit en erreur la varaible code*/
        HumanPlayer.feedback(propositionIa, Collections.singletonList(userFeedback));
        System.out.println("test");
        String userFeedbacks = iaPlayer.feedback(propositionHu, Collections.singletonList(userFeedback));


        /**
          *  Premet de savoir si la proposition correspond aux codes
          */
        if (isFeedbackWin(userFeedback)) {
            System.out.println("Perdu !!!\nLa machine a craqué votre code en " + j + " tours !\n--------------------------------------------------------------------");
            result = GameResult.PLAYER1_WIN;
        } else if (isFeedbackWin(userFeedbacks)){
            System.out.println("Gagné !!!\nLe code était bien " + codeOfIA + "\n--------------------------------------------------------------------");
            result = GameResult.PLAYER2_WIN;
        } else {
            System.out.println("Mauvaise combinaison :/");
            System.out.println("Voici le rendu de votre proposition : " + userFeedbacks
                    + "\n--------------------------------------------------------------------");
            i++;
        }

        /** On lance les itérations en série*/
        while (i <= maxTry) {
            /***/
            System.out.println("Vous devez cracker un code long de " + getGconfig().getSizeCombi() + " caractères avant la machine.\nNombre d'essais : " + i + "/" + maxTry + ".");
            propositionHu = HumanPlayer.askConbinaison();
            /***/
            System.out.println("Tour de l'IA");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("...\n");
            TimeUnit.SECONDS.sleep(1);
            retour = IAPlayer.verifCode(codeOfHuman, size);
            propositionIa = retour.get(0);
            userFeedback = retour.get(1);
            /***/
            HumanPlayer.feedback(propositionIa, Collections.singletonList(userFeedback));
            userFeedbacks = iaPlayer.feedback(propositionHu, Collections.singletonList(userFeedback));

            if (isFeedbackWin(userFeedbacks)){
                System.out.println("Gagné !!!\nLe code était bien " + codeOfIA + "\n--------------------------------------------------------------------");
                result = GameResult.PLAYER2_WIN;
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
                result = GameResult.PLAYER1_WIN;
            } else if (j == maxTry - 1) {
                System.out.println("\n--------------------------------------------\n" +
                        "L'ordinateur n'a pas pu cracker votre code.\nFélicitations !!! ");
                result = GameResult.PLAYER2_WIN;

            }
        }
        return result;
    }


}
