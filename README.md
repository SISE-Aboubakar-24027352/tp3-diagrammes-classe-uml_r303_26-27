# TP 3 : Modélisation statique avec UML– Diagrammes de classes

## **Ex. 1 Echauffement**<sup>**´**</sup> 

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