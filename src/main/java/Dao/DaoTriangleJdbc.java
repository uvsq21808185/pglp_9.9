package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;
import forme.Point;
import forme.Triangle;

/**
 * Classe DaoTriangle qui implémente les opérations JDBC sur un triangle.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class DaoTriangleJdbc implements Dao<Triangle> {

	  /**
	   * un attribut pour établire la connexion.
	   */
	  private Connection connexion = null;

	  /**
	   * la requete da creation de la table Triangles.
	   */
	  @SuppressWarnings("unused")
	private String table = "create table triangles(name varchar(20) NOT NULL PRIMARY KEY, " 
		      + " x double NOT NULL, y double NOT NULL, base double Not Null, height double Not Null,groupId integer)";
			 // +"foreign key (name) references Forme (variableName))";
	  
	  private String table2 ="create table triangles(name varchar(20) NOT NULL PRIMARY KEY,"
			  +"p1x double, p1y double, p2x double, p2y double,p3x double , p3y double)";
	  /**
	   * attribut statemet.
	   */
	  private Statement statement;

	  /**
	   * Constructeur pour établissement de la connexion.
	   * et la creation de la table si elle n'existe pas .
	   */
	  public void CreateDaoTable() {
			connexion = Connexion.getConnection();
	    try {
	      ResultSet res = connexion.getMetaData().getTables(null,null,"triangles".toUpperCase(),null);
	      statement = connexion.createStatement();
	      if (!res.next()) {
	        statement.execute(table2);
	      }
	      statement.close();
	      connexion.close();
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	  }

	  /**
	   * methode pour insérer un tuple dans la table Triangles.
	   * @param obj Rectangle.
	   * @return obj crée sinon null.
	   */
	  public Triangle create(Triangle obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement create =  null;
	    int status = 0;
	    String insertString = "insert into triangles(name, p1x, p1y,p2x,p2y,p3x,p3y) values (?,?,?,?,?,?,?)";
	    try {
	      create = connexion.prepareStatement(insertString);
	      create.setString(1, obj.getName());
	      create.setDouble(2, obj.getPoint(0).getX());
	      create.setDouble(3, obj.getPoint(0).getY());
	      create.setDouble(4, obj.getPoint(1).getX());
	      create.setDouble(5, obj.getPoint(1).getY());
	      create.setDouble(6, obj.getPoint(2).getX());
	      create.setDouble(7, obj.getPoint(2).getY());

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
	   * methode pour trouver le tuple triangle.
	   * @param id nom du triangle.
	   * @return objet triangle trouvé ,null sinon.
	   */
	  public Triangle find(String id) {
			connexion = Connexion.getConnection();
	    PreparedStatement find =  null;
	    Triangle t = null;
	    Point point1 = null;
	    Point point2 = null;
	    Point point3 = null;

	    ResultSet resultat = null;
	    String findString = "select * from triangles where name = (?)"; 
	    try {
	      find = connexion.prepareStatement(findString);
	      find.setString(1, id);
	      find.execute();
	      resultat = find.getResultSet();
	      if (resultat.next()) {
	        String nom = resultat.getString("name");
	        double x1 = resultat.getDouble("p1x");
	        double y1 = resultat.getDouble("p1y");
	        double x2 = resultat.getDouble("p2x");
	        double y2 = resultat.getDouble("p2y");
	        double x3 = resultat.getDouble("p3x");
	        double y3 = resultat.getDouble("p3y");
	        
	        point1 =new Point(x1,y1);
	        point2 =new Point(x2,y2);
	        point3 =new Point(x3,y3);
	        
	        t = new Triangle(nom,point1,point2,point3);
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
	    return t;
	  }

	  /**
	   * methode pour mettre à jour un tuple triangle.
	   * @param obj l'objet pour faire mise à jour.
	   * @return l'objet après la mise à jour.
	   */
	  public Triangle update(Triangle obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement update =  null;
	    String updateString = "update triangles set p1x = (?), "
		        + "p1y = (?), p2x = (?),p2y = (?), p3x = (?), p3y = (?) where name =(?)";
	    try {
	      update = connexion.prepareStatement(updateString);
	      update.setDouble(1, obj.getPoint(0).getX());
	      update.setDouble(2, obj.getPoint(0).getY());
	      update.setDouble(3, obj.getPoint(1).getX());
	      update.setDouble(4, obj.getPoint(1).getY());
	      update.setDouble(5, obj.getPoint(2).getX());
	      update.setDouble(6, obj.getPoint(2).getY());
	      update.setString(7, obj.getName());
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
	   * methode pour supprimer le tuple de l'objet triangle de la base de données.
	   * @param obj triangle à supprimer.
	   */
	  public void delete(Triangle obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement delete =  null;
	    String deleteString = "delete from triangles where name =(?)";
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
	    public ArrayList<Triangle> findAll() {
			connexion = Connexion.getConnection();

	        ArrayList<Triangle> find = new ArrayList<Triangle>();
	        try {
	            PreparedStatement prepare = connexion.prepareStatement(
	                    "SELECT name FROM triangles");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("name")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Triangle>();
	        }
	        return find;
	    }

	}