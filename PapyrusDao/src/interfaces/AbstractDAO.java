package interfaces;
import java.sql.Connection;
import java.util.List;

import exceptions.ElementPasTrouveException;
import model.EntCom;
import model.Ligcom;
import outils.ConnexionBDD;

/**
 * 
 */

/**
 * @author damoklesh
 *
 */
public abstract class AbstractDAO<T> {
	
	
	//Singleton de connexion à la base de données
	public Connection connection = ConnexionBDD.getConnection();
	
	//Méthodes CRUD
	
	//Create élement
	public abstract T Create(T element) throws ElementPasTrouveException;
	
	//Read élement
	
	// ici je passe l4element la dao se debrouillera pour identifier l4ID de recherche
	//public abstract T Read(T eltRecherche);
	
	// j4implemente une methode qui rechercherait par ID mais renvoit null quoiqu il arrive
	public T Read(int id){
		return null;
	}
	
	public T Read(String codart, int numfou){return null;}
	
	public T Read(String codart){
		return null;
	}
	
	public T Create(T element, int numcom, int numlig){ return null;}
	
	//j4implemente une methode qui rechercherait par ID+CODART mais renvoit null quoiqu il arrive
	// 0 CODER si ma PK 5cl2 primaire' dans ma table ORACLE est composee de 2 FK id et codeart
	public T Read(int id, String Codart){
		return null;
	}
	
	//Update pour les lignes de commande de Entcom
	public T UpdateLignes(T element, List<Ligcom> ligcoms) {
		return null;
	}
	
	/**
	 * @param element
	 * @param ligcoms
	 * @return
	 */
	public EntCom UpdateLignes(EntCom element, List<Ligcom> ligcoms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//READALL
	public abstract List<T> ReadAll();
	
	//Update elmnt
	public abstract T Update(T element);
	
	//Delete elmnt
	public abstract void Delete(T element);

	
}


