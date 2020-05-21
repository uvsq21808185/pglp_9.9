package forme;

import java.io.CharConversionException;

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
	     * constructeur de position avec un type String.
	     * syntaxe : (x,y)
	     * @param position string contenant la position.
	     * @throws CharConversionException invalid String
	     */
	    public Point(final String position) throws CharConversionException {
	        position.replace(" ", "");
	        if (position.charAt(0) != '('
	        || position.charAt(position.length() - 1) != ')') {
	            System.err.println(position);
	            throw new CharConversionException();
	        }
	        String position2 = position.substring(1, position.length() - 1);
	        String[] positionSplit = position2.split(",");
	        if (positionSplit.length != 2) {
	            System.err.println(position);
	            throw new CharConversionException();
	        }
	        try {
	            x = Integer.parseInt(positionSplit[0]);
	            y = Integer.parseInt(positionSplit[1]);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            throw e;
	        }
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
	  /**
	     * déplacer ce point selon les paramètres.
	     * @param x2 décalage en abscisse par rapport à l'origine
	     * @param y2 décalage en ordonnée par rapport à l'origine
	     */
	    public void deplace(final double x2, final double y2) {
	        x += x2;
	        y += y2;
	    }
	    /**
	     * conversion en String d'une position.
	     * @return position converti en chaine de caractère
	     */
	    public String toString() {
	        return "(" + x + "," + y + ")";
	    }
	    /**
	     * retourne une copie de la position.
	     * @return copie de l'objet
	     */
	    @Override
	    public Point clone() {
	        return new Point(x, y);
	    }
	}