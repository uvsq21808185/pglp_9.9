/**
 * 
 */
package forme;

import java.util.ArrayList;
import java.util.List;
/**
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class GroupShapes extends Shape implements InterfaceShape {
	  /**
	   * nom du groupe.
	   */
	  private String nameG;

	  /**
	   * id du groupe.
	   */
	  private int idG;

	  /**
	   * liste des formes.
	   */
	  private List<InterfaceShape> shapes;

	  /**
	   * Constructeur.
	   * @param nameGatt nom du groupe.
	   * @param pointGatt point du reference du groupe.
	   * @param idGatt id du groupe.
	   */
	  public GroupShapes(String nameGatt, int idGatt) {
		super(nameGatt,idGatt);
	    nameG = nameGatt;
	    idG = idGatt;
	    shapes = new ArrayList<InterfaceShape>();
	  }

	public GroupShapes(String Name) {
		// TODO Auto-generated constructor stub
		super(Name);
}

	/**
	   * methode pour acceder au nom du groupe.
	   * @return nom du groupe.
	   */
	  public String getNameG() {
	    return nameG;
	  }

	  /**
	   * methode pour modifier le nom du groupe.
	   * @param nameG le nouveau nom du groupe.
	   */
	  public void setNameG(String nameG) {
	    this.nameG = nameG;
	  }

	  /**
	   * methode pour acceder au id du groupe.
	   * @return id du groupe.
	   */
	  public int getIdG() {
	    return idG;
	  }

	  /**
	   * methode pour modifier le id du groupe. 
	   * @param idG le nouveau id.
	   */
	  public void setIdG(int idG) {
	    this.idG = idG;
	  }
	  /**
	     * supprimer l'ensemble des formes et groupes du groupe.
	     */
	    public void reset() {
	        while (!shapes.isEmpty()) {
	            shapes.remove(0);
	        }
	    }
	  /**
	   * methode pour acceder au groupe des formes.
	   * @return le groupe des formes.
	   */
	  public List<InterfaceShape> getShapes() {
	    return shapes;
	  }

	  /**
	   * methode pour ajouter une forme au groupe.
	   * @param shape la forme à ajouter .
	   */
	  public void add(InterfaceShape shape) {
		this.getShapes().add(shape);
	  }

	  /**
	   * methode pour supprimer une formet du groupe.
	   * @param shape forme à supprimer.
	   */
	  public void remove(InterfaceShape shape) {
	    if (this.getShapes().contains(shape)) {
	      shapes.remove(shape);
	    } else {
	      System.out.println("element non trouvé");
	    }
	  }
	  /**
	   * methode pour déplacer le groupe des formes.
	   * @param x coordonnée x du deplacement.
	   * @param y coordonnée y du deplacement.
	   */
	  @Override
	  public void move(double x, double y) {
	    for (InterfaceShape shape : shapes) {
	      shape.move(x, y);
	    }
	  }

	  /**
	   * methode pour afficher les caractéristiques de la forme.
	   * @return les caractéristiques du groupe.
	   */
	  @Override
	  public String Affiche() {
	    StringBuffer affiche = new StringBuffer();
	    for (InterfaceShape shape : shapes) {
	      affiche.append(" ");
	      affiche.append(shape.Affiche());
		}
	    return this.getNameG()+"Groupe :" +" " + affiche;
	  }
	}
