/**
 * 
 */
package interfaces;

import java.sql.Connection;
import java.util.List;
import outils.ConnexionBDD;

/**
 * @author damoklesh
 *
 */
public interface IDAO<T> {
	
	//Singleton de connexion à la base de données
	public Connection connection = ConnexionBDD.getConnection();
	
	//Méthodes CRUD
	
	//Create élement
	public T Create(T element);
	
	//Read élement
	public T Read(int id);
	public T Read(int id, String Codart);
	public T Read(String codart, int numfou);
	public List<T> ReadAll();
	
	//Update elmnt
	public T Update(T element);
	
	//Delete elmnt
	public void Delete(T element);
}
