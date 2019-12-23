# StarWarsApi
 StarWarsApi by Mathieu Ralamboson ESIEA

Projet individuel 4A

Réalisation d'une application android sous AndroidStudio , codé en Java et utilisant une API Rest

J'ai choisi une Api dans le thème de StarWars
https://swapi.co/
Le projet comporte 80 commits datant du 22 novembre 2019

# Consigne respectées :
- 3 écrans : 2 Activity et 1 Fragment.
- Appel WebService à une API Rest.
- Architecture MVC.
- Une fonctionnalité "Bar de recherche " ajouté.
- Un fragment commun utilisé pour afficher les details d'un object.
- Git utilisé
- Stockage des données en cache.
- Design respectant le thème StarWars
- Toast

Une Activity avec une liste de catégorie à explorer.
En cliquant sur une catégorie vous accedez à la liste des object de cette dernière .
Enfin en cliquant sur un object vous pouvez consulter les détails et donnée fourni par l'API

De plus pour chaque Catégorie vous avez la fonctionnalité de recherche à travers le 2ème écran.
Elle permet de filtrer la liste d'object en fonction de votre texte taper dans la barre de recherche.

# Premier Ecran
- Affichage de la liste des Catégories que l'on peut parcourir
- Affichage de l'image de chaque Catégorie avec son nom aligné
- Scrolling 20 par 20

![Ecran 1](https://user-images.githubusercontent.com/48732335/71378730-2fdeb480-25c9-11ea-9f50-d796954d0fd7.PNG)

# Deuxième Ecran
- Affichage de la liste de la Catégorie choisi avec une image accompagnant chaque object
- Scrolling
- Fonctionalité bar de recherche avec la loupe en haut à droite

# Troisième Ecran
- Fragment commun
- Affichage des details de l'object choisi
- Detail de l'object : Nom , taille, type , poid ...

