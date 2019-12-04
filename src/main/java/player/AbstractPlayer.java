package player;

/**
 * Ici on décrit un joueur, qui peut etre soit une IA soit un humain
 */
public abstract class AbstractPlayer {
    private final Integer sizeCombi;

    protected AbstractPlayer(Integer sizeCombi) {
        this.sizeCombi = sizeCombi;
    }

    public abstract Integer askConbinaison();

    public Integer getSizeCombi() {
        return sizeCombi;
    }
}
