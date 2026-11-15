public abstract class Volume {

    private int numero;
    private String titre;
    private String auteur;

    private Emprunt empruntEnCours;

    public Volume(int numero, String titre, String auteur) {
        this.numero = numero;
        this.titre = titre;
        this.auteur = auteur;
    }

    public boolean estDisponible() {
        return empruntEnCours == null;
    }

    void setEmpruntEnCours(Emprunt emprunt) {
        this.empruntEnCours = emprunt;
    }

    void liberer() {
        this.empruntEnCours = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }
}
