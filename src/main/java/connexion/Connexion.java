package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe Connexion pour se connecter à la base de donnée et créer les tables.
 * @author ZAOUAM Sirageddine
 *
 */
public class Connexion {
		private static  String nomBdd="DessinBDD";
	
		
	/**
	   * methode pour Connecter à la base de données.
	   * @return connexion à la base de donnée
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
	      connexion = DriverManager.getConnection("jdbc:derby:"+nomBdd+";create=false");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return connexion;
	  }
	 /* 
	public void modifierNomBdd(String newBdd) {
		  Connexion.nomBdd=newBdd;
	  }
	  */
	  /**
	     * créer la bdd.
	     * @exception SQLException erreur de création
	     */
	    public static void createDataBase() throws SQLException  {
	        Connection c;
	        try {
	            c = DriverManager.getConnection(
	                "jdbc:derby:" + nomBdd + ";create=true");
	            c.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * créer la table de composition entre groupeForme et GroupeForme.
	     * @param connect connexion a la bdd
	     * @exception SQLException erreur sql
	     */
		public static void CreateTableComposition(final Connection connect) throws SQLException{
	        String table = "create table Composition ("
	                + "idGroupe varchar(30),"
	                + "idComposant varchar(30),"
	                + "primary key (idGroupe, idComposant),"
	               // + "foreign key (idGroupe) references GroupeForme(nomGroupe), "
	                + "foreign key (idComposant) "
	                + "references Forme (variableName)"
	                + ")";
	        Statement stat;
				stat = connect.createStatement();
		        stat.execute(table);

		}
	    /**
	     * créer la table Forme.
	     * @param connect connexion a la bdd
	     * @throws SQLException erreur sql
	     */
		public static void CreateTableForme(final Connection connect)
	            throws SQLException {
	        String table = "create table Forme ("
	                + "variableName varchar(30) primary key not null"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	    
	    public static void deleteTables(final Connection connect) {
	        Statement stat = null;
	        try {
	            stat = connect.createStatement();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            stat.execute("drop table Composition");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table groupshapes");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table cercles");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table rectangles");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table carres");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Triangle");
	        } catch (SQLException e) {
	        }
	        try {
	            stat.execute("drop table Forme");
	        } catch (SQLException e) {
	        }
	    }

}
