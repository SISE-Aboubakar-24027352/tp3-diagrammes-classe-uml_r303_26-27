# TP 3 : Modélisation statique avec UML– Diagrammes de classes

## Ex. 1 Echauffement

1. Pour chaque exemple ci-dessous, dessiner le diagramme de classes correspondant 

   - Dans le cadre d’un système de vente en ligne les livres et les CDs sont des articles. Les clients peuvent commander des articles. 

   - Une compagnie aérienne propose des vols et un vol peut être proposé par différentes compagnies. 

   - D’après les textes de loi en 2018, en France deux personnes peuvent être mariées et une personne ne peut pas être mariée à deux autres personnes en même temps. 

   - Voici deux classes écrites en `C++` : 

        ```cpp
        class Document
        {
        public:
            void imprimer(Imprimante &Out);
        };
        ```

        ```cpp
        class Imprimante
        {
        public:
            string getNom() { return nom; }
        };
        ```

        Donnez le diagramme de classes correspondant. 

   - Un répertoire contient des fichiers. La suppression du répertoire implique la suppression de ses fichiers. 

   - Une pièce contient des murs (mais attention : un mur peut appartenir à deux pièces contigu¨es) 

2. Illustrez et expliquez toutes les relations entre les classes vues en cours. Proposez le code `C++` des relations que vous connaissez.


## Ex. 2 Gestion d’une bibliothèque

On vous demande de proposer un logiciel de gestion des emprunts dans une bibliothèque universitaire.
Une telle bibliothèque comprend un ensemble de documents et un ensemble d’adhérents. Les adhérents
sont des étudiants ou des professeurs. Chacun ayant un prénom, nom, une adresse postale, une adresse
mail et une date d’adhésion. De plus les étudiants possèdent un numéro d’étudiant alors que les
professeurs ont un numéro NUMEN (numéro d’identification éducation nationale). Les adhérents sont
inscrits ou désinscrits à la bibliothèque sur une simple demande.
La bibliothèque est bien entretenue : de nouveaux documents y sont ajoutés régulièrement. Chaque
document est caractérisé par son numéro. également les documents ont un titre. La bibliothèque
distingue les différents types de documents: des journaux, soit des volumes. Les volumes sont soit des
dictionnaires, soit des livres, soit des manuels scolaires. Les volumes ont en plus un auteur (chaˆıne
de caractères). Les journaux ont, outre les caractéristiques des documents, une date de parution. Les
manuels sont distingués par la matière, ainsi que le cycle d’études (Licence, Master, Doctorat). Chaque
livre a un genre littéraire et chaque dictionnaire a une thématique.
Les adhérents peuvent consulter tous les documents dont la bibliothèque dispose. En revanche, les
adhérents peuvent emprunter uniquement des volumes (maximum 4 en fait). Naturellement, il faudrait
prévoir un moyen de savoir quels sont les volumes empruntés par un adhérent. La date de restitution
d’un volume emprunté est fixée au moment du prêt. Cette date peut être prolongée sur demande de
l’adhérent.

1. Réalisez le diagramme de classes du logiciel.
2. Proposez le code Java de deux ou trois classes de votre diagramme.