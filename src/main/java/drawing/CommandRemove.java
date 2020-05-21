package drawing;

import java.sql.Connection;
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
import forme.Rectangle;
import forme.Shape;
import forme.Triangle;

/**
  * commande pour supprimer une ou plusieurs formes.
  */
public class CommandRemove implements Command {
    /**
     * liste des formes à supprimer.
     */
    private ArrayList<Shape> list;
    /**
     * constructeur de la classe.
     * @param f liste des formes à retirer
     */
    public CommandRemove(final ArrayList<Shape> f) {
        list = f;
    }
    /**
     * execution de la commande.
     */
    public void execute() {
        for (Shape forme : list) {
            if (forme.getClass() == Cercle.class) {
            	
                Dao<Cercle> dao = new DaoCercleJdbc();
                dao.delete((Cercle) forme);
                
            } else if (forme.getClass() == Carre.class) {
            	
                Dao<Carre> dao = new DaoCarreJdbc();
                dao.delete((Carre) forme);
                
            } else if (forme.getClass() == Rectangle.class) {
            	
                Dao<Rectangle> dao = new DaoRectangleJdbc();
                dao.delete((Rectangle) forme);
                
            } else if (forme.getClass() == Triangle.class) {
            	
                Dao<Triangle> dao = new DaoTriangleJdbc();
                dao.delete((Triangle) forme);
                
            } else {
                Dao<GroupShapes> dao = new DaoGroupShapesJdbc();
                dao.delete((GroupShapes) forme);
            }
        }
    }
}
