# TP 3 : Modélisation statique avec UML– Diagrammes de classes
## Exercice 1
**1) Pour chaque exemple ci-dessous, dessiner le diagramme de classes correspondant** <br>
- **A)** Dans le cadre d’un système de vente en ligne les livres et les CDs sont des articles. Les clients peuvent commander des articles
```puml
@startuml
abstract class Article
class Livre
class CD
class Client
class Commande

Article <|-- Livre : <<extends>>
Article <|-- CD : <<extends>>

Client "1" -- "0..*" Commande : passe
Commande "1..*" -- "0..1" Article : contient
@enduml
```
<img src="img/exo1-1A.png"><br>

- **B)** Une compagnie aérienne propose des vols et un vol peut être proposé par différentes compagnies.
```puml
@startuml
class CompagnieAerienne
class Vol

CompagnieAerienne "1..*" -- "1..*" Vol : propose
@enduml
```
<img src="img/exo1-1B.png"><br>

- **C)** D’après les textes de loi en 2018, en France deux personnes peuvent être mariées et une personne ne peut pas être mariée à deux autres personnes en même temps.
```puml
@startuml
class Personne

Personne "0..1" -- "0..1" Personne : est marié(e) à
@endum
```
<img src="img/exo1-1C.png"><br>

- **D)** Voici deux classes  ́ecrites en C++ 
```cpp
class Document
{
    public:
    void imprimer(Imprimante &Out);
};

class Imprimante
{
    public:
    string getNom() { return nom; }
};
```
Donnez le diagramme de classes correspondant.
```puml
@startuml
class Document {
  +imprimer(Out: Imprimante&): void
}

class Imprimante{
  - nom: string
  + getNom(): string
}

note as Solution2
imprimer(Imprimante &Out): void
Une autre solution viable mais redondante avec 'imprimer()'.
end note

Document ..> Imprimante : imprime  ()

Solution2 --- Document
@enduml
```
<img src="img/exo1-1D.png"><br>

- **E)** Un répertoire contient des fichiers. La suppression du répertoire implique la suppression de ses fichiers.
``` puml
@startuml
class Repertoire
class Fichier

Repertoire "1" *-- "0..*" Fichier : contient
@enduml
```
<img src="img/exo1-1E.png"><br>

- **F)** Une pièce contient des murs (mais attention : un mur peut appartenir à deux pièces contiguës)
```puml
@startuml 
class Piece
class Mur

Piece "0..*" -- "1..*" Mur : contient
@enduml
```
<img src="img/exo1-1F.png"><br>

---
**2) Illustrez et expliquez toutes les relations entre les classes vues en cours. Proposez le code C++ des relations que vous connaissez.**
``` puml
@startuml 
package Association <<Rectangle>> {
  class A{
  b: B
  }
  
  class B{
    a: A
  }
  
  A -- B
}

package Aggragation <<Rectangle>> {
  class A{
  b: B
  }
  
  class B{}
  
  A o-- B : A_UN
}

package Composition <<Rectangle>> {
  class A{
  b: B
  }
  
  class B{}
  
  A *-- B
}


package Heritage <<Rectangle>> {
  class A{}
  
  class B{}
  
  A <-- B : EST_UN
}

package Dépendance <<Rectangle>> {
  class A{}
  
  class B{
    +m(a: A)
  }
  
  A <.. B : m
}

package Implementation <<Rectangle>> {
  class A{}
  class B{}
  
  A <|.. B
}
@enduml
```
<img src="img/exo1-2.png"><br>

## Exercice 2
