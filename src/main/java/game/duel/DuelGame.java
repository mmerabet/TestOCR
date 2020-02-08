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

    public static String condition;

    /**
     * Les joueurs sont à la fois attaquant et defenseur.
     *
     * @param iaPlayer
     * @param HumanPlayer
     * @return
     */
    @Override
    public GameResult playGame(AbstractPlayer iaPlayer, AbstractPlayer HumanPlayer) throws InterruptedException {
        String code = "";
        GameResult result = null;

        /** on demande le code aux différentes parties*/
        System.out.println("Joueur humain, voici votre tour d'initialisation.\nAfin de défier la machine, veuillez suivre les instructions suivantes : ");

        String codeOfHuman = HumanPlayer.askConbinaison();// ici le code entré par le joueur en debut de partie, ce code sera soumis à l'algorithme de résolution automatique
        System.out.println("L'ordinateur va créer son code. Veuillez patienter." + "\n--------------------------------------------------------------------");
        TimeUnit.SECONDS.sleep(1);
        String codeOfIA = iaPlayer.askConbinaison(); // ici, le code créé automatiquement par la machine
        Integer i = 1;
        Integer maxTry = getGconfig().getTryNum();
        int j = 1;
        int size = codeOfHuman.length();
        if (getGconfig().getDevMod() == true) {
            System.out.println("DevModeActivé:" + codeOfIA);
        }
        /**Les deux parties cherchent à craquer le code tour à tour*/

        /** L'humain, premier input */

        System.out.println("Tour de l'humain\nVous devez trouver la solution d'un code long de " + getGconfig().getSizeCombi() + " caractères avant la machine.\nNombre d'essais : " + i + "/" + maxTry + ".");
        String propositionHu = HumanPlayer.askConbinaison();

        /** L'IA , initialisation*/

        System.out.println("Tour de l'IA");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("...\n");
        TimeUnit.SECONDS.sleep(1);
        /**  */
        List<String> retour = IAPlayer.init(codeOfHuman, size);
        String propositionIa = retour.get(0);
        String userFeedback = retour.get(1);


        /** Le programme prend la valeur précédemment entrée et l'établi comme code à cracker par la machine, le problème vient de la classe HumanPlayer qui induit en erreur la varaible code*/
        System.out.println("Vérification des propositions des deux parties." + "\n--------------------------------------------------------------------");
        HumanPlayer.feedback(propositionIa, Collections.singletonList(userFeedback), codeOfHuman); //vérification de la proposition de l'ordi

        String userFeedbacks = iaPlayer.feedback(propositionHu, Collections.singletonList(userFeedback), codeOfIA); // vérification de la proposition du joueur
        System.out.println("Voici ce que l'ordinateur pense de votre proposition : " + userFeedbacks+ "(pour rappel votre code est : " + propositionHu
                + "\n--------------------------------------------------------------------");


        /**
         *  Premet de savoir si la proposition correspond aux codes
         */
        if (isFeedbackWin(userFeedback) && isFeedbackWin(userFeedbacks)) {
            System.out.println("Match nul !" + "\n--------------------------------------------------------------------");
            result = GameResult.DRAW;
        } else if (isFeedbackWin(userFeedback)) {
            System.out.println("Perdu !!!\nLa machine a craqué votre code en " + j + " tours !\n--------------------------------------------------------------------");
            result = GameResult.PLAYER1_WIN;
        } else if (isFeedbackWin(userFeedbacks)) {
            System.out.println("Gagné !!!\nLe code était bien " + codeOfIA + "\n--------------------------------------------------------------------");
            result = GameResult.PLAYER2_WIN;
        } else {
            i++;

            /** On lance les itérations en série*/
            while (i <= maxTry) {
                /***/
                System.out.println("Tour de l'humain. \nNombre d'essais : " + i + "/" + maxTry + "." + "\n--------------------------------------------------------------------");
                propositionHu = HumanPlayer.askConbinaison();
                /***/
                System.out.println("Tour de l'IA" + "\n--------------------------------------------------------------------");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("...\n");
                TimeUnit.SECONDS.sleep(1);
                retour = IAPlayer.verifCode(codeOfHuman, size);
                propositionIa = retour.get(0);
                userFeedback = retour.get(1);
                /***/
                System.out.println("Vérification des proposition des deux partis." + "\n--------------------------------------------------------------------");
                HumanPlayer.feedback(propositionIa, Collections.singletonList(userFeedback), codeOfHuman);
                userFeedbacks = iaPlayer.feedback(propositionHu, Collections.singletonList(userFeedback), codeOfIA);
                System.out.println("Voici ce que l'ordinateur pense de votre proposition : " + userFeedbacks + "(pour rappel votre proposition est : " + propositionHu
                        + "\n--------------------------------------------------------------------");


                if (isFeedbackWin(userFeedback) && isFeedbackWin(userFeedbacks)) {
                    System.out.println("Match nul !");
                    result = GameResult.DRAW;
                    break;
                } else if (isFeedbackWin(userFeedback)) {
                    System.out.println("Perdu !!!\nLa machine a craqué votre code en " + i + " tours !\n--------------------------------------------------------------------");
                    result = GameResult.PLAYER1_WIN;
                    break;
                } else if (isFeedbackWin(userFeedbacks)) {
                    System.out.println("Gagné !!!\nLe code était bien " + codeOfIA + "\n--------------------------------------------------------------------");
                    result = GameResult.PLAYER2_WIN;
                    break;
                } else {
                    i++;

                    if (i == maxTry - 1) {
                        System.out.println("Ceci est votre dernier essai."
                                + "\n--------------------------------------------------------------------");
                    }
                }
            }
        }
        return result;
    }


}
