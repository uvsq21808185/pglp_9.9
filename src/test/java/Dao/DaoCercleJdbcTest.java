/**
 * 
 */
package Dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import connexion.Connexion;
import forme.Cercle;
import forme.Point;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class DaoCercleJdbcTest {

	DaoCercleJdbc cjdbc=new DaoCercleJdbc();

	@Before
	public void deleteTable() {
		//vider le tabelau Personnels
		  Connection connexion = Connexion.getConnection();
		  String deleteTableString = "delete from cercles";
		  try {
			  connexion.createStatement().execute(deleteTableString);
			  connexion.close();
			} catch (SQLException e1) {
			  e1.printStackTrace();
			} 
	}

	  /**
	   * teste de la metheode create.
	   */
	  @Test
	  public void Test1create() {
		Point point = new Point(0,0);
		Cercle  r1= new Cercle("c1",point,5); 
	    assertNull(cjdbc.create(r1));
	  }

	  /**
	   * teste de la metheode delete.
	   */
	  @Test
	  public void Test4delete() {
		  Point point3 = new Point(2,2);
		  Cercle r4 = new Cercle("c4",point3,5.0,2);
		  cjdbc.create(r4);
		  cjdbc.delete(r4);
		  assertNull(cjdbc.find("c4"));
	  }
}
