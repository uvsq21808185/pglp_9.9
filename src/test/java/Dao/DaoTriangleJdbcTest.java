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
import forme.Point;
import forme.Triangle;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class DaoTriangleJdbcTest {

	DaoTriangleJdbc trjdbc=new DaoTriangleJdbc();

	@Before
	public void deleteTable() {
		//vider le tabelau Personnels
		  Connection connexion = Connexion.getConnection();
		  String deleteTableString = "delete from triangles";
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
		Point point1 = new Point(0,0);
		Point point2 = new Point(0,0);

		Triangle  tr1= new Triangle("tr1",point,point1,point2); 
	    assertNotNull(trjdbc.create(tr1));
	  }
	  /**
	   * teste de la metheode find.
	   */
	  @Test
	  public void Test2find() {
	    Point point = new Point(0,0);
		Point point1 = new Point(2,1);
		Point point2 = new Point(3,0);

		Triangle  tr2= new Triangle("tr2",point,point1,point2);
		trjdbc.create(tr2);
	    Triangle tr = trjdbc.find("tr2");
	    assertEquals(tr.getName(),"tr2");
	    Double x = tr.getPoint(0).getX();
	    Double y = tr.getPoint(0).getY();
	    assertTrue(x.equals(0.0));
	    assertTrue(y.equals(0.0));
	  }

	  /**
	   * teste de la metheode update.
	   */
	  @Test
	  public void Test3upDate() {
		  
		Point point = new Point(0,0);
		Point point1 = new Point(2,1);
		Point point2 = new Point(3,0);	
		Triangle  tr3= new Triangle("tr3",point,point1,point2);
		  trjdbc.create(tr3);
		  Triangle  rs = new Triangle("tr3",point2,point1,point);
		  trjdbc.update(rs);
		  Triangle tr = trjdbc.find("tr3");
		  assertEquals(tr.getName(),"tr3");
		  Double x = tr.getPoint(0).getX();
		  Double y = tr.getPoint(0).getY();
		  assertTrue(x.equals(3.0));
		  assertTrue(y.equals(0.0));
	  }

	  /**
	   * teste de la metheode delete.
	   */
	  @Test
	  public void Test4delete() {
		  Point point1 = new Point(2,1);
		  Point point2 = new Point(3,0);			
		  Triangle tr4 = new Triangle("tr4",point2,point2,point1);
		  trjdbc.create(tr4);
		  trjdbc.delete(tr4);
		  assertNull(trjdbc.find("tr4"));
	  }


}
