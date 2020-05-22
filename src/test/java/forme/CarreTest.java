/**
 * 
 */
package forme;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class CarreTest {

	Point point = new Point(0,0);
	  Carre  ca1= new Carre("ca1",point,4);

	  @Test
	  public void movetest() {
	    ca1.move(4, 1);
	    Double x = ca1.getLeftTop().getX();
	    Double y = ca1.getLeftTop().getY();
	    assertTrue(x.equals(4.0));
	    assertTrue(y.equals(1.0));
	   }

	  @Test
	  public void getNametest( ) {
	    assertTrue(ca1.getName().equals("ca1"));
	    ca1.setName("ca2");
	    assertTrue(ca1.getName().equals("ca2"));
	   }
	  @Test
	  public void getPointRefTest( ) {
	    Point p = new Point(2,6);
	    Carre  ca3= new Carre("ca3",p,8);
	    Double x = ca3.getLeftTop().getX();
	    Double y = ca3.getLeftTop().getY();
	    assertTrue(x.equals(2.0));
	    assertTrue(y.equals(6.0));
	    p = new Point(4.5,8);
	    ca3.setLeftTop(p);
	    x = ca3.getLeftTop().getX();
	    y = ca3.getLeftTop().getY();
	    assertTrue(x.equals(4.5));
	    assertTrue(y.equals(8.0));
	   }
	  @Test
	  public void getSideTest( ) {
		Double side = ca1.getSide();
	    assertTrue(side.equals(4.0));
	   }

}
