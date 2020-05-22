package forme;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author ZAOUAM Sirageddine
 *
 */
public class RectangleTest {


	  Point point = new Point(0,0);
	  Rectangle  r1= new Rectangle("r1",point,5,4);

	  @Test
	  public void movetest() {
	    r1.move(2, 3);
	    Double x = r1.getLeftTop().getX();
	    Double y = r1.getLeftTop().getY();
	    assertTrue(x.equals(2.0));
	    assertTrue(y.equals(3.0));
	   }

	  @Test
	  public void getNametest( ) {
	    assertTrue(r1.getName().equals("r1"));
	    r1.setName("r2");
	    assertTrue(r1.getName().equals("r2"));
	   }
	  @Test
	  public void getPointRefTest( ) {
	    Point p = new Point(5,8);
	    Rectangle  r3= new Rectangle("r3",p,8,4);
	    Double x = r3.getLeftTop().getX();
	    Double y = r3.getLeftTop().getY();
	    assertTrue(x.equals(5.0));
	    assertTrue(y.equals(8.0));
	    p = new Point(3.5,7);
	    r3.setLeftTop(p);
	    x = r3.getLeftTop().getX();
	    y = r3.getLeftTop().getY();
	    assertTrue(x.equals(3.5));
	    assertTrue(y.equals(7.0));
	   }
	  @Test
	  public void getLengthTest( ) {
		Double length = r1.getLength();
	    assertTrue(length.equals(5.0));
	   }
	  @Test
	  public void getWeidthTest( ) {
	    Double weidth = r1.getWidth();
	    assertTrue(weidth.equals(4.0));
	   }
	}