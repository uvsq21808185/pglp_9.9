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
import forme.Carre;
import forme.Point;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class DaoCarreJdbcTest {


	DaoCarreJdbc cajdbc=new DaoCarreJdbc();

	@Before
	public void deleteTable() {
		//vider le tabelau Personnels
		  Connection connexion = Connexion.getConnection();
		  String deleteTableString = "delete from carres";
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
		Carre  r1= new Carre("ca1",point,5,1); 
	    assertNotNull(cajdbc.create(r1));
	  }
	  /**
	   * teste de la metheode find.
	   */
	  @Test
	  public void Test2find() {
	    Point point = new Point(0,0);
		Carre  ca2= new Carre("ca2",point,2,2);
		cajdbc.create(ca2);
	    Carre r=cajdbc.find("ca2");
	    assertEquals(r.getName(),"ca2");
	    Double x = r.getLeftTop().getX();
	    Double y = r.getLeftTop().getY();
	    Double cote = r.getSide();
	    assertTrue(x.equals(0.0));
	    assertTrue(y.equals(0.0));
	    assertTrue(cote.equals(2.0));
	    assertEquals(r.getGroupId(),2);
	  }

	  /**
	   * teste de la metheode update.
	   */
	  @Test
	  public void Test3upDate() {
		  Point p = new Point(5,8);
		  Carre  ca3= new Carre("ca3",p,8.3,2);
		  cajdbc.create(ca3);
		  Carre  cas = new Carre("ca3",p,6,3);
		  cajdbc.update(cas);
		  Carre c = cajdbc.find("ca3");
		  assertEquals(c.getName(),"ca3");
		  Double x = c.getLeftTop().getX();
		  Double y = c.getLeftTop().getY();
		  Double cote = c.getSide();
		  assertTrue(x.equals(5.0));
		  assertTrue(y.equals(8.0));
		  assertTrue(cote.equals(6.0));
		  assertEquals(c.getGroupId(),3);
	  }

	  /**
	   * teste de la metheode delete.
	   */
	  @Test
	  public void Test4delete() {
		  Point point3 = new Point(2,2);
		  Carre r4 = new Carre("ca4",point3,5.0,2);
		  cajdbc.create(r4);
		  cajdbc.delete(r4);
		  assertNull(cajdbc.find("ca4"));
	  }

}
