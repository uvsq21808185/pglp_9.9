package forme;
import java.util.ArrayList;

/**
 * Pattern composite pour les formes.
 */
public class GroupShapes extends Shape {
    /**
     * liste des formes.
     */
    private ArrayList<Shape> formes;
    /**
     * constructeur du groupe.
     * @param variableName nom de la variable pour le groupe
     */
    public GroupShapes(final String variableName) {
        super(variableName);
        formes = new ArrayList<Shape>();
    }
    /**
     * deplace les formes.
     * @param x déplacement en abscisse
     * @param y deplacement en ordonée
     */
    public void move(final double x, final double y) {
        for (Shape f : formes) {
            f.move(x, y);
        }
    }
    /**
     * affiche les formes et groupes de formes dans ce groupe.
     */
    public String Affiche() {
       // super.Affiche();
        System.out.println("Groupe (");
        for (Shape f : formes) {
            f.Affiche();
        }
        System.out.println(")");
        return "r";
    }
    /**
     * ajoute une forme ou un groupe au groupe.
     * @param f forme ou groupe à ajouter au groupe
     */
    public void add(final Shape f) {
        if (!formes.contains(f) && f != this) {
            formes.add(f);
        }
    }
    /**
     * supprime une forme ou un groupe du groupe.
     * @param f forme ou groupe à supprimer du groupe
     */
    public void remove(final Shape f) {
        formes.remove(f);
    }
    /**
     * supprimer l'ensemble des formes et groupes du groupe.
     */
    public void reset() {
        while (!formes.isEmpty()) {
            formes.remove(0);
        }
    }
    /**
     * obtenir la liste des formes et groupes du groupe.
     * @return la liste des formes et groupe du groupe
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Shape> getList() {
        return (ArrayList<Shape>) formes.clone();
    }
	

}
