# pglp_9.9 Logiciel de dessin

![Test Image 4](https://www.monsieurcartoon.com/monsieurcartoon.com/Q%26R_files/geometrique.jpg)

Logiciel de dessin développé en java mettant en oeuvre les designs patterns vu en cours (Pattern composite, builder, command, ect...), et se limittant à afficher le description des figures crées qu'on peut les déplacer par la suite.


## Exemple d'utilisation :

Pour créer un élément graphique (cercle, carré, rectangle ou un triangle) il faut utliser la commande suivante:

      Nom_Element_Graphique = Type_Element_Graphiqur( les arguments )
      
## Création d'un élément graphique :
      
 Exemple de création d'un Cercle, un Carré, un Triangle et un rectangle:
 
 
      cercle1 = Cercle( (2,0), 6) // 5 est le rayon du cercle et (2, 0) les coordonnées du centre.
      
      carre1 = Carre((2,2),4) // le point (2,2) est le point de référence du carré, 4 est la longueur de son coté.
      
      triangle1 = Triangle( (2, 4), (3, 5), (2, 7)) les 3 points saisis comme arguments ce sont les 3 sommets du triangle.

      rectangle1 = Rectangle( (5, 8), 4, 7) avec (5,8) est le point de référence (en haut  gauche), 4 et 7 sont respectivement

      la longueur et la largeur du rectangle.
      
## Déplacer un élément graphique :
      
Pour déplacer un élément graphique il faut utiliser la commande move pour cela : move (Nom_Element_Graphique, (x,y)) cette instruction décale tous les points
de figure en ajoutant x à l'abscisse et y à l'ordonnée, nous avons ci dessous un exemple de déplacement d'un cercle, un carré, un triangle et un rectangle :

      move( cercle1, (2, 4))
      
      move(carre1, (5, 6))
      
      move(triangle1, (4, 6))

      move(rectangle1, (3, 6))
     
## Créer un groupe d'éléments graphique :

Pour créer un ensemble d'éléments graphique utiliser la syntaxe suivant:

      Nom_Group_Element = group( liste des éléments graphiques )
      
Exemple de création d'un ensemble d'éléments:

      groupeCercle = group( cercle1, cercle2, cercle3)
      
      groupeCarre = group( carre1, carre2, carre3)
      
      groupeTriangle = group( triangle1, triangle2)

    
 ## Déplacer un groupe d'élément :
 
 Pour déplacer un groupe d'élément, utiliser la syntaxe suivante:
 
      move(Nom_du_group, (a, b))
      
 Le 1er argument étant le nom du groupe, le 2ème élément étant le point qui servira a déplacer l'élément graphique concerné,
 avec a et b étant des entiers.
 
 ## Afficher un élément graphique :
 
 L'affichage se fait automatiquement toujours soit en créant un nouveau élement ou en déplacant n'importe quel élement du dessin.

 Donc on a toujours une image du dessin que le programme affiche à chaque fois. Il tient bien en compte tous les mises à jour effectuées.
 
 ## Avoir un guide d'utilisation :
 
 Pour avoir un guide d'utilisation de l'application, au lancement de l'application veuiller utiliser la commande help comme ci-dessous:
 
                  help

## Contibution :

1. Cloner le projet (https://github.com/uvsq21808185/pglp_9.9)
2. Créer votre branche (`git checkout -b feature/Add`)
3. Commiter les changements (`git commit -am 'Add some fooBar'`)
4. Pusher les changements dans votre branche (`git push origin feature/Add`)
5. Créer un nouveau Pull Request

<!-- Markdown link & img dfn's -->
[wiki]: https://github.com/uvsq21808185
