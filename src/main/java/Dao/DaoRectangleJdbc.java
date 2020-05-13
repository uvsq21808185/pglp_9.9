/**
 * 
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;
import forme.Point;
import forme.Rectangle;

/**
 * Classe DaoRectangle qui implémente les opérations JDBC sur un rectangle.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class DaoRectangleJdbc implements Dao<Rectangle> {

	  /**
	   * un attribut pour établire la connexion.
	   */
	  private Connection connexion = null;

	  /**
	   * la requte da creation de la table Rectangles.
	   */
	  private String table = "create table rectangles(name varchar(20) NOT NULL PRIMARY KEY, " 
	      + " x double NOT NULL, y double NOT NULL, length double Not Null, width double Not Null,groupId integer)";

	  /**
	   * attribut statemet.
	   */
	  private Statement statement;

	  /**
	   * Constructeur pour établissement de la connexion.
	   * et la creation de la table si elle n'existe pas .
	   */
	  public DaoRectangleJdbc() {
			connexion = Connexion.getConnection();
	    try {
	      ResultSet res = connexion.getMetaData().getTables(null,null,"rectangles".toUpperCase(),null);
	      statement = connexion.createStatement();
	      if (!res.next()) {
	        statement.execute(table);
	      }
	      statement.close();
	      connexion.close();
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	  }

	  /**
	   * methode pour insérer un tuple dans la table Rectangles.
	   * @param obj Rectangle.
	   * @return obj crée sinon null.
	   */
	  public Rectangle create(Rectangle obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement create =  null;
	    int status = 0;
	    String insertString = "insert into rectangles(name, x, y,length,width,groupId) values (?,?,?,?,?,?)";
	    try {
	      create = connexion.prepareStatement(insertString);
	      create.setString(1, obj.getName());
	      create.setDouble(2, obj.getLeftTop().getX());
		  create.setDouble(3, obj.getLeftTop().getY());
		  create.setDouble(4, obj.getLength());
		  create.setDouble(5, obj.getWidth());
		  create.setInt(6, obj.getGroupId());
		  status = create.executeUpdate();
		  connexion.close();
		} catch (SQLException e) {
	      e.printStackTrace();
	    }
	    try {
	      if (create != null) {
	        create.close();
	      }
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	    if (status > 0) {
	      return obj;
	    } else {
	      return null;
		}
	  }

	  /**
	   * methode pour trouver le tuple rectangle.
	   * @param id nom du rectangle.
	   * @return objet rectangle trouvé ,null sinon.
	   */
	  public Rectangle find(String id) {
			connexion = Connexion.getConnection();
	    PreparedStatement find =  null;
	    Rectangle r = null;
	    Point point = null;
	    ResultSet resultat = null;
	    String findString = "select * from rectangles where name = (?)"; 
	    try {
	      find = connexion.prepareStatement(findString);
	      find.setString(1, id);
	      find.execute();
	      resultat = find.getResultSet();
	      if (resultat.next()) {
	        String nom = resultat.getString("name");
	        double x = resultat.getDouble("x");
	        double y = resultat.getDouble("y");
	        double length = resultat.getDouble("length");
	        double width = resultat.getDouble("width");
	        int idG = resultat.getInt("groupId");
	        point =new Point(x,y);
	        r = new Rectangle(nom,point,length,width,idG);
	        connexion.close();
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    try {
	      if (find != null) {
	        find.close();
	      }
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	    return r;
	  }

	  /**
	   * methode pour mettre à jour un tuple rectangle.
	   * @param obj l'objet pour faire mise à jour.
	   * @return l'objet après la mise à jour.
	   */
	  public Rectangle update(Rectangle obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement update =  null;
	    String updateString = "update rectangles set x = (?), "
	        + "y = (?), length = (?),width = (?), groupId = (?) where name =(?)";
	    try {
	      update = connexion.prepareStatement(updateString);
	      update.setDouble(1, obj.getLeftTop().getX());
		  update.setDouble(2, obj.getLeftTop().getY());
		  update.setDouble(3, obj.getLength());
		  update.setDouble(4, obj.getWidth());
		  update.setInt(5, obj.getGroupId());
		  update.setString(6, obj.getName());
	      update.executeUpdate();
	      connexion.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    try {
	      if (update != null) {
	        update.close();
	      }
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	    return obj;
	  }

	  /**
	   * methode pour supprimer le tuple de l'objet rectangle de la base de données.
	   * @param obj rectangle à supprimer.
	   */
	  public void delete(Rectangle obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement delete =  null;
	    String deleteString = "delete from rectangles where name =(?)";
	    try {
	      delete = connexion.prepareStatement(deleteString);
	      delete.setString(1, obj.getName());
	      delete.executeUpdate();
	      connexion.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    try {
	      if (delete != null) {
	        delete.close();
	      }
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	  }
	  /**
	     * obtenir tous les éléments.
	     * @return tous les éléments
	     */
	    public ArrayList<Rectangle> findAll() {
			connexion = Connexion.getConnection();

	        ArrayList<Rectangle> find = new ArrayList<Rectangle>();
	        try {
	            PreparedStatement prepare = connexion.prepareStatement(
	                    "SELECT name FROM rectangles");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("name")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Rectangle>();
	        }
	        return find;
	    }
	}
