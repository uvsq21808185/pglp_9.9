/**
 * 
 */
package forme;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Win10
 *
 */
public class PointTest {

	 @Test
	    public void testDeplace() {
	        Point p = new Point(15,12);
	        p.deplace(100, 1000);
	        assertTrue(p.getX() == 115 && p.getY() == 1012);
	    }
	    @Test
	    public void testToString() {
	        Point p = new Point(15,12);
	        p.deplace(100, 1000);
	        String s="(115,1012)";
	        String test = p.toString();
	        assertFalse(test.equals(s));
	    }
	    @Test
	    public void testClone() {
	        Point p = new Point(1456,1236);
	        Point p2 = p.clone();
	        assertTrue(p2.getX() == p.getX() && p2.getY() == p.getY() && p != p2);
	    }
}
