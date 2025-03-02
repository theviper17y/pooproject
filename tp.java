package test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Personne {
    protected String nom;
    protected String prenom;
    protected String dateOfBirth; // Changed from age to dateOfBirth
    protected String sexe; // "M" for male, "F" for female
    protected String email;
    protected String phoneNumber;

    public Personne(String nom, String prenom, String dateOfBirth, String sexe, String email, String phoneNumber) {
        setNom(nom);
        setPrenom(prenom);
        this.dateOfBirth = dateOfBirth; // Set date of birth
        this.sexe = sexe;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom != null && !nom.isEmpty()) {
            this.nom = capitalizeFirstLetter(nom);
        }
    }

    // Getter and Setter for prenom
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom != null && !prenom.isEmpty()) {
            this.prenom = capitalizeFirstLetter(prenom);
        }
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public boolean isMajeur() {
        // Calculate age based on date of birth
        String[] parts = dateOfBirth.split("-");
        int birthYear = Integer.parseInt(parts[0]);
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return (currentYear - birthYear) >= 18; // Check if the person is 18 or older
    }

    public String getSexe() {
        return sexe;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s %-5s %-30s %-15s", nom, prenom, dateOfBirth, sexe, email, phoneNumber);
    }
}

class Etudiant extends Personne {
    private String matricule;
    private double moyenneBac;

    public Etudiant(String nom, String prenom, String dateOfBirth, String sexe, double moyenneBac, String matricule, String email, String phoneNumber) {
        super(nom, prenom, dateOfBirth, sexe, email, phoneNumber);
        this.moyenneBac = moyenneBac;
        this.matricule = matricule;
    }

    public double getMoyenneBac() {
        return moyenneBac;
    }

    public String getMatricule() {
        return matricule;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s %-5s %-15s %-30s %-15s %-10.2f", getNom(), getPrenom(), dateOfBirth, sexe, matricule, email, phoneNumber, moyenneBac);
    }
}

class EtudiantENS extends Etudiant {
    String diplome; // "PES" or "PEM"

    public EtudiantENS(String nom, String prenom, String dateOfBirth, String sexe, double moyenneBac, String matricule, String phoneNumber) {
        super(nom, prenom, dateOfBirth, sexe, moyenneBac, matricule, generateEmail(nom, prenom), phoneNumber);
        this.diplome = (moyenneBac >= 15.3) ? "PES" : "PEM";
    }

    private static String generateEmail(String nom, String prenom) {
        return prenom.toLowerCase() + "." + nom.toLowerCase() + "@g.ens.dz";
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s %-5s %-15s %-30s %-15s %-10.2f %-5s", getNom(), getPrenom(), dateOfBirth, sexe, getMatricule(), email, phoneNumber, getMoyenneBac(), diplome);
    }
}

public class Main {
    private static List<Personne> personnes = new ArrayList<>();
    private static int countEtudiants = 0;
    private static int countENS = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n==============================");
            System.out.println("       Menu Principal          ");
            System.out.println("==============================");
            System.out.println("1. Ajouter un etudiant");
            System.out.println("2. Afficher les etudiant");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            choice = scanner.nextLine();

            switch (choice) {
            case "1":
                addPerson(scanner);
                break;
            case "2":
                displayPersons(scanner);
                break;
            case "3":
                System.out.println("Au revoir!");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    } while (!choice.equals("3"));
}

