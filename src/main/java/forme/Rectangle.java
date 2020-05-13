package forme;
/**
 * Classe décrivant la forme d'un rectangle. Elle implémente la classe abstraite Shape.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class Rectangle extends Shape {

	  /**
	   * point en haut à gauche du rectangle.
	   */
	  private Point leftTop;
	  /**
	   * la longueur du rectangle.
	   */
	  private double length;

	  /**
	   * la largeur du rectangle.
	   */
	  private double width;

	  /**
	   * constructeur.
	   * @param nomRec nom du rectangle.
	   * @param position point de reference.
	   * @param length la longueur du rectangle.
	   * @param width la largeur du rectangle.
	   * @param groupId le id du groupe du rectangle.
	   */
	  public Rectangle(String nomRec,Point position ,double length,double width,int groupId) {
	    super(nomRec,groupId);
	    this.leftTop = position.clone();
	    this.length = length;
	    this.width = width;
	  }

	  public Rectangle(String nomRec, Point pointR, int longueur, int largeur) {
		  super(nomRec);
		    this.leftTop = pointR.clone();
		    this.length = longueur;
		    this.width = largeur;	}

	/**
	   * methode pour renvoyer le point en haut à gauche du rectangle.
	   * @return point en haut à gauche du rectangle.
	   */
	  public Point getLeftTop() {
	    return leftTop;
	  }

	  /**
	   * methode pour modifier le point en haut à gauche du rectangle.
	   *  @param leftTop nouveuax point.
	   */
	  public void setLeftTop(Point leftTop) {
	    this.leftTop = leftTop;
	   }

	  /**
	   * methode pour acceder à la longueur du rectangle.
	   * @return longueur.
	   */
	  public double getLength() {
	    return length;
	  }
	  /*
	   * méthode pour modifier la longueur de triangle
	   */
	  public void setLength(double length) {
		  this.length=length;
	  }
	  /**
	   * methode pour acceder à la largeur.
	   * @return largeur.
	   */
	  public double getWidth() {
	    return width;
	  }
	  /*
	   * methode pour modifier la largeur du rectangle
	   */
	  public void setWidth(double width) {
		  this.width=width;
	  }
	  /**
	   * methode pour déplacer le rectagle par x et y.
	   * @param x coordonnée x du deplacement.
	   * @param y coordonnée y du deplacement.
	   */
	  @Override
	  public void move(double x, double y) {
		leftTop.setX(leftTop.getX()+x);
		leftTop.setY(leftTop.getY()+y);
	  }

	  /**
	   * methode pour afficher les caractéristiques du rectangle.
	   * @return les caractéristiques
	   */
	  @Override
	  public String Affiche() {
		Point point = this.getLeftTop();
	    return this.getName()+" = Rectangle(point de reférence("+point.getX()+","
		    +point.getY()+"),longueur="+this.getLength()+", largeur="+this.getWidth()+")";
	  }
	}
