package player.ia;

import configuration.GameConfig;
import player.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Décrire le comportement de l'ia.
 */
public class IAPlayer extends AbstractPlayer {

    private static String code;
    private static List<Integer> prop = new ArrayList<>();
    private static List<Integer> intervalleMin = new ArrayList<>();
    private static List<Integer> intervalleMax = new ArrayList<>();
    private static List<Integer> codeList = new ArrayList<>();
    private static GameConfig gameConfig = new GameConfig();

    public IAPlayer(Integer sizeCombi) {
        super(sizeCombi);
    }

    private static int size = gameConfig.getSizeCombi();

    /**
     * Génere une combinaison de taille 'sizeCombi' (anciennement GenerationCodeAuto)
     *
     * @return
     */
    @Override
    public String askConbinaison() {
        String code = "";
        for (int i = 0; i < getSizeCombi(); i++) {
            long X = Math.round(-0.5 + Math.random() * 10);
            code = code + (int) X;
        }
        this.code = code;
        return code;
    }


    /**
     * Génere un feeback en comparant la proposition et le code à deviner. (anciennement implementUserFeedback)
     *
     * @param proposition
     * @return
     */
    @Override
    public String feedback(String proposition) {
        String userFeedback = "";
        for (int i = 0; i < proposition.length(); i++) {

            Character p = proposition.charAt(i); // crée un char "p" qui prend la valeur de la i-ème valeur de prop
            Character c = code.charAt(i); // crée même chose
            if (p.equals(c)) {
                userFeedback += "=";
            } else if (p.compareTo(c) > 0) {
                userFeedback += "-";
            } else if (p.compareTo(c) < 0) {
                userFeedback += "+";
            }
        }
        return userFeedback;
    }

    public static String init(boolean fin){
        String userFeedback = "";
        String proposition="";
        System.out.println("gameconfig : "+size);
        for (int i = 0; i <gameConfig.getSizeCombi(); i++) {
            intervalleMin.add(i, 0);
            intervalleMax.add(i, 10);
            int iMin = intervalleMin.get(i);
            int iMax = intervalleMax.get(i);
            prop.add((iMax - iMin) / 2 + iMin);
            Integer pr = prop.get(i);
            proposition += pr.toString();
            System.out.println("Test vraiment pas opti pour proposition : " + proposition);
            int c = (code.codePointAt(i)) - 48;
            if (pr == c) {
                preRetourUser(pr, c, i,userFeedback);
            } else if (pr < c) {
                preRetourUser(pr, c, i,userFeedback);
            } else if (pr > c) {
                preRetourUser(pr, c, i,userFeedback);
            }
            endGame(c, i, fin);
        }
        System.out.println("Test pas opti de la proposition : "+proposition);
        return proposition;
    }
    public static String verifCode(int j, boolean fin) {
        String userFeedback = "";
        String proposition="";
        for (int i = 0; i < gameConfig.getSizeCombi(); i++) {
            Integer pr = prop.get(i);
            int c = (code.codePointAt(i)) - 48;
            if (pr == c) {
                preRetourUser(pr, c, i, userFeedback);
            } else if (pr < c) {
                intervalleMin.set(i, pr--);
                pr = (intervalleMax.get(i) - intervalleMin.get(i)) / 2 + intervalleMin.get(i);
                prop.set(i, pr);
                preRetourUser(pr, c, i, userFeedback);
            } else if (pr > c) {
                intervalleMax.set(i, pr++);
                pr = (intervalleMax.get(i) - intervalleMin.get(i)) / 2 + intervalleMin.get(i);
                prop.set(i, pr);
                preRetourUser(pr, c, i, userFeedback);

            }

            endGame(c, i, fin);
            return proposition;
        }
        if (fin == true) {
            System.out.println("\n--------------------------------------------------------------------" +
                    "L'ordinateur a réussi à craquer le code en " + (j = j+1) + " coups." +
                    "\n--------------------------------------------------------------------");
        }
        return userFeedback;
    }
    public static void endGame(int c, int i, boolean fin) {
        codeList.add(i, c);
        if (codeList.equals(prop)) {
            fin = true;
        }
        if (i == 3) {
            codeList.clear();
        }
    }
    public static void preRetourUser(int pr, int c, int i, String userFeedback) {

        if (pr == c) {
            userFeedback+= "=";
        } else if (pr < c) {
            userFeedback+="-";
        } else if (pr > c) {
            userFeedback+="+";
        }
    }
}
