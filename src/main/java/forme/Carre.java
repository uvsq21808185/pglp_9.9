package forme;

/**
 * Classe Carre.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class Carre extends Rectangle {

	  /**
	   * le coté du carrée.
	   */
	  private double side;

	  /**
	   * constructeur de la classe.
	   * @param nomCarr le nom du carrée. 
	   * @param pointR point de reference.
	   * @param side le coté du carrée.
	   * @param groupId le id du groupe du caree.
	   */
	  public Carre(String nomCarr,Point pointR, double side,int groupId) {
	    super(nomCarr, pointR, side, side,groupId);
	    this.side=side;
	  }
	  /**
	   * constructeur2 de la classe.
	   * @param nomCarr le nom du carrée. 
	   * @param pointR point de reference.
	   * @param longueur le coté du carrée.
	   */
	  public Carre(String nomCarr, Point pointR, int longueur) {
		  super(nomCarr, pointR, longueur,longueur);
		    this.side=longueur;	}
	/**
	   * methode pour renvoie le coté du carré.
	   * @return le coté.
	   */
	  public double getSide() {
	    return side;
	  }
	  /*
	   * Méthode pour modifier le side du carré.
	   * @param side le coté du carré.
	   */
	  public void setSide(double side) {
		  this.side=side;
	  }

	  /**
	   * methode pour afficher les caractéristiques du Carre.
	   * @return les caractéristiques.
	   */
	  @Override
	  public String Affiche() {
		Point point = this.getLeftTop();
	    return this.getName()+" = Carre( point de reférence("+point.getX()+","
		    +point.getY()+"),cote="+this.getSide()+")";
	  }

	}