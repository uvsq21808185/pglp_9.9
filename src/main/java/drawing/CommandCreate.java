package drawing;

import forme.Shape;


import Dao.Dao;
import Dao.DaoCarreJdbc;
import Dao.DaoCercleJdbc;
import Dao.DaoGroupShapesJdbc;
import Dao.DaoRectangleJdbc;
import Dao.DaoTriangleJdbc;
import forme.Cercle;
import forme.Carre;
import forme.Rectangle;
import forme.Triangle;
import forme.GroupShapes;

/**
 * Classe CommandCreate qui implémente la méthode abstraite execute de l'interface command pour déplacer
 * les formes.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class CommandCreate implements Command{
	/**
     * forme à créer.
     */
    private Shape forme;
    /**
     * constructeur de la classe.
     * @param f forme à créer
     */
    public CommandCreate(final Shape f) {
        forme = f;
    }
    /**
     * execution de la commande create.
     */
    public void execute() {
        Shape f;
        if (forme.getClass() == Cercle.class) {
        	
            Dao<Cercle> dao = new DaoCercleJdbc();
            dao.CreateDaoTable();
            f = dao.create((Cercle) forme);
            
        } else if (forme.getClass() == Carre.class) {
        	
            Dao<Carre> dao = new DaoCarreJdbc();
            dao.CreateDaoTable();
            f = dao.create((Carre) forme);
            
        } else if (forme.getClass() == Rectangle.class) {
        	
            Dao<Rectangle> dao = new DaoRectangleJdbc();
            dao.CreateDaoTable();
            f = dao.create((Rectangle) forme);
            
        } else if (forme.getClass() == Triangle.class) {
        	
            Dao<Triangle> dao =new  DaoTriangleJdbc();
            dao.CreateDaoTable();
            f = dao.create((Triangle) forme);
            
        } else {

            Dao<GroupShapes> dao = new DaoGroupShapesJdbc();
            dao.CreateDaoTable();
            f = dao.create((GroupShapes) forme);
        }
        if (f != null) {
        	
            System.out.println("Création de la forme "
                    + forme.getName() + " réussi.");
        } else {
            System.out.println("Une forme existe déjà à ce nom : "
                    + forme.getName());
        }
    }
}
