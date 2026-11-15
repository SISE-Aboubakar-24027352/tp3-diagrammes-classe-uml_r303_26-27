## Exercice 2
**1) Réalisez le diagramme de classes du logiciel.**
```puml
@startuml
title Gestion d'une bibliothèque universitaire

enum CycleEtudes {
    LICENCE
    MASTER
    DOCTORAT
}

enum GenreLitteraire {
    ROMAN
    POESIE
    THEATRE
    FANTASTIQUE
    AUTRE
}


abstract class Adherent {
    - prenom : String
    - nom : String
    - adressePostale : String
    - adresseMail : String
    - dateAdhesion : LocalDate
    - inscrit : boolean
    + inscrire() : void
    + desinscrire() : void
    + emprunter(volume : Volume, dateRetour : LocalDate) : Emprunt
}

class Etudiant {
    - numeroEtudiant : String
}

class Professeur {
    - numen : String
}

Adherent <|-- Etudiant
Adherent <|-- Professeur

abstract class Document {
    - numero : int
    - titre : String
}

class Journal {
    - dateParution : LocalDate
}

abstract class Volume {
    - auteur : String
    - titre : String
    - numero : int
    + estDisponible() : boolean
    + liberer() : void
    + setEmpruntEnCours(emprunt : Emprunt) : void
    + getNumero : int
    + getTitre : String
    + getAuteur() : String
}

class Livre {
    - genre : GenreLitteraire
}

class Dictionnaire {
    - thematique : String
}

class ManuelScolaire {
    - matiere : String
    - cycle : CycleEtudes
}

Document <|-- Journal
Document <|-- Volume

Volume <|-- Livre
Volume <|-- Dictionnaire
Volume <|-- ManuelScolaire


class Emprunt {
    - dateEmprunt : LocalDate
    - dateRestitutionPrevue : LocalDate
    - dateRestitutionEffective : LocalDate
    + prolonger(nouvelleDate : LocalDate) : void
    + restituer() : void
    + estEnCours() : boolean
}

Adherent "1" -- "0..*" Emprunt : effectue
Volume "1" -- "0..*" Emprunt : concerne

class Bibliotheque {
    - nom : String
    + ajouterDocument(d : Document) : void
    + ajouterAdherent(a : Adherent) : void
    + rechercherDocument(numero : int) : Document
    + emprunter(a : Adherent, v : Volume) : Emprunt
}

Bibliotheque "1" o-- "0..*" Document : catalogue
Bibliotheque "1" o-- "0..*" Adherent : membres
Bibliotheque "1" o-- "0..*" Emprunt : gestion

note right of Adherent
  Un adhérent peut emprunter
  au maximum 4 volumes
  simultanément.
end note

note right of Volume
  Un volume ne peut être
  emprunté que par un
  seul adhérent à la fois.
end note

note bottom of Document
  Tous les documents peuvent
  être consultés.
  Seuls les volumes peuvent
  être empruntés.
end note

@enduml
```
<img src="../img/exo2.png"><br>

---
**2) Proposez le code Java de deux ou trois classes de votre diagramme.**
1. `Adherent.java`
```java

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
        return emprunts.stream().filter(Emprunt::estEnCours).count();
    }

    // Emprunter un volume
    public Emprunt emprunter(Volume volume, LocalDate dateRetour) {

        if (!inscrit) {
            throw new IllegalStateException("L'adhérent n'est pas inscrit.");
        }

        if (nombreEmpruntsEnCours() >= 4) {
            throw new IllegalStateException("Maximum de 4 emprunts atteint.");
        }

        if (!volume.estDisponible()) {
            throw new IllegalStateException("Ce volume est déjà emprunté.");
        }

        if (!dateRetour.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de retour doit être future.");
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
```

2. `Emprunt.java`
```java
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
```

3. `Volume.java`
```java
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
```