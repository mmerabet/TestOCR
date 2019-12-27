package menu;

import java.util.Scanner;

/**
 * Represente l'affichage des modes de jeu ainsi que les saisies joueur.
 */
public class ChoixMode {
    private static Scanner sc = new Scanner(System.in);

    /**
     * On affiche les modes de jeu.
     */
    public static void afficherModes() {
        System.out.println("Veuillez sélectionner un mode de jeu : " + "\n" +
                " \n" +
                " 1 - Challenger\n" +
                " 2 - Défenseur\n" +
                " 3 - Duel ");
    }

    /**
     * On demande au joeur le mode et ici on boucle tant que le choix n'est pas valide.
     *
     * @return
     */
    public static GameMode demanderMode() {
        while (true) {
            String Choix = sc.nextLine();
            if (Choix.length() == 1) {
                if (Choix.contains("2")) {
                    System.out.println("Mode sélectionné : Défenseur.");
                    return GameMode.DEFENSEUR;
                } else if (Choix.contains("1")) {
                    System.out.println("Mode sélectionné : Challenger.");
                    return GameMode.CHALLENGER;
                } else if (Choix.contains("3")) {
                    System.out.println("Mode sélectionné : Duel. Préparez-vous.");
                    return GameMode.DUEL;
                }
            } else {
                if (Choix.contains("Défenseur") || Choix.contains("défenseur")
                        || Choix.contains("Defenseur") || Choix.contains("defenseur")) {
                    System.out.println("Mode sélectionné : Défenseur.");
                    return GameMode.DEFENSEUR;

                } else if (Choix.contains("Challenger") || Choix.contains("challenger")) {
                    System.out.println("Mode sélectionné : Challenger.");
                    return GameMode.CHALLENGER;

                } else if (Choix.contains("Duel") || Choix.contains("duel")) {
                    System.out.println("Mode sélectionné : Duel. Préparez-vous.");
                    return GameMode.DUEL;

                } else {
                    System.out.println("Veuillez réitérer votre saisie\n------------------------------");
                    afficherModes();
                }
            }
        }
    }


}
