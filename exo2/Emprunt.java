import java.time.LocalDate;

public class Emprunt {
    private LocalDate dateEmprunt;
    private LocalDate dateRestitutionPrevue;
    private LocalDate dateRestitutionEffective;

    private Volume volume;

    public Emprunt(Volume volume, LocalDate dateRetour) {
        this.volume = volume;
        this.dateEmprunt = LocalDate.now();
        this.dateRestitutionPrevue = dateRetour;
        this.dateRestitutionEffective = null;
    }

    // Prolonger la durée de l'emprunt
    public void prolonger(LocalDate nouvelleDate) {

        if (!estEnCours()) {
            throw new IllegalStateException("Cet emprunt est déjà terminé.");
        }

        if (!nouvelleDate.isAfter(dateRestitutionPrevue)) {
            throw new IllegalArgumentException("La nouvelle date doit être ultérieure.");
        }

        dateRestitutionPrevue = nouvelleDate;
    }

    // Restituer le volume
    public void restituer() {

        if (!estEnCours()) {
            throw new IllegalStateException("Ce volume a déjà été restitué.");
        }

        dateRestitutionEffective = LocalDate.now();
        volume.liberer();
    }

    public boolean estEnCours() {
        return dateRestitutionEffective == null;
    }
}
