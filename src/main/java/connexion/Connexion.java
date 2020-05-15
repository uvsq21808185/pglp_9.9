package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class Connexion {
	
	private static String nomBdd = "bdd" ;
	
	public Connexion() {};
	/**
	   * methode pour Connecter à la base de données.
	   */
	  public static Connection getConnection() {
	    Connection connexion = null;
	    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	    try {
	      Class.forName(driver);
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	    try {
	      connexion = DriverManager.getConnection("jdbc:derby:"+nomBdd +";create=false");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return connexion;
	  }
	  
	  public static void setNomBdd(final String name) {
		  nomBdd=name;
	  }
	

}
