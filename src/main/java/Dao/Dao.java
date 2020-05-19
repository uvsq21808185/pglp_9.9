package Dao;

import java.util.ArrayList;

/**
* interface DAO.
* @author ZAOUAM Sirageddine.
* @version 1.0
* @param <T>  type d'objet.
*/
public interface Dao<T> {

  /**
   * methode pour creer un objet.
   * @param obj T.
   * @return obj crée.
   */
  public T create(T obj);

  /**
   * methode pour chercher un objet d'identifiant id.
   * @param id nom de l'objet.
   * @return objet recherché.
   */
  public T find(String id);

  /**
   * methode pour mettre à jour un objet T.
   * @param obj l'objet qu'on veut mettre à jour.
   * @return l'obj après la mise à jour.
   */
  public T update(T obj);

  /**
   * methode pour supprimer le fichier de l'objet T obj.
   * @param obj T à supprimer.
   */
  public void delete(T obj);

  /**
     * obtenir tous les éléments.
     * @return tous les éléments
     */
  public ArrayList<T> findAll();
  
  	public void CreateDaoTable();
}