private static void addPerson(Scanner scanner) {
    System.out.println("\n==============================");
    System.out.println("      Ajouter une Personne     ");
    System.out.println("==============================");
    System.out.print("Entrez le nom : ");
    String nom = scanner.nextLine();
    System.out.print("Entrez le prénom : ");
    String prenom = scanner.nextLine();
    System.out.print("Entrez la date de naissance (YYYY-MM-DD) : ");
    String dateOfBirth = scanner.nextLine();
    System.out.print("Entrez le sexe (M/F) : ");
    String sexe = scanner.nextLine();
    System.out.print("Entrez le numéro de téléphone : ");
    String phoneNumber = scanner.nextLine();

    double moyenneBac = 0.0;
    while (true) {
        System.out.print("Entrez la moyenne du Bac (10-20) : ");
        moyenneBac = Double.parseDouble(scanner.nextLine());
        if (moyenneBac >= 10 && moyenneBac <= 20) {
            break; // Exit the loop if the input is valid
        } else {
            System.out.println("Erreur : La moyenne doit être comprise entre 10 et 20. Veuillez réessayer.");
        }
    }

    System.out.print("Est-ce un étudiant de l'ENS ? (O/N) : ");
    String isENS = scanner.nextLine();
    System.out.print("Entrez le matricule : ");
    String matricule = scanner.nextLine();

    if (isENS.equalsIgnoreCase("O")) {
        EtudiantENS etudiantENS = new EtudiantENS(nom, prenom, dateOfBirth, sexe, moyenneBac, matricule, phoneNumber);
        personnes.add(etudiantENS);
        countEtudiants++;
        countENS++;
    } else {
        Etudiant etudiant = new Etudiant(nom, prenom, dateOfBirth, sexe, moyenneBac, matricule, generateNormalEmail(nom, prenom), phoneNumber);
        personnes.add(etudiant);
        countEtudiants++;
    }
    System.out.println("Personne ajoutée avec succès !");
}

private static String generateNormalEmail(String nom, String prenom) {
    return prenom.toLowerCase() + "." + nom.toLowerCase() + "@example.com"; // Replace with a normal email domain
}

private static void displayPersons(Scanner scanner) {
    System.out.println("\n==============================");
    System.out.println("      Afficher les Personnes   ");
    System.out.println("==============================");
    System.out.println("Voulez-vous filtrer par (1) Majeur ou pas, (2) Sexe, (3) Diplôme ENS ? (0 pour tout afficher)");
    String filterChoice = scanner.nextLine();

    // Print header for the output
    System.out.printf("%-20s %-20s %-15s %-5s %-15s %-30s %-15s %-10s%n", "Nom", "Prénom", "Date de Naissance", "Sexe", "Matricule", "Email", "Téléphone", "Moyenne Bac");
    System.out.println("--------------------------------------------------------------------------------");

    for (Personne personne : personnes) {
        boolean display = true;

        if (filterChoice.equals("1")) {
            System.out.print("Voulez-vous afficher les majeurs (O/N) ? ");
            String isMajeur = scanner.nextLine();
            if (isMajeur.equalsIgnoreCase("O") && !personne.isMajeur()) {
                display = false;
            } else if (isMajeur.equalsIgnoreCase("N") && personne.isMajeur()) {
                display = false;
            }
        } else if (filterChoice.equals("2")) {
            System.out.print("Voulez-vous afficher les femmes (F) ou les hommes (M) ? ");
            String sexeFilter = scanner.nextLine();
            if (!personne.getSexe().equalsIgnoreCase(sexeFilter)) {
                display = false;
            }
        } else if (filterChoice.equals("3") && personne instanceof EtudiantENS) {
            EtudiantENS ensStudent = (EtudiantENS) personne;
            System.out.print("Voulez-vous afficher les diplômés PES (O) ou PEM (N) ? ");
            String diplomeFilter = scanner.nextLine();
            if (!ensStudent.diplome.equalsIgnoreCase(diplomeFilter.equals("O") ? "PES" : "PEM")) {
                display = false;
            }
        } else if (filterChoice.equals("0")) {
            display = true; // Show all
        }

        if (display) {
            System.out.println(personne);
        }
    }

    System.out.println("--------------------------------------------------------------------------------");
    System.out.println("Total personnes: " + personnes.size());
    System.out.println("Total étudiants: " + countEtudiants);
    System.out.println("Total étudiants ENS: " + countENS);
}
}
    		
