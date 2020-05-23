package forme;

/**
 * InterfaceShape
 * 
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public interface InterfaceShape {

	  /**
	   * methode pour déplacer la forme.
	   * @param x coordonnée x du deplacement.
	   * @param y coordonnée y du deplacement.
	   */
	  public void move(double x,double y);

	  /**
	   * methode pour afficher les caractéristiques de la forme.
	   * @return String le string de la forme.
	   */
	  public String Affiche();

	}
