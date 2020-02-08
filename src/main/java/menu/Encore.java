package menu;

import java.util.Scanner;

/**
 * Permet de demander à l'utilisateur s'il veut faire une nouvelle partie.
 */
public class Encore {
    private static Scanner sc = new Scanner(System.in);

    /**
     * On affiche l'option à l'user.
     */
    public static void askReboot(){
        System.out.println("Voulez-vous recommencer une partie ? O/N");
    }

    public static ChoixEncore reboot(){
        while (true){
            String choix = sc.next();
            if (choix.length()>1){
                System.out.println("Saisie incorrect, veuillez saisir 'O' ou 'N'.");
            } else if (choix.contains("o") || choix.contains("O")){
                System.out.println("Nouvelle partie lancée.");
                return ChoixEncore.O;
            } else if (choix.contains("n") || choix.contains("N")){
                System.out.println("Merci d'avoir joué !");
                return ChoixEncore.N;
            }else {
                System.out.println("Saisie incorrect, veuillez saisir 'O' ou 'N'.");
            }
        }
    }


}
