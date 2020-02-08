package menu;

import game.GameResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CountGame {

    private static Map<GameMode, GameResult> rowList = new HashMap<GameMode, GameResult>();
    private static Scanner sc = new Scanner(System.in);

    public static Map<GameMode, GameResult> CountGame(GameMode gameMode, GameResult gameResult){
        rowList.put(gameMode,gameResult);
        return rowList;
    }

    public static void showMe(){
        for(Map.Entry<GameMode, GameResult> entry : rowList.entrySet()){
            GameMode entryNumber1 = entry.getKey();

            GameResult rowEntryNumber2 = entry.getValue();
            String entryNumber2 = readable(rowEntryNumber2.toString());

            System.out.println("Type de partie : "+entryNumber1+" / Résultat de la partie : "+entryNumber2);
        }
    }

    private static String readable(String entry2){
        if (entry2.contains("PLAYER1_WIN")){
            entry2 = "L'ordinateur a gagné";
        } else if (entry2.contains("PLAYER2_WIN")){
            entry2 = "Le joueur humain a gagné";
        } else if (entry2.contains("DRAW")){
            entry2 = "Egalité durant la partie";
        }
        return entry2;
    }

    public static ChoixEncore scoreboard(){
        System.out.println("Voulez vous afficher le tableau des scores ? O/N");
        String choix = sc.next();
        while (true){
            if (choix.length()>1){
                System.out.println("Saisie incorrect, veuillez saisir 'O' ou 'N'.");
            } else if (choix.contains("o") || choix.contains("O")){
                System.out.println("Affichage du tableau des scores");
                return ChoixEncore.O;
            } else if (choix.contains("n") || choix.contains("N")){
                return ChoixEncore.N;
            }else {
                System.out.println("Saisie incorrect, veuillez saisir 'O' ou 'N'.");
            }
        }
    }

}
