
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Adherent {

    private String prenom;
    private String nom;
    private String adressePostale;
    private String adresseMail;
    private LocalDate dateAdhesion;
    private boolean inscrit;

    private List<Emprunt> emprunts = new ArrayList<>();

    public Adherent(String prenom, String nom,
                    String adressePostale, String adresseMail) {

        this.prenom = prenom;
        this.nom = nom;
        this.adressePostale = adressePostale;
        this.adresseMail = adresseMail;
        this.dateAdhesion = LocalDate.now();
        this.inscrit = true;
    }

    public void inscrire() {
        inscrit = true;
    }

    public void desinscrire() {
        inscrit = false;
    }

    // Compter les emprunts non restitués
    public long nombreEmpruntsEnCours() {
        return emprunts.stream()
                .filter(Emprunt::estEnCours)
                .count();
    }

    // Emprunter un volume
    public Emprunt emprunter(Volume volume, LocalDate dateRetour) {

        if (!inscrit) {
            throw new IllegalStateException(
                "L'adhérent n'est pas inscrit."
            );
        }

        if (nombreEmpruntsEnCours() >= 4) {
            throw new IllegalStateException(
                "Maximum de 4 emprunts atteint."
            );
        }

        if (!volume.estDisponible()) {
            throw new IllegalStateException(
                "Ce volume est déjà emprunté."
            );
        }

        if (!dateRetour.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "La date de retour doit être future."
            );
        }

        Emprunt emprunt = new Emprunt(volume, dateRetour);

        emprunts.add(emprunt);
        volume.setEmpruntEnCours(emprunt);

        return emprunt;
    }

    public List<Emprunt> getEmprunts() {
        return List.copyOf(emprunts);
    }
}
