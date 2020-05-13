package forme;
/**
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class Triangle extends Shape{

	  /**
	   * la base du triangle.
	   */
	  private double base;

	  /**
	   * la hauteur du triangle;
	   */
	  private double hauteur;
	  /**
	   * le point sommet du triangle.
	   */
	   private Point top;

	  /**
	   * constructeur.
	   * @param nomT nom du triangle.
	   * @param pointT point de reference.
	   * @param base la longueur du triangle.
	   * @param hauteur l'hauteur du triangle.
	   * @param groupId le id du groupe du triangle.
	   */
	  public Triangle(String nomT,Point pointT ,double base,double hauteur,int groupId) {
	    super(nomT,groupId);
	    this.base = base;
	    this.hauteur= hauteur;
	    top=pointT.clone();
	  }

	  /**
	   * methode pour acceder la base.
	   * @return base.
	   */
	  public double getBase() {
	    return base;
	  }
	  /*
	   * methode pour modifier la base
	   */
	  public void setBase(double base) {
		  this.base=base;
	  }
	  /**
	   * methode pour acceder la hauteur.
	   * @return hauteur.
	   */
	  public double getHauteur() {
	    return hauteur;
	  }
	  /*
	   * methode pour modifier l'hauteur
	   */
	  public void setHauteur(double hauteur) {
		  this.hauteur=hauteur;
	  }
	  /**
	   * methode pour acceder au sommet.
	   * @return sommet.
	   */
	  public Point getTop() {
	    return top;
	  }

	  /**
	   * methode pour modifier le sommet.
	   * @param top neveau sommet.
	   */
	  public void setTop(Point top) {
	    this.top = top;
	  }

	  /**
	   * methode pour déplacer le triagle par x et y.
	   * @param x coordonnée x du deplacement.
	   * @param y coordonnée y du deplacement.
	   */
	  @Override
	  public void move(double x, double y) {
	    top.setX(top.getX()+x);
		top.setY(top.getY()+y);
	  }

	  /**
	   * methode pour afficher les caractéristiques du triangle.
	   * @return les caractéristiques.
	   */
	  @Override
	  public String Affiche() {
	    Point point = this.getTop();
	      return this.getName()+" = Triangle(Sommet("+point.getX()+","
	        +point.getY()+"),base="+this.getBase()+", hauteur="+this.getHauteur()+")";
		  }
	}
