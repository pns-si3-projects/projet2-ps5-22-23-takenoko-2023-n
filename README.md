# Projet ps5 takenoko 2022-2023 de l'equipe N  

## Instructions  
Pour executer le jeu, il faut posseder la version 17.0 de Java  
Puis compiler et executer le code par les commandes :  
```sh
javac ./src/main/java/fr/cotedazur/univ/polytech/startingpoint/Main.java
java ./src/main/java/fr/cotedazur/univ/polytech/startingpoint/Main
```
Ou si vous avez Maven, il faut executer cette commande:  
```sh
mvn clean
mvn install
mvn exec:java  
```

## Fonctionnalités
Le jeu est joué par 2 joueurs automatisés avec affichage de leurs actions effectuées
* Initialisation du jeu avec l'Etang, le Jardinier et le Panda
* Les robots peuvent faire 2 actions différentes maximum par tour  
    (poser une parcelle, piocher un objectif de son choix, déplacer le Panda<!--, déplacer le Jardinier-->)
* Les robots sont capables de poser plusieurs parcelles sur le plateau
Il est possible de déplacer le Panda pour manger du bambou
<!--* Le Jardinier est utilisé pour faire pousser du bambou-->
