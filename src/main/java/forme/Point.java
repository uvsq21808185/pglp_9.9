package forme;

/**
 * Classe Point représentant la position des objets qu'on va manipuler.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class Point {
	  /**
	   * coordonnée x du point .
	   */
	  private double x;

	  /**
	   * coordonnée y du point.
	   */
	  private double y;
	  
	  /*
	   * Constructeur de la classe Point : en (0,0).
	   */
	  public Point() {
	    this.x = 0;
	    this.y = 0;
	  }
	  /*
	   * Constructeur de la classe Point : avec x et y en paramètre.
	   * @param xAtt la coordonnée X
	   * @param yAtt la coordonnée Y
	   */
	  public Point(double xAtt,double yAtt) {
	    this.x = xAtt;
	    this.y = yAtt;
	  }

	  /**
	   * methode pour accéder au coordonnée x du point.
	   * @return cordonné x du point. 
	   */
	  public double getX() {
	    return x;
	  }
	  /**
	   * methode pour accéder au coordonnée y du point.
	   * @return cordonné y du point. 
	   */
	  public double getY() {
	    return y;
	  }

	  /**
	   * methode pour modifier coordonnée x du point.
	   */
	  public void setX(double x1) {
		  this.x = x1;
	  }

	  /**
	   * methode pour modifier coordonnée y du point.
	   */
	  public void setY(double y1) {
		  this.y = y1;
	  }
	}