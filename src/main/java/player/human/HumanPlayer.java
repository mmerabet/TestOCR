package player.human;

import configuration.GameConfig;
import player.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {
    private String codeHu;
    private String code;

    public HumanPlayer(Integer sizeCombi) {
        super(sizeCombi);
    }




    @Override
    public String askConbinaison() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Vous devez entrer un nombre contenant "+GameConfig.build().getSizeCombi()+" chiffres.");
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
                this.codeHu = prop;
                return prop;
            }
        }
    }


    @Override
    public String feedback(String proposition, List<String> userFeedback, String code){
        Scanner sc = new Scanner(System.in);
        List<Character> userFeedbacks = new ArrayList<>();
        String test = userFeedback.get(0);

        for (int i=0; i<getSizeCombi();i++){
            userFeedbacks.add(i,test.charAt(i));
        }
        boolean c = false;
       do{
            System.out.println("Voici ce qu'à trouvé la machine comme proposition pour votre code " + proposition + " (pour rappel votre code est : "+code+" )."
            +"\nQu'en pensez-vous ?"+
            "\n----------------------------------------------------------------------------" +
            "\nSi la valeur donnée est plus importante que celle du code, répondez par +"+
            "\nSi elle est inférieure, répondez par -"+
            "\nSi enfin elle est égale, répondez par ="+
            "\n----------------------------------------------------------------------------");
            String retour = sc.nextLine();
            for (int i=0;i<getSizeCombi();i++){
                String conditionA=Character.toString(userFeedbacks.get(i));
                String conditionB=Character.toString(retour.charAt(i));
                if (retour.length() != getSizeCombi() || retour.matches("^+-=")) {
                System.out.println("\n--------------------------------------------------------------------" +
                            "\nÊtes-vous sûr du résultat ?" +
                            "\n--------------------------------------------------------------------");
                    c = false;
                    break;
                } else if (conditionA.equals(conditionB)) {
                    c = true;
                } else {
                    c = false;
                    System.out.println("\n--------------------------------------------------------------------" +
                            "\nÊtes-vous sûr du résultat ?" +
                            "\n--------------------------------------------------------------------");
                    break;
                }
            }
        }while(c == false);
       return null;
    }
}
