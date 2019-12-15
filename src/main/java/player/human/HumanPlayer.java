package player.human;

import player.AbstractPlayer;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {
    private String code;

    public HumanPlayer(Integer sizeCombi) {
        super(sizeCombi);
    }

    @Override
    public String askConbinaison() {
        Scanner sc = new Scanner(System.in);
        while (true){
            String prop = sc.nextLine();
            if (prop.length() != getSizeCombi()) {
                System.out.println("\n--------------------------------------------------------------------" +
                        "\nErreur de contenu. Veuillez effectuer une nouvelle saisie." +
                        "\n--------------------------------------------------------------------");
            }else if (prop.matches("[^0-9]")) {
                System.out.println("\n--------------------------------------------------------------------" +
                        "\nErreur de contenu. Veuillez effectuer une nouvelle saisie." +
                        "\n--------------------------------------------------------------------");
            }else {
                this.code = prop;
                return prop;
            }
        }
    }

    @Override
    public String feedback(String proposition) {
        return null;
    }
}
