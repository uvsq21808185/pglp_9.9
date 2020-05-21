package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;
import forme.Carre;
import forme.Point;

/**
 * Classe DaoCarree qui implémente les opérations JDBC sur un carré.
 * 
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class DaoCarreJdbc implements Dao<Carre>{

	  /**
	   * un attribut pour établir la connexion.
	   */
	  private Connection connexion = null;

	  /**
	   *la requete da creation de la table Carres.
	   */
	  private String table = "create table carres(name varchar(20) NOT NULL PRIMARY KEY, " 
		      + " x double NOT NULL, y double NOT NULL, side double Not Null,groupId integer)";
			 // +"foreign key (name) references Forme (variableName))";


	  /**
	   * attribut statement.
	   */
	  private Statement statement;

	  /**
	   * Constructeur pour établissement de la connexion.
	   * et la creation de la table carres si elle n'existe pas .
	   */
	  public void CreateDaoTable() {
			connexion = Connexion.getConnection();
	    try {
	      ResultSet res = connexion.getMetaData().getTables(null,null,"carres".toUpperCase(),null);
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
	   * methode pour insérer un tuple dans la table Carres.
	   * @param obj Carre.
	   * @return obj crée sinon null.
	   */
	  public Carre create(Carre obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement create =  null;
	    int status = 0;
	    String insertString = "insert into carres(name, x, y,side,groupId) values (?,?,?,?,?)";
	    try {
	      create = connexion.prepareStatement(insertString);
	      create.setString(1, obj.getName());
	      create.setDouble(2, obj.getLeftTop().getX());
	      create.setDouble(3, obj.getLeftTop().getY());
	      create.setDouble(4, obj.getSide());
	      create.setInt(5, obj.getGroupId());
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
	   * methode pour trouver le tuple carre.
	   * @param id nom du carre.
	   * @return objet carre trouvé ,null sinon.
	   */
	  public Carre find(String id) {
			connexion = Connexion.getConnection();
	    PreparedStatement find =  null;
	    Carre c = null;
	    Point point = null;
	    ResultSet resultat = null;
	    String findString = "select * from carres where name = (?)"; 
	    try {
	      find = connexion.prepareStatement(findString);
	      find.setString(1, id);
	      find.execute();
	      resultat = find.getResultSet();
	      if (resultat.next()) {
	        String nom = resultat.getString("name");
	        double x = resultat.getDouble("x");
	        double y = resultat.getDouble("y");
	        double side = resultat.getDouble("side");
	        int idG = resultat.getInt("groupId");
	        point =new Point(x,y);
	        c = new Carre(nom,point,side,idG);
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
	    return c;
	  }

	  /**
	   * methode pour mettre à jour un tuple carre.
	   * @param obj l'objet pour faire mise à jour.
	   * @return l'objet après la mise à jour.
	   */
	  public Carre update(Carre obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement update =  null;
	    String updateString = "update carres set x = (?), "
	        + "y = (?), side = (?), groupId = (?) where name =(?)";
	    try {
	      update = connexion.prepareStatement(updateString);
	      update.setDouble(1, obj.getLeftTop().getX());
	      update.setDouble(2, obj.getLeftTop().getY());
	      update.setDouble(3, obj.getSide());
	      update.setInt(4, obj.getGroupId());
	      update.setString(5, obj.getName());
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
	    System.out.println("Le carré est bien deplacé");

	    return obj;
	  }

	  /**
	   * methode pour supprimer le tuple de l'objet carre de la base de données.
	   * @param obj carre à supprimer.
	   */
	  public void delete(Carre obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement delete =  null;
	    String deleteString = "delete from carres where name =(?)";
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
	    public ArrayList<Carre> findAll() {
			connexion = Connexion.getConnection();

	        ArrayList<Carre> find = new ArrayList<Carre>();
	        try {
	            PreparedStatement prepare = connexion.prepareStatement(
	                    "SELECT name FROM carres");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("name")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Carre>();
	        }
	        return find;
	    }

	
	}
