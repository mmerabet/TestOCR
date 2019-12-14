package game.defenseur;

import configuration.GameConfig;
import game.AbstractGame;
import game.GameResult;
import player.AbstractPlayer;

public class DefenseurGame extends AbstractGame {

    /**
     * Le constructeur sert uniquement à initialiser l'état d'un objet, on évite au maximum d'implémenter du code métier.
     * @param gconfig
     */
    public DefenseurGame(GameConfig gconfig) {
        super(gconfig);
    }

    @Override
    public GameResult playGame(AbstractPlayer defenseur, AbstractPlayer attaquant) {

        String code = defenseur.askConbinaison();
        if (getGconfig().getDevMod()){
            System.out.println("DevModeActivé:"+code);
        }
        Integer i = 1;
        Integer maxTry = getGconfig().getTryNum();
        while (i <= maxTry) {
            System.out.println("Vous devez cracker un code long de " + getGconfig().getSizeCombi() + " caractères.\nNombre d'essais : " + i + "/" + maxTry + ".");
            String proposition = attaquant.askConbinaison();
            String userFeedback = defenseur.feedback(proposition);
            if (isFeedbackWin(userFeedback)){
                System.out.println("Gagné !!!\nLe code était bien " + code + "\n--------------------------------------------------------------------");
                return GameResult.PLAYER2_WIN;
            } else {
                System.out.println("Mauvaise combinaison :/");
                System.out.println("Voici le rendu de votre proposition : " + userFeedback
                        + "\n--------------------------------------------------------------------");
                i++;
                if (i == maxTry - 1) {
                    System.out.println("Ceci est votre dernier essai." +
                            "\n--------------------------------------------------------------------");
                }
            }
        }
        return GameResult.PLAYER1_WIN;
    }
}
