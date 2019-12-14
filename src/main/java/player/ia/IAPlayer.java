package player.ia;

import player.AbstractPlayer;

/**
 * Décrire le comportement de l'ia.
 */
public class IAPlayer extends AbstractPlayer {
    private String code;

    public IAPlayer(Integer sizeCombi) {
        super(sizeCombi);
    }

    /**
     * Génere une combinaison de taille 'sizeCombi' (anciennement GenerationCodeAuto)
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
     * @param proposition
     * @return
     */
    @Override
    public String feedback(String proposition) {
        String userFeedback = "";
        for (int i = 0; i < proposition.length(); i++) {

            Character p = proposition.charAt(i); // crée un char "p" qui prend la valeur de la i-ème valeur de prop
            Character c = code.charAt(i); // crée même chose
            //System.out.println("Prop : "+p+"\nCode : "+c);
            if (p.equals(c)) {
                //System.out.println(p.equals(c));
                userFeedback+="=";
            } else if (p.compareTo(c) > 0) {
                //System.out.println(p.compareTo(c)>0);
                userFeedback+="-";
            } else if (p.compareTo(c) < 0) {
                //System.out.println(p.compareTo(c)<0);
                userFeedback+="+";
            }
        }
        return userFeedback;
    }
}
