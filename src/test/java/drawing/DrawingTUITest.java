/**
 * 
 */
package drawing;

import static org.junit.Assert.*;

import org.junit.Test;

import forme.Carre;
import forme.Cercle;
import forme.Rectangle;
import forme.Triangle;

/**
 * @author Win10
 *
 */
public class DrawingTUITest {

	DrawingTUI dt = new DrawingTUI();
	  @Test
	  public void drawingTuiTest() {
	    String userCommande = "c1=Cercle((0,0),50)";
	    Command commande = dt.nextCommand(userCommande);
	    assertTrue( commande instanceof CommandCreate);
	    assertNotNull(dt.create(userCommande));
	    assertEquals(dt.create(userCommande).getName(),"c1");
	    
	    Cercle c = (Cercle) dt.create(userCommande);
	    Double x = c.getCentre().getX();
	    Double y = c.getCentre().getY();
	    Double rayon = c.getRayon();
	    assertEquals(c.getName(),"c1");
	    assertTrue(x.equals(0.0));
	    assertTrue(y.equals(0.0));
	    assertTrue(rayon.equals(50.0));
	  }
	  @Test
	  public void CreateCarreTest() {

	    String userCommande = "ca1=Carre((10,5),10)";
	    Command commande = dt.nextCommand(userCommande);
	    assertTrue( commande instanceof CommandCreate);
	    Carre ca = (Carre) dt.create(userCommande);
	    Double x = ca.getLeftTop().getX();
	    Double y = ca.getLeftTop().getY();
	    Double side = ca.getSide();
	    assertEquals(ca.getName(),"ca1");
	    assertTrue(x.equals(10.0));
	    assertTrue(y.equals(5.0));
	    assertTrue(side.equals(10.0));
	  }
	  @Test
	  public void CreateRectangleTest() {

	    String userCommande = "r1=Rectangle((8,5),7,3)";
	    Command commande = dt.nextCommand(userCommande);
	    assertTrue( commande instanceof CommandCreate);
	    Rectangle r = (Rectangle) dt.create(userCommande);
	    Double x = r.getLeftTop().getX();
	    Double y = r.getLeftTop().getY();
	    Double length = r.getLength();
	    Double width = r.getWidth();
	    assertEquals(r.getName(),"r1");
	    assertTrue(x.equals(8.0));
	    assertTrue(y.equals(5.0));
	    assertTrue(length.equals(7.0));
	    assertTrue(width.equals(3.0));
	  }
	  @Test
	  public void CreateTriangleTest() {

	    String userCommande = "t1=Triangle((6,2),(2,0),(5,9))";
	    Command commande = dt.nextCommand(userCommande);
	    assertTrue( commande instanceof CommandCreate);
	    Triangle t = (Triangle) dt.create(userCommande);
	    Double x = t.getPoint(0).getX();
	    Double y = t.getPoint(0).getY();
	    Double x1 = t.getPoint(1).getX();
	    Double y1 = t.getPoint(1).getY();
	    Double x2 = t.getPoint(2).getX();
	    Double y2 = t.getPoint(2).getY();
	    assertEquals(t.getName(),"t1");
	    assertTrue(x.equals(6.0));
	    assertTrue(y.equals(2.0));
	    assertTrue(x1.equals(2.0));
	    assertTrue(y1.equals(0.0));
	    assertTrue(x2.equals(5.0));
	    assertTrue(y2.equals(9.0));
	   
	  }

}
