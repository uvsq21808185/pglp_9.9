package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;
import forme.Carre;
import forme.Cercle;
import forme.GroupShapes;
import forme.Rectangle;
import forme.Shape;
import forme.Triangle;


/**
 * dao pour opération JDBC sur les groupes de formes.
 * @author ZAOUAM Sirageddine
 * @version 2.0
 */
public class DaoGroupShapesJdbc implements Dao<GroupShapes> {
	
    private Connection connexion = null;
	private Statement statement;

    private String table =  "create table GroupeForme (variableName varchar(30) primary key)";
            //+ "foreign key (variableName) references Forme (variableName))";

    @Override
    /*
     * Créer la table GroupForme pour le Dao si elle n'existe pas.
     */
	public void CreateDaoTable() {
	
		connexion = Connexion.getConnection();
	    try {
	      ResultSet res = connexion.getMetaData().getTables(null,null,"GroupeForme".toUpperCase(),null);
	      statement = connexion.createStatement();
	      if (!res.next()) {
	        statement.execute(table);
	      }
	      statement.close();
	      connexion.close();
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }		
	}
    /**
     * créé une association d'un groupe qui contient une forme.
     * @param idGroupe groupe qui contient
     * @param idComposant forme qui compose le groupe
     */
    public void createComposition(
    		
            final String idGroupe, final String idComposant) {
        final int un = 1, deux = 2;
        connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
            "INSERT INTO Composition"
            + " (idGroupe, idComposant)"
            + " VALUES(?, ?)");
            prepare.setString(un, idGroupe);
            prepare.setString(deux, idComposant);
            prepare.executeUpdate();
        } catch (SQLException e) {
        }
    }
    /**
     * recherche toutes les associations d'un groupe qui contient des formes.
     * @param id identifiant du groupe
     * @return listes des composants du groupe
     */
    public ArrayList<Shape> findComposition(final String id) {
        final int un = 1;
        ArrayList<Shape> find = new ArrayList<Shape>();
		connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
                    "SELECT idComposant "
                    + "FROM Composition WHERE idGroupe = ?");
            prepare.setString(un, id);
            ResultSet result = prepare.executeQuery();
            Dao<Cercle> daoCe = new DaoCercleJdbc();
            Dao<Carre> daoCa = new DaoCarreJdbc();
            Dao<Rectangle> daoR = new DaoRectangleJdbc();
            Dao<Triangle> daoT = new DaoTriangleJdbc();
            while (result.next()) {
                Shape f = daoCe.find(result.getString("idComposant"));
                if (f == null) {
                    f = daoCa.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = daoR.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = daoT.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = this.find(result.getString("idComposant"));
                }
                find.add(f);
            }
        } catch (SQLException e) {
            return new ArrayList<Shape>();
        }
        return find;
    }
    /**
     * retire toutes les associations d'un groupe qui contient des formes.
     * @param id identifiant du groupe
     */
    public void deleteComposition(final String id) {
        final int un = 1;
		connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
                    "DELETE FROM Composition WHERE idGroupe = ?");
            prepare.setString(un, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
        }
    }
    /**
     * supprime toutes les associations
     * de la forme contenu dans les groupes.
     * @param id identifiant de la forme
     */
    private void deleteComposant(final String id) {
        final int un = 1;
		connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
                    "DELETE FROM Composition WHERE idComposant = ?");
            prepare.setString(un, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
        }
    }
    /**
     * ajoute un élément au DAO.
     * @param object l'élément à ajouter
     * @return la creation
     */
    @Override
    public GroupShapes create(final GroupShapes object) {
        final int un = 1;
		connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variableName)"
                    + " VALUES(?)");
            prepare.setString(un, object.getName());
            prepare.executeUpdate();
            prepare = connexion.prepareStatement(
                    "INSERT INTO GroupeForme"
                    + " (variableName)"
                    + " VALUES(?)");
            prepare.setString(un, object.getName());
            prepare.executeUpdate();
            for (Shape f : object.getList()) {
                if (f.getClass() == Cercle.class) {
                    Dao<Cercle> dao = new DaoCercleJdbc();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                    Dao<Carre> dao = new DaoCarreJdbc();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                    Dao<Rectangle> dao = new DaoRectangleJdbc();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                    Dao<Triangle> dao = new DaoTriangleJdbc();
                    dao.create((Triangle) f);
                } else {
                    this.create((GroupShapes) f);
                }
                this.createComposition(
                        object.getName(), f.getName());
            }
        } catch (SQLException e) {
            return null;
        }
        return object;
    }
    /**
     * obtenir un élément par son identifiant.
     * @param id identifiant de l'élément à obtenir
     * @return l'élément souhaité
     */
    @Override
    public GroupShapes find(final String id) {
        final int un = 1;
        GroupShapes find = null;
		connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
                    "SELECT * FROM GroupeForme WHERE variableName = ?");
            prepare.setString(un, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                find = new GroupShapes(id);
                ArrayList<Shape> list = findComposition(id);
                for (Shape f : list) {
                    find.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return find;
    }
    /**
     * obtenir tous les éléments.
     * @return tous les éléments
     */
    @Override
    public ArrayList<GroupShapes> findAll() {
        ArrayList<GroupShapes> find = new ArrayList<GroupShapes>();
		connexion = Connexion.getConnection();

        try {
            PreparedStatement prepare = connexion.prepareStatement(
                    "SELECT variableName FROM GroupeForme");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variableName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<GroupShapes>();
        }
        return find;
    }
    /**
     * modifie un élément du DAO.
     * @param object l'élément à modifier
     * @return la modification
     */
    @Override
    public GroupShapes update(final GroupShapes object) {
		connexion = Connexion.getConnection();

        ArrayList<Shape> contenu = this.findComposition(
                object.getName());
        if (!contenu.isEmpty()) {
            this.deleteComposition(object.getName());
            for (Shape f : object.getList()) {
                if (f.getClass() == Cercle.class) {
                    Dao<Cercle> dao = new DaoCercleJdbc();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                    Dao<Carre> dao = new DaoCarreJdbc();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                    Dao<Rectangle> dao = new DaoRectangleJdbc();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                    Dao<Triangle> dao = new DaoTriangleJdbc();
                    dao.create((Triangle) f);
                } else {
                    this.create((GroupShapes) f);
                }
                this.createComposition(
                        object.getName(), f.getName());
            }
        } else {
            return null;
        }
        return object;
    }
    /**
     * supprime un élément du DAO.
     * @param object élément à supprimer
     */
    @Override
    public void delete(final GroupShapes object) {
        final int un = 1;
		connexion = Connexion.getConnection();

        try {
            this.deleteComposition(object.getName());
            this.deleteComposant(object.getName());
            PreparedStatement prepare = connexion.prepareStatement(
                    "DELETE FROM GroupeForme WHERE variableName = ?");
            prepare.setString(un, object.getName());
            prepare.executeUpdate();
            prepare = connexion.prepareStatement(
                    "DELETE FROM Forme WHERE variableName = ?");
            prepare.setString(un, object.getName());
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
