# Rapport de Projet ps5 takenoko 2022-2023 de l'equipe N

## Point d’avancement

### Fonctionnalités réalisées :
Le jeu est joué par 4 joueurs automatisés avec affichage de leurs actions effectuées
* Initialisation du jeu avec l'Etang, le Jardinier et le Panda
* Les robots peuvent faire 2 actions différentes maximum par tour  
    (poser une parcelle, piocher un objectif de son choix, déplacer le Panda, déplacer le Jardinier, prendre une irrigation)
* Les robots sont capables de poser plusieurs parcelles et des irrigations sur le plateau
* Il est possible de déplacer le Panda et le Jardinier
* Le Panda est utilisé pour manger du bambou et de les stocker dans une réserve dee bambou dans la plaquette du joueur
* Le Jardinier est utilisé pour faire pousser du bambou
* Pour poser une parcelle de couleur, il faut piocher et choisir une des trois parcelles proposées et un bambou de la même couleur est posé
* Les joueurs ont des objectifs Panda, Parcelles et Jardinier à valider
* Une partie se termine lorsqu'un joueur à validé 7 objectifs (parties de 4 joueurs), 8 objectifs (parties de 3 joueurs), 9 objectifs (parties de 2 joueurs)
* Les objectifs Panda sont validés lorsque la réserve de bambou du joueur possède le nombre de bambou de couleur demandé par l'objectif 
* Les objectifs Jardiniers sont validés lorsque des parcelles de la couleur demandée ont le nombre de section de bambou de l'objectif
* Les objectifs Parcelles sont validés lorque le motif de l'objectif est posé sur le plateau
* Le premier joueur ayant validé le nombre d'objectifs requit gagne la carte Empereur qui lui fait rajoute 2 points à son score
* Stratégies de jeu : 3 stratégies qui se focalisent uniquement sur un type d'objectif  
   `* Stratégie Panda`  
   `* Stratégie Parcelle`  
   `* Stratégie Jardinier`

### Fonctionnalités non réalisées :
* Le dé météo
* Les amménagements et les parcelles avec des aménagements

### Logs :
En fonction du mode de jeu, on peut désactiver l'affichage des logger en changeant le level.
Nous avons utilisé les logger pour l'affichage des actions réalisées, la validation des objectifs, le début et la fin du jeu.
Nous avons découpé en différentes classes les affichages en fonction de ce qu'elles affichaient.
Par exemple : L'affichage du déplacement des personnages est dans la classe AfficheurPersonnage, celle de la validation des objectifs est dans AfficheurObjectifs, celle du choix des actions des personnages est dans AfficheurJoueur, etc...

### CSV :
Capable de lire les données déja enregistrés dans un fichier CSV et d'ajouter les nouvelles données de parties.  
On écrit dans le fichier CSV les données mise à jour.  
Statisques des joueurs : nombre de parties gagnées, pourcentages de parties gagnées, nombre de parties perdues, pourcentages de parties perdues, nombre de parties nulles, pourcentages de parties nulles, score moyen


### Bot spécifique demandé :
Au premier tour, les actions du bot sont de piocher une carte Objectif et une irrigation.
Le bot choisi l'action Panda dès qu'il y a un bambou sur le plateau et favorise ceux de la couleur de ses objectifs de panda.
Le bot fait en sorte de toujours avoir 5 objectifs en main en préférant en avoir un de chaque minimum.
Le bot choisi l'action Irrigation lorsqu'il y a 3 possibilités d'irrigation sur le plateau afin que les autres joueurs ne puisse pas irriguer les parcelles qu'ils ont besoin.



## Architecture et qualité
_**Comment est faite l'architecture du projet ? Et quels choix vous ont amené à la réaliser ainsi ?**_  
Généralement, on code à l'interface mais dans les cas où il y a beaucoup de code communs entre plusieurs classes, on utilisait une classe abstract.  
On a choisi de créer des interfaces pour les parcelles (Etang, ParcelleCouleur, ParcelleDisponible) car elles sont toutes des parcelles avec une position. Cela permet de regrouper dans les appels de méthodes. De même pour les pioches d'objectifs.  
On a choisi de créer une classe abstraite pour Motif car on a remarqué que les différentes classes de motifs de parcelles ont du code communs pour les comparaisons et donc on a généralisé ces méthodes.  
  

_**Où trouver les infos ?**_  
De la Java doc est présente dans chacune de nos classes et interfaces.
  

_**Quelles parties sont bien faites ?**_  
Les pioches font leur role en ayant un minimum de méthodes. Egalement, il n'y a pas l'utilisation de getClass().
Les différents gestionnaires permettant de mieux définir les responsabilités des classes. 
  

_**Quelles parties sont à refactor et pourquoi ?**_  
Pour améliorer notre code, un refactor de la classe StrategieComplete pourrait être réalisé car elle possède trop de méthodes. Il aurait été possible de faire une autre classe pour aider à réflechir sur la stratégie.
  

_**Comment la sortie SONAR le montre-elle ?**_  
Sur la visualisation en ville de notre code, nous pouvons remarquer que cette classe est plus haute que les autres et a une complexité plus importante.


## Processus

Qui est responsable de quoi / qui a fait quoi ?  
Chloé : Jardinier, Irrigations, objectifs Jardinier, objectifs Panda, Stratégie Jardinier, Stratégie Complète  
Nicolas : Parcelles, Refactor du plateau et plaquette, Gestion des déplacements de personnages, Motifs d'objectifs Parcelles, Stratégie Parcelle  
Fabien : Parcelles, création des objectifs, Panda, Gestion des déplacements de personnages, Bambous, Couleur, documentations, Refactor plateau plaquette joueur et arbitre, Pioches, Ajout JCommander et Logger, Mode de jeu CSV  
Amandine : Jardinier, Bambous, Irrigations, objectifs Jardinier, Stratégie Panda, Stratégie Complète  


Quel est le process de l'équipe ?  
_**Git**_  
Nous avons créer des milestones pour l'implémentation de chaque nouvelles fonctionnalités ou lors d'un refactor.  
Nous les avons ensuite découpé en slices que nous avons par la suite découpé en features.  
Les features comportaient ensuite les différentes tasks que nous avions à réaliser ainsi que les tests.  
Chacun de nos commit était relié à une issue.


_**Branching Strategy**_  
Des branches de travail dans lesquelles on rajoutait les nouvelles fonctionnalités à implémenter.  
Le merge d'une branche de travail vers la branche Develop nécessitait une pull request validée par au moins un autre membre de l'équipe.  
Une branche Develop pour gérer les conflits entre ces branches de travail lors des merges.  
Le merge de la branche Develop vers la branche Master nécessitait une pull request validée par au moins la moitié des membres de l'équipe.  
Une branche Master qui permet des rendu stables.
