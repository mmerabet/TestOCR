package player.ia;

import game.duel.DuelGame;
import player.AbstractPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 * Décrire le comportement de l'ia.
 */
public class IAPlayer extends AbstractPlayer {

    private String code;
    private static List<Integer> prop = new ArrayList<>();
    private static List<Integer> intervalleMin = new ArrayList<>();
    private static List<Integer> intervalleMax = new ArrayList<>();
    private static List<Integer> codeList = new ArrayList<>();

    public IAPlayer(Integer sizeCombi) {
        super(sizeCombi);
    }


    /**
     * Génere une combinaison de taille 'sizeCombi' (anciennement GenerationCodeAuto)
     *
     * @return
     */
    @Override
    public String askConbinaison() {
        String code = "";
        String codeIa = "";
        String codeHu = "";
        for (int i = 0; i < getSizeCombi(); i++) {
            long X = Math.round(-0.5 + Math.random() * 10);
            code = code + (int) X;
        }
        if (DuelGame.condition=="Hu"){
            codeHu = code;
            return codeHu;
        } else {
            codeIa = code;
            return codeIa;
        }
    }


    /**
     * Génere un feeback en comparant la proposition et le code à deviner. (anciennement implementUserFeedback)
     *
     * @param proposition
     * @return
     */
    @Override
    public String feedback(String proposition, List<String> userFeedback, String code) {
        String userFeedbacks = "";
        for (int i = 0; i < proposition.length(); i++) {

            Character p = proposition.charAt(i); // crée un char "p" qui prend la valeur de la i-ème valeur de prop
            Character c = code.charAt(i); // crée même chose
            if (p.equals(c)) {
                userFeedbacks += "=";
            } else if (p.compareTo(c) > 0) {
                userFeedbacks += "-";
            } else if (p.compareTo(c) < 0) {
                userFeedbacks += "+";
            }
        }
        return userFeedbacks;
    }

    public static List<String> init(String code, int size) {
        String userFeedback = "";
        String proposition = "";
        List<String> retour = new ArrayList<>();
        intervalleMax.clear();
        intervalleMin.clear();
        prop.clear();
        for (int i = 0; i < size; i++) {
            intervalleMin.add(i, 0);
            intervalleMax.add(i, 10);
            int iMin = intervalleMin.get(i);
            int iMax = intervalleMax.get(i);
            prop.add((iMax - iMin) / 2 + iMin);
            Integer pr = prop.get(i);
            proposition += pr.toString();
            int c = (code.codePointAt(i)) - 48;
            if (pr == c) {
                userFeedback += "=";
            } else if (pr < c) {
                userFeedback += "-";
            } else if (pr > c) {
                userFeedback += "+";
            }
        }
        retour.add(0, proposition);
        retour.add(1, userFeedback);
        return retour;
    }


    public static List<String> verifCode(String code, int size) {
        String proposition = "";
        String userFeedback = "";
        List<String> retour = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer pr = prop.get(i);
            int c = (code.codePointAt(i)) - 48;
            if (pr == c) {
                ;
            } else if (pr < c) {
                intervalleMin.set(i, pr--);
                pr = (intervalleMax.get(i) - intervalleMin.get(i)) / 2 + intervalleMin.get(i);
                prop.set(i, pr);
            } else if (pr > c) {
                intervalleMax.set(i, pr++);
                pr = (intervalleMax.get(i) - intervalleMin.get(i)) / 2 + intervalleMin.get(i);
                prop.set(i, pr);
            }
            if (pr == c) {
                userFeedback += "=";
            } else if (pr < c) {
                userFeedback += "-";
            } else if (pr > c) {
                userFeedback += "+";
            }
            proposition += pr.toString();
        }
        retour.add(0, proposition);
        retour.add(1, userFeedback);
        return retour;
    }
}
