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
public class TriangleTest {

	Point point1 = new Point(0,0);
	Point point2 =  new Point(2,5);
	Point point3 =  new Point(2,0);
	Point point4 = new Point(5,8);

	 Triangle  t1= new Triangle("t1",point1,point2,point3);
	 Triangle  t4 = new Triangle("t2",point1,point2,point4);

	  @Test
	  public void movetest() {
	    t1.move(2, 3);
	    Double x = t1.getPoint(0).getX();
	    Double y = t1.getPoint(0).getY();
	    assertTrue(x.equals(2.0));
	    assertTrue(y.equals(3.0));
	   }

	  @Test
	  public void getNametest( ) {
	    assertTrue(t1.getName().equals("t1"));
	    t1.setName("t2");
	    assertTrue(t1.getName().equals("t2"));
	   }

	  @Test
	  public void getPointRefTest( ) {
	    Point p = new Point(5,8);
	    Triangle  t3= new Triangle("t3",p,8.3,5,3);
	    Double x = t3.getTop().getX();
	    Double y = t3.getTop().getY();
	    assertTrue(x.equals(5.0));
	    assertTrue(y.equals(8.0));
	    p = new Point(3.5,7);
	    t3.setTop(p);
	    x = t3.getTop().getX();
	    y = t3.getTop().getY();
	    assertTrue(x.equals(3.5));
	    assertTrue(y.equals(7.0));
	   }

	  @Test
	  public void getBaseTest( ) {
		Point p = new Point(5,8);
	 	Triangle  t3= new Triangle("t3",p,8.3,5,3);
		Double base = t3.getBase();
	    assertTrue(base.equals(8.3));
	   }

	  @Test
	  public void getHauteurTest( ) {
		Point p = new Point(5,8);
		Triangle  t3= new Triangle("t3",p,8.3,5,3);
		Double height = t3.getHauteur();
	    assertTrue(height.equals(5.0));
	   }

	  @Test
	  public void getTopTest( ) {
		Point p = new Point(5,8);
		Triangle  t3= new Triangle("t3",p,8.3,5,3);
		Point top = t3.getTop();
		Double x = top.getX();
	    Double y = top.getY();
	    assertTrue(x.equals(5.0));
	    assertTrue(y.equals(8.0));
	   }


}
