package drawing;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.Dao;
import Dao.DaoCarreJdbc;
import Dao.DaoCercleJdbc;
import Dao.DaoGroupShapesJdbc;
import Dao.DaoRectangleJdbc;
import Dao.DaoTriangleJdbc;
import connexion.Connexion;
import forme.Carre;
import forme.Cercle;
import forme.GroupShapes;
import forme.Point;
import forme.Rectangle;
import forme.Shape;
import forme.Triangle;
/**
 * Classe DrawingTUI
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class DrawingTUI {
    /**
     * interprète la commande de création d'un carré.
     * @param variableName nom de la variable
     * @param split2 données après le '='
     * @return le carré ou null en cas d'erreurs
     */
    private Shape creerCarre(
            final String variableName, final String[] split2) {
        final int trois = 3;
        String[] split = split2[1].split("Carre");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != trois) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + trois + " arguments");
            } else {
                Point topLeft;
                int longueur;
                try {
                    topLeft = new Point(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    return new Carre(variableName, topLeft, longueur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
    }
    /**
     * interprète la commande de création d'un cercle.
     * @param variableName nom de la variable
     * @param split2 données après le '='
     * @return le cercle ou null en cas d'erreurs
     */
    private Shape creerCercle(
            final String variableName, final String[] split2) {
        final int trois = 3;
        String[] split = split2[1].split("Cercle");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != trois) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + trois + " arguments");
            } else {
                Point centre;
                int rayon;
                try {
                    centre = new Point(split[0] + "," + split[1]);
                    rayon = Integer.parseInt(split[2]);
                    return new Cercle(variableName, centre, rayon);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
    }
    /**
     * interprète la commande de création d'un triangle.
     * @param variableName nom de la variable
     * @param split2 données après le '='
     * @return le triangle ou null en cas d'erreurs
     */
    
    private Shape creerTriangle(
            final String variableName, final String[] split2) {
        final int six = 6;
        String[]  split = split2[1].split("Triangle");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != six) {
                System.err.println("Commande invalide, "
            + split.length + "/" + six + " arguments");
            }
            Point[] point = {null, null, null};
            try {
                final int trois = 3, quatre = 4, cinq = 5;
                point[0] = new Point(split[0] + "," + split[1]);
                point[1] = new Point(split[2] + "," + split[trois]);
                point[2] = new Point(split[quatre] + "," + split[cinq]);
                return new Triangle(variableName, point[0], point[1], point[2]);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Commande invalide, "
                        + "impossible de créer la forme");
            }
        }
        return null;
    }
    /**
     * interprète la commande de création d'un rectangle.
     * @param variableName nom de la variable
     * @param split2 données après le '='
     * @return le rectangle ou null en cas d'erreurs
     */
    private Shape creerRectangle(
            final String variableName, final String[] split2) {
        final int quatre = 4;
        String[] split = split2[1].split("Rectangle");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != quatre) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + quatre + " arguments");
            } else {
                Point topLeft;
                int longueur;
                int largeur;
                try {
                    final int trois = 3;
                    topLeft = new Point(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    largeur = Integer.parseInt(split[trois]);
                    return new Rectangle(variableName, topLeft, longueur, largeur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
    }
    /**
     * interprète la commande de création d'un groupe.
     * @param variableName nom de la variable
     * @param split2 données après le '='
     * @return le groupe ou null en cas d'erreurs
     */
    private Shape creerGroupe(
            final String variableName, final String[] split2) {
        String[] split = split2[1].split("Groupe");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            return creerGroupeComposants(variableName, split);
        }
        return null;
    }
    /**
     * obtenir la forme avec son nom de variable.
     * @param variableName nom de la forme
     * @return la forme
     */
    private Shape getForme(final String variableName) {

        Dao<Cercle> daoCe = new DaoCercleJdbc();
        Dao<Carre> daoCa = new DaoCarreJdbc();
        Dao<Rectangle> daoR = new DaoRectangleJdbc();
        Dao<Triangle> daoT = new DaoTriangleJdbc();
        Dao<GroupShapes> daoG = new DaoGroupShapesJdbc();
        Shape f = daoCe.find(variableName);
        if (f == null) {
            f = daoCa.find(variableName);
        }
        if (f == null) {
            f = daoR.find(variableName);
        }
        if (f == null) {
            f = daoT.find(variableName);
        }
        if (f == null) {
            f = daoG.find(variableName);
        }
        if (f == null) {
            System.err.println("Aucune forme n'existe à ce nom : "
                    + variableName);
        }
        return f;
    }
    /**
     * interprète la commande de création d'un groupe (partie composants).
     * @param variableName nom de la variable
     * @param split données après le '='
     * @return le groupe ou null en cas d'erreurs
     */
	private Shape creerGroupeComposants(
            final String variableName, final String[] split) {
        GroupShapes gf = new GroupShapes(variableName);
        for (String s : split) {
            Shape f = this.getForme(s);
            if (f != null) {
                gf.add(f);
            } else {
                return null;
            }
        }
        return gf;
    }
    /**
     * interprète la commande de création de forme.
     * @param cmd2 la commande
     * @return la forme générée
     */
    private Shape create(final String cmd2) {
        String[] split;
        split = cmd2.split("=");
        split[0] = split[0].trim();
        String variableName = split[0];
        if (split[0].contains(" ")) {
            System.out.println("Le nom de la variable contient des espaces");
        } else {
            split[1] = split[1].replace(" ", "");
            Shape f = null;
            if (split[1].contains("Cercle")) {
                f = this.creerCercle(variableName, split);
            } else if (split[1].contains("Carre")) {
                f = this.creerCarre(variableName, split);
            } else if (split[1].contains("Rectangle")) {
                f = this.creerRectangle(variableName, split);
            } else if (split[1].contains("Triangle")) {
               f = this.creerTriangle(variableName, split);
            } else if (split[1].contains("Groupe")) {
                f = this.creerGroupe(variableName, split);
            }
            return f;
        }
        return null;
    }
    /**
     * interprète la commande de déplacement d'une forme.
     * @param cmd2 commande de déplacement
     * @return la commande de déplacement
     */
    private Command move(final String cmd2) {
        final int trois = 3;
        String cmd = cmd2.replace(" ", "");
        String[] split = cmd.split("move");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != trois) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + trois + " arguments");
            } else {
                String variableName;
                Point deplacement;
                try {
                    variableName = split[0];
                    deplacement = new Point(split[1] + "," + split[2]);
                    Shape f = this.getForme(variableName);
                    if (f != null) {
                        return new CommandMove(f, deplacement);
                    }
                } catch (Exception e) {
                    System.err.println("Commande invalide");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * interprète la commande de suppression de formes.
     * @param cmd2 commande de suppression
     * @return la commande de suppression
     */
    private Command remove(final String cmd2) {
        String cmd = cmd2.replace(" ", "");
        String[] split = cmd.split("delete");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            ArrayList<Shape> list = new ArrayList<Shape>();
            for (String var : split) {
                Shape f = this.getForme(var);
                if (f != null) {
                    list.add(f);
                } else {
                    System.err.println("Commande invalide, forme inconnu");
                    return null;
                }
            }
            return new CommandRemove(list);
        }
        return null;
    }
    /**
     * interprète une commande.
     * @param cmd commande à interpréter.
     * @return une commande à exécuter ou null s'il n'y en a pas.
     */
    public Command nextCommand(final String cmd) {
        if (cmd.contains("=")) {
            Shape f = this.create(cmd);
            if (f != null) {
                return new CommandCreate(f);
            }
        } else if (cmd.contains("move")) {
            return this.move(cmd);
       } else if (cmd.contains("delete")) {
            return this.remove(cmd);
        } else if (cmd.equals("start")) {
            System.out.println("Commandes disponibles : \n \n"
            		+"***************************************MENU********************************** \n"
                    +"*"+" Dessiner un cercle    :     nom_cercle = Cercle((x,y), rayon)             * \n"
                    +"*"+" Dessiner un carré     :     nom_carre = Carre((x,y), longueur)            * \n"
                    +"*"+" Dessiner un rectangle :     nom_rectangle  = Rectangle((x,y), long, larg) * \n"
                    +"*"+" Dessiner un triangle  :     nom_triangle = Triangle((x,y), (x,y), (x,y))  * \n"
                    +"*"+" Dessiner un groupe    :     nom_groupe = Groupe(variableName, ...)        * \n"
                    +"*"+"                                                                           *\n"
                    +"*"+" Déplacer une forme ou un groupe :  move(variableName, (x,y))              * \n"
                    +"*"+"                                                                           * \n"
                    +"*"+" Supprimer une forme ou un groupe : delete(variableName, ...)              * \n"
            		+"***************************************************************************** \n");
        } else if (!cmd.equalsIgnoreCase("exit")) {
            System.err.println("Commande non reconnu");
        }
        return null;
    }
    /**
     * indique si une forme est contenu dans un groupe.
     * @param f forme pour rechercher
     * @return vrai si la forme est dans un groupe
     */
   
    @SuppressWarnings("unused")
	private boolean estDansUnGroupe(final Shape f) {
        Connection connect = Connexion.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * "
                    + "FROM Composition WHERE idComposant = ?");
            prepare.setString(1, f.getName());
            ResultSet result = prepare.executeQuery();
            boolean b = result.next();
            connect.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connect.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }
    /**
     * affiche toutes les formes du dessin (sauf les groupes).
     */
  
    public void afficheDessin() {

       Dao<Cercle> daoCercle = new DaoCercleJdbc();
       Dao<Carre> daoCarree = new DaoCarreJdbc();
       Dao<Rectangle> daoRectangle = new DaoRectangleJdbc();
       Dao<Triangle> daoTriangle = new DaoTriangleJdbc();
       Dao<GroupShapes> daoGroupShapes = new DaoGroupShapesJdbc();
        
        ArrayList<Shape> formes = new ArrayList<Shape>();
        formes.addAll(daoCercle.findAll());
        formes.addAll(daoCarree.findAll());
        formes.addAll(daoRectangle.findAll());
        formes.addAll(daoTriangle.findAll());
        formes.addAll(daoGroupShapes.findAll());

        System.out.println("\n Le dessin contient : \n");

        for (Shape f : formes) {
            if (!this.estDansUnGroupe(f)) {

                System.out.println(f.Affiche()+"\n");
           }
        }
    }
   
}