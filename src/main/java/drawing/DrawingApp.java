/**
 * 
 */
package drawing;

import java.sql.Connection;
import java.util.Scanner;

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
import forme.Triangle;

/** Classe principale du programme dessin contenant le main.
 * @author ZAOUAM Sirageddine
 * @version 2.0
 *
 */
public class DrawingApp {

    private Scanner saisie;

    private DrawingTUI dt;
    
    /**
     * constructeur de la classe.
     */
    public DrawingApp() {
        dt = new DrawingTUI();
        saisie = new Scanner(System.in);
    }
    /**
     * exécution des commandes.
     */
    public void run() {
        System.out.print("Entrer (start) pour commencer :) : \n");
        String cmd = saisie.nextLine();
        Command c;
        while (!cmd.equalsIgnoreCase("exit")) {
            c = dt.nextCommand(cmd);
            if (c != null) {
                c.execute();
                dt.afficheDessin();

            }
           // dt.afficheDessin();
            cmd = saisie.nextLine();
        }
    }  
    
    /**
     * début du programme.
     * @param args arguments
     * @throws Exception erreur bdd
     */
    public static void main(final String[] args) throws Exception {
        		Connexion.createDataBase();
    			Connection connect = Connexion.getConnection();
    			Connexion.deleteTables(connect);
        		Connexion.CreateTableForme(connect);
        
                Connexion.CreateTableComposition(connect); 
           	    Dao<Cercle> daoCercle = new DaoCercleJdbc();
           	    daoCercle.CreateDaoTable();
        	    Dao<Carre> daoCarree = new DaoCarreJdbc();
        	    daoCarree.CreateDaoTable();
        	    Dao<Rectangle> daoRectangle = new DaoRectangleJdbc();
        	    daoRectangle.CreateDaoTable();
        	    Dao<Triangle> daoTriangle = new DaoTriangleJdbc();
        	    daoTriangle.CreateDaoTable();   
        	    Dao<GroupShapes> daoGroupShapes = new DaoGroupShapesJdbc();
         	    daoGroupShapes.CreateDaoTable();
        	    DrawingApp app = new DrawingApp();
                app.run();
    }	
}
