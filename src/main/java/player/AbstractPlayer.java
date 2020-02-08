package player;

import java.util.List;

/**
 * Ici on décrit un joueur, qui peut etre soit une IA soit un humain
 */
public abstract class AbstractPlayer {

    private final Integer sizeCombi;

    protected AbstractPlayer(Integer sizeCombi) {
        this.sizeCombi = sizeCombi;
    }

    public abstract String askConbinaison();

    public  final Integer getSizeCombi() {
        return sizeCombi;
    }

    public abstract String feedback(String proposition, List<String> userFeedback, String code);


}
