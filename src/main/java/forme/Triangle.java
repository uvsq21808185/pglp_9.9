package forme;
/**
 * Classe qui décrit la forme d'un triangle.
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
	     * ensemble des 3 points d'un triangle.
	     */
	   private Point[] points;
	    /**
	     * constructeur 1 d'un triangle.
	     * @param nomVariable nom de variable pour créer le triangle
	     * @param point1 premier point du triangle
	     * @param point2 deuxième point du triangle
	     * @param point3 troisième point du triangle
	     */
	    public Triangle(final String nomVariable, final Point point1,
	            final Point point2, final Point point3) {
	        super(nomVariable);
	        final int trois = 3;
	        points = new Point[trois];
	        points[0] = point1.clone();
	        points[1] = point2.clone();
	        points[2] = point3.clone();
	    }
	  /**
	   * constructeur 2.
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
	     * obtenir un point du triangle.
	     * @param index valeur entre 0 et 2 indiquant le point souhaité
	     * @return le point souhaité
	     */
	    public Point getPoint(final int index) {
	        if (index < 0 || index > 2) {
	            throw new IndexOutOfBoundsException();
	        } else {
	            return points[index].clone();
	        }
	    }

	  /**
		  * methode pour déplacer le triangle au point (x,y).
		  * @param x coordonnée x du deplacement.
		  * @param y coordonnée y du deplacement.
		  */
		  @Override
		    public void move(final double x, final double y) {
			  final int trois = 3;
		        for (int i = 0; i < trois; i++) {
		            points[i].deplace(x, y);
		        }
		  }

	  /**
	   * methode pour afficher les caractéristiques du triangle.
	   * @return les caractéristiques.
	   */
	  @Override
	  public String Affiche() {
	      return this.getName()+" = Triangle(Point 1 ("+points[0].getX()+","
	        +points[0].getY()+")"+", Point 2 ("+points[1].getX()+","+points[1].getY()+")"+""
	        		+", Point 3 ("+points[2].getX()+","+points[2].getY()+") )";
		  }
	}
