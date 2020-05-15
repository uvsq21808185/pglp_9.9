/**
 * 
 */
package drawing;

import Dao.Dao;
import Dao.DaoCarreJdbc;
import Dao.DaoCercleJdbc;
import Dao.DaoGroupShapesJdbc;
import Dao.DaoRectangleJdbc;
import Dao.DaoTriangleJdbc;
import forme.Carre;
import forme.Cercle;
import forme.GroupShapes;
import forme.Point;
import forme.Rectangle;
import forme.Shape;
import forme.Triangle;

/**
 * Classe CommandMove qui implémente la méthode abstraite execute de l'interface Command
 * pour déplacer une forme ou un groupe de formes.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class CommandMove implements Command {
	 /**
     * forme à déplacer.
     */
    private Shape forme;
    /**
     * vecteur de deplacement de la forme.
     */
    private Point vecteur;
    /**
     * constructeur de la classe CommandMove.
     * @param f forme à déplacer
     * @param vecteur vecteur de déplacement de la forme
     */
    public CommandMove(final Shape f, final Point vecteur) {
        this.vecteur = vecteur;
        forme = f;
    }
    /**
     * execution de la commande move.
     */
    public void execute() {
        forme.move(vecteur.getX(), vecteur.getY());
        if (forme.getClass() == Cercle.class) {
            Dao<Cercle> dao = new DaoCercleJdbc();
            dao.update((Cercle) forme);
        } else if (forme.getClass() == Carre.class) {
            Dao<Carre> dao = new DaoCarreJdbc();
            dao.update((Carre) forme);
        } else if (forme.getClass() == Rectangle.class) {
            Dao<Rectangle> dao = new DaoRectangleJdbc();
            dao.update((Rectangle) forme);
        } else if (forme.getClass() == Triangle.class) {
            Dao<Triangle> dao = new DaoTriangleJdbc();
            dao.update((Triangle) forme);
        } else {
            Dao<GroupShapes> dao = new DaoGroupShapesJdbc();
            dao.update((GroupShapes) forme);
        }
    }
}
