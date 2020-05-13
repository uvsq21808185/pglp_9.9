/**
 * 
 */
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
import forme.InterfaceShape;
import forme.Rectangle;
import forme.Triangle;

/**
 * Classe DaoGroupShapes qui implémente les opérations JDBC sur un groupe de formes.
 * @author ZAOUAM Sirageddine
 * @version 1.0
 */
public class DaoGroupShapesJdbc implements Dao<GroupShapes> {

	  /**
	   * un attribut pour établire la connexion.
	   */
	  private Connection connexion = null;

	  /**
	   * la requte da creation de la table GroupShapes.
	   */
	  private String table = "create table groupshapes(groupeId INTEGER primary key not null"
		      + ", nomGroupe varchar(20) not null )";

	  /**
	   *  attribut statemet.
	   */
	  private Statement statement;

	  /**
	   * Constructeur.
	   */
	  public DaoGroupShapesJdbc() {
			connexion = Connexion.getConnection();
	    try {
	      ResultSet res = connexion.getMetaData()
	        .getTables(null,null, "groupshapes".toUpperCase(), null);
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
	   * methode pour insérer un tuple dans la table groupShapes.
	   * @param obj groupShapes.
	   * @return obj crée sinon null.
	   */
	  public GroupShapes create(GroupShapes obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement create =  null;
	    int status = 0;
	    String insertString = "insert into groupshapes(groupeId, nomGroupe) values (?,?)";
	    try {
	      create = connexion.prepareStatement(insertString);
	      create.setInt(1, obj.getIdG());
	      create.setString(2, obj.getName());
	      status = create.executeUpdate();
	      DaoRectangleJdbc rj = new DaoRectangleJdbc();
	      DaoCarreJdbc caj = new DaoCarreJdbc();
	      DaoCercleJdbc cj = new DaoCercleJdbc();
	      DaoTriangleJdbc tj = new DaoTriangleJdbc();
		  for (InterfaceShape p: obj.getShapes()) {
	        if (!(p instanceof GroupShapes)) {
	          if (p instanceof Rectangle) {
	            rj.create((Rectangle) p);
	          }
	          if (p instanceof Carre) {
	            caj.create((Carre) p);
	          }
	          if (p instanceof Cercle) {
	            cj.create((Cercle) p);
	          }
	          if (p instanceof Triangle) {
	            tj.create((Triangle) p);
	          }
	        }
	      }
	    connexion.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    try {
	      if (create != null) {
	        create.close();
	      }
	    } catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    if (status > 0) {
	      return obj;
	    } else {
	      return null;
	    }
	  }

	  /**
	   * methode pour trouver le tuple GroupShapes.
	   * @param id num de groupe du groupShapes.
	   * @return objet GroupShapes trouvé ,null sinon.
	   */
	  public GroupShapes find(String id) {
			connexion = Connexion.getConnection();
	    int groupeid = Integer.parseInt(id);
		    String findString = "select * from groupshapes where groupeId = (?)";
		    GroupShapes cs = null;
		    PreparedStatement find = null;
		    PreparedStatement findG = null;
		    try {
	          Rectangle r = null;
	          DaoRectangleJdbc rj = new DaoRectangleJdbc();
	          Carre ca = null;
	          DaoCarreJdbc caj= new DaoCarreJdbc();
	          Cercle c = null;
	          DaoCercleJdbc cj = new DaoCercleJdbc();
	          Triangle tr = null;
	          DaoTriangleJdbc trj = new DaoTriangleJdbc();
		      find = connexion.prepareStatement(findString);
		      find.setInt(1, groupeid);
		      find.execute();
		      ResultSet resultat = find.getResultSet();
		      if (resultat.next()) {
		        cs = new GroupShapes(resultat.getString("nomGroupe"),resultat.getInt("groupeId"));
		        String findGroupe = "select name from rectangles,Groupshapes where groupId = (?)";
		        findG = connexion.prepareStatement(findGroupe);
		        findG.setInt(1, groupeid);
		        findG.execute();
		        ResultSet resultat1 = findG.getResultSet();
		        if (resultat1.next()) {
		          r = rj.find(resultat1.getString("name"));
		          cs.add(r);
		        }
		        findGroupe = "select name from carres,Groupshapes where groupId = (?)";
		        findG = connexion.prepareStatement(findGroupe);
		        findG.setInt(1, groupeid);
		        findG.execute();
		        resultat1 = findG.getResultSet();
		        if (resultat1.next()) {
		          ca = caj.find(resultat1.getString("name"));
		          cs.add(ca);
		        }
		        findGroupe = "select name from cercles,Groupshapes where groupId = (?)";
		        findG = connexion.prepareStatement(findGroupe);
		        findG.setInt(1, groupeid);
		        findG.execute();
		        resultat1 = findG.getResultSet();
		        if (resultat1.next()) {
		          c = cj.find(resultat1.getString("name"));
		          cs.add(c);
		        }
		        findGroupe = "select name from Triangles,Groupshapes where groupId = (?)";
		        findG = connexion.prepareStatement(findGroupe);
		        findG.setInt(1, groupeid);
		        findG.execute();
		        resultat1 = findG.getResultSet();
		        if (resultat1.next()) {
		          tr  = trj.find(resultat1.getString("name"));
		          cs.add(tr);
		        }
		        connexion.close();
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    try {
		      if (findG != null) {
		        findG.close();
		      }
		    } catch (SQLException e1) {
		      e1.printStackTrace();
		    }
		    try {
		      if (find != null) {
		        find.close();
		      }
		    } catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return cs;
		  }

	  /**
	   * methode pour mettre à jour un tuple GroupShapes.
	   * @param obj l'objet pour faire mise à jour.
	   * @return l'objet après la mise à jour.
	   */
	  public GroupShapes update(GroupShapes obj) {
			connexion = Connexion.getConnection();
	    String updateString = "update groupshapes set nomGroupe = (?) where groupeId = (?)";
	    final GroupShapes cs = null;
	    PreparedStatement update = null;
	    try {
	      update = connexion.prepareStatement(updateString);
	      update.setString(1, obj.getNameG());
	      update.setInt(2, obj.getIdG());
	      update.execute();
	      connexion.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	   try {
	     if (update != null) {
	       update.close();
	     }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return cs;
	  }

	  /**
	   * methode pour supprimer le tuple de l'objet GrpoupShapes de la bd.
	   * @param obj GroupShapes à supprimer.
	   */
	  public void delete(GroupShapes obj) {
			connexion = Connexion.getConnection();
	    PreparedStatement delete = null;
		PreparedStatement delete1 = null;
	    try {
	      DaoRectangleJdbc rj = new DaoRectangleJdbc();
	      DaoCarreJdbc caj= new DaoCarreJdbc();
	      DaoCercleJdbc cj = new DaoCercleJdbc();
	      DaoTriangleJdbc trj = new DaoTriangleJdbc();
	      for (InterfaceShape p: obj.getShapes()) {
	        if (p instanceof Rectangle) {
	          rj.delete((Rectangle) p);
	        }
	        if (p instanceof Carre) {
	          caj.create((Carre) p);
	        }
	        if (p instanceof Cercle) {
	          cj.create((Cercle) p);
	        }
	        if (p instanceof Triangle) { 
	          trj.create((Triangle) p);
	        }
		  }
	      final String deleteString = "Delete from groupshapes where groupeId = (?)";
	      int groupeid = obj.getGroupId();
	      delete = connexion.prepareStatement(deleteString);
	      delete.setInt(1, groupeid);
	      delete.execute();
	      String deleteString1 = "Update rectangles set groupId = null where groupId = (?)";
	      delete1 = connexion.prepareStatement(deleteString1);
	      delete1.setInt(1, groupeid);
	      delete1.execute();
	      deleteString1 = "Update carres set groupId = null where groupId = (?)";
	      delete1 = connexion.prepareStatement(deleteString1);
	      delete1.setInt(1, groupeid);
	      delete1.execute();
	      deleteString1 = "Update cercles set groupId = null where groupId = (?)";
	      delete1 = connexion.prepareStatement(deleteString1);
	      delete1.setInt(1, groupeid);
	      delete1.execute();
	      deleteString1 = "Update triangles set groupId = null where groupId = (?)";
	      delete1 = connexion.prepareStatement(deleteString1);
	      delete1.setInt(1, groupeid);
	      delete1.execute();
	      connexion.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    try {
	      if (delete != null) {
	        delete.close();
	      }
	    } catch (SQLException e1) {
	      e1.printStackTrace();
	    }
	    try {
	      if (delete1 != null) {
	        delete1.close();
	      }
	    } catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }	
	  /**
	     * obtenir tous les éléments.
	     * @return tous les éléments
	     */
	    public ArrayList<GroupShapes> findAll() {
			connexion = Connexion.getConnection();

	        ArrayList<GroupShapes> find = new ArrayList<GroupShapes>();
	        try {
	            PreparedStatement prepare = connexion.prepareStatement(
	                    "SELECT nomGroupe FROM groupshapes");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("nomGroupe")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<GroupShapes>();
	        }
	        return find;
	    }
	}
