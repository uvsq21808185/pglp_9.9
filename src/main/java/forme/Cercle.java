
package forme;

/**
 * Classe décrivant la forme Cercle. Elle implémente la classe abstraite Shape.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class Cercle extends Shape{

	  /**
	   * le rayon du cecle.
	   */
	  private double rayon;

	  /**
	   * centre du cercle.
	   */
	  private Point centre;

	  /**
	   * constructeur.
	   * @param nomCer nom du cercle.
	   * @param pointC centre du cercle.
	   * @param rayon rayon du cerle.
	   */
	  public Cercle(String nomCer,Point pointC ,double rayon) {
	    super(nomCer);
	    this.rayon = rayon;
	    centre = pointC.clone();
	  }

	  /**
	   * constructeur 2.
	   * @param nomCer nom du cercle.
	   * @param pointC centre du cercle.
	   * @param rayon rayon du cerle.
	   * @param groupId le id du groupe du cercle.
	   */
	  
	  public Cercle(String nomCer,Point pointC ,double rayon,int groupId) {
	    super(nomCer,groupId);
	    this.rayon = rayon;
	    centre = pointC.clone();
	  }

	  /**
	   * methode pour acceder au rayon du cercle.
	   * @return rayon.
	   */
	  public double getRayon() {
	    return rayon;
	  }

	  /**
	   * methode pour modifier redius.
	   * @param redius nouveau redius.
	   */
	  public void setRayon(double rayon) {
	    this.rayon = rayon;
	  }

	  /**
	   * methode pour acceder au centre du cercle.
	   * @returnle centre 
	   */
	  public Point getCentre() {
	    return centre;
	  }

	  /**
	   * methode pour modifier le centre.
	   * @param redius nouveau centre.
	   */
	  public void setCentre(Point centre) {
	    this.centre = centre;
	  }

	  /**
	  * methode pour déplacer le cercle au point (x,y).
	  * @param x coordonnée x du deplacement.
	  * @param y coordonnée y du deplacement.
	  */
	  @Override
	    public void move(final double x, final double y) {
	        centre.deplace(x, y);
	    }
	  
	 /* public void move(double x, double y) {
	    centre.setX(centre.getX()+x);
		centre.setY(centre.getY()+y);
	  }
	 */
	  /**
	   * methode pour afficher les caractéristiques du cercle.
	   * @return les caractéristiques
	   */
	  @Override
	  public String Affiche() {
	  Point point = this.getCentre();
	  return this.getName()+" = Cercle((centre("+point.getX()+","
	      +point.getY()+"),rayon="+this.getRayon()+")";
	  }
	}
