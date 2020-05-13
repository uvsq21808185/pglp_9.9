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
import forme.Cercle;
import forme.Point;
/**
 * Classe DaoCercle qui implémente les opérations JDBC sur un cercle.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */

public class DaoCercleJdbc implements Dao<Cercle>{

  /**
   * un attribut pour établire la connexion.
   */
  private Connection connexion = null;

  /**
   * la requte da creation de la table Cercles.
   */
  private String table = "create table cercles(name varchar(20) NOT NULL PRIMARY KEY, " 
      + " x double NOT NULL, y double NOT NULL, rayon double Not Null,groupId integer)";
  
  private String insertString = "insert into cercles(name, x, y, rayon,groupId) values (?,?,?,?,?)";
  private String findString = "select * from cercles where name = (?)"; 
  private String updateString = "update cercles set x = (?), "
	        + "y = (?), rayon = (?), groupId = (?) where name =(?)";
  private String deleteString = "delete from cercles where name =(?)";


  /**
   * attribut statemet.
   */
  private Statement statement;

  /**
   * Constructeur pour établissement de la connexion.
   * et la creation de la table si elle n'existe pas .
   */
  public DaoCercleJdbc() {
	connexion = Connexion.getConnection();
    try {
      ResultSet res = connexion.getMetaData().getTables(null,null,"cercles".toUpperCase(),null);
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
   * methode pour insérer un tuple dans la table Cercles.
   * @param obj Carre.
   * @return obj crée sinon null.
   */
  public Cercle create(Cercle obj) {
    connexion = Connexion.getConnection();
    PreparedStatement create =  null;
    int status = 0;
    try {
      create = connexion.prepareStatement(insertString);
      create.setString(1, obj.getName());
      create.setDouble(2, obj.getCentre().getX());
      create.setDouble(3, obj.getCentre().getY());
      create.setDouble(4, obj.getRayon());
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
   * methode pour trouver le tuple cercle.
   * @param id nom du cercle.
   * @return objet cercle trouvé ,null sinon.
   */
  public Cercle find(String id) {
	connexion = Connexion.getConnection();
    PreparedStatement find =  null;
    Cercle c = null;
    Point point = null;
    ResultSet resultat = null;
    try {
      find = connexion.prepareStatement(findString);
      find.setString(1, id);
      find.execute();
      resultat = find.getResultSet();
      if (resultat.next()) {
        String nom = resultat.getString("name");
        double x = resultat.getDouble("x");
        double y = resultat.getDouble("y");
        double redius = resultat.getDouble("rayon");
        int idG = resultat.getInt("groupId");
        point = new Point(x,y);
        c = new Cercle(nom,point,redius,idG);
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
   * methode pour mettre à jour un tuple cercle.
   * @param obj l'objet pour faire mise à jour.
   * @return l'objet après la mise à jour.
   */
  public Cercle update(	Cercle obj) {
	connexion = Connexion.getConnection();
    PreparedStatement update =  null;
    try {
      update = connexion.prepareStatement(updateString);
      update.setDouble(1, obj.getCentre().getX());
      update.setDouble(2, obj.getCentre().getY());
      update.setDouble(3, obj.getRayon());
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
    return obj;
  }

  /**
   * methode pour supprimer le tuple de l'objet cercle de la base de données.
   * @param obj cercle à supprimer.
   */
  public void delete(Cercle obj) {
	connexion = Connexion.getConnection();
    PreparedStatement delete =  null;
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
  public ArrayList<Cercle> findAll() {
		connexion = Connexion.getConnection();

      ArrayList<Cercle> find = new ArrayList<Cercle>();
      try {
          PreparedStatement prepare = connexion.prepareStatement(
                  "SELECT name FROM cercles");
          ResultSet result = prepare.executeQuery();
          while (result.next()) {
              find.add(this.find(result.getString("name")));
          }
      } catch (SQLException e) {
          e.printStackTrace();
          return new ArrayList<Cercle>();
      }
      return find;
  }
}
