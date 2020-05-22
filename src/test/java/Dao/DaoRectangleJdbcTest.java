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
import forme.Rectangle;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class DaoRectangleJdbcTest {


	DaoRectangleJdbc rjdbc=new DaoRectangleJdbc();

	@Before
	public void deleteTable() {
		//vider le tabelau Personnels
		  Connection connexion = Connexion.getConnection();
		  String deleteTableString = "delete from rectangles";
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
		Rectangle  r1= new Rectangle("r1",point,5,4); 
	    assertNotNull(rjdbc.create(r1));
	  }
	  /**
	   * teste de la metheode find.
	   */
	  @Test
	  public void Test2find() {
	    Point point = new Point(0,0);
		Rectangle  r2= new Rectangle("r2",point,2,3);
		rjdbc.create(r2);
	    Rectangle r=rjdbc.find("r2");
	    assertEquals(r.getName(),"r2");
	    Double x = r.getLeftTop().getX();
	    Double y = r.getLeftTop().getY();
	    Double length = r.getLength();
	    Double width = r.getWidth();
	    assertTrue(x.equals(0.0));
	    assertTrue(y.equals(0.0));
	    assertTrue(length.equals(2.0));
	    assertTrue(width.equals(3.0));
	  }

	  /**
	   * teste de la metheode update.
	   */
	  @Test
	  public void Test3upDate() {
		  Point p = new Point(5,8);
		  Rectangle  r3= new Rectangle("r3",p,8,4);
		  rjdbc.create(r3);
		  Rectangle  rs = new Rectangle("r3",p,5,6);
		  rjdbc.update(rs);
		  Rectangle r = rjdbc.find("r3");
		  assertEquals(r.getName(),"r3");
		  Double x = r.getLeftTop().getX();
		  Double y = r.getLeftTop().getY();
		  Double length = r.getLength();
		  Double width = r.getWidth();
		  assertTrue(x.equals(5.0));
		  assertTrue(y.equals(8.0));
		  assertTrue(length.equals(5.0));
		  assertTrue(width.equals(6.0));
	  }

	  /**
	   * teste de la metheode delete.
	   */
	  @Test
	  public void Test4delete() {
		  Point point3 = new Point(2,2);
		  Rectangle r4 = new Rectangle("r4",point3,5,2);
		  rjdbc.create(r4);
		  rjdbc.delete(r4);
		  assertNull(rjdbc.find("r4"));
	  }

}
