/**
 * 
 */
package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factories.PojoFactory;
import model.Fournis;
import interfaces.AbstractDAO;

/**
 * @author damoklesh
 *
 */
public class FournisDao extends AbstractDAO<Fournis>{
	

	/**
	 * @param element Fournisseur
	 * @return Fournisseur
	 */
	public Fournis Create(Fournis element){
		
		//essayer de insérer l'élement
		try {
			//création et préaration du statement
			PreparedStatement preparedState = this.connection.prepareStatement("INSERT INTO FOURNIS VALUES (?,?,?,?,?,?,?)");
			
			//rajouter paramètres
			preparedState.setInt(1,element.getNumfou());
			preparedState.setString(2,element.getNomfou());
			preparedState.setString(3, element.getRuefou());
			preparedState.setInt(4, element.getPosfour());
			preparedState.setString(5, element.getVilfou());
			preparedState.setString(6, element.getConfou());
			preparedState.setInt(7, element.getSatisf());
			
			//éxecute requete
			preparedState.executeUpdate();
			System.out.println("Fournisseur  enregistré");
			
			
			//catch erreur s'elle se produit
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		
		return Read(element.getNumfou());
	}
	
	/**
	 * @param numcom int
	 * @param codart string
	 * @return Ligcom object
	 */
	
	public Fournis Read(int numfou){
		Fournis elCherche = null;
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("SELECT * FROM FOURNIS WHERE NUMFOU = ?");
			
			//inserer paramètres
			prepare.setInt(1,numfou);
			
			//exec
			ResultSet result = prepare.executeQuery();
			
			//créer un nouvelle objet
			if (result.next()){
				elCherche = PojoFactory.CreateFournis(result.getInt(1),result.getString(2),
									   result.getString(3),result.getInt(4),
								       result.getString(5),result.getString(6),result.getInt(7));
				//System.out.println("objet trouvé " + result.getInt(1) );
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	};
	
	
	
	public List<Fournis> ReadAll(){
		List<Fournis> fournisseurs = new ArrayList<Fournis>();
		
		try {
			//executer requete
			ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM FOURNIS");
		
			
			//remplir la liste
			if (result.next()){
				
				/*
				Ligcom elCherche = PojoFactory.CreateLigcom(
												result.getInt(1),
												result.getString(2),
												result.getInt(3),
												result.getInt(4),
												result.getInt(5),
												result.getInt(6),
												result.getDate(7));
												*/
				// ici utilisation de la factory pour recuperer un objet 5licom' et l4inserer dans la liste dont a popos duquel nous nous entretenions
				fournisseurs.add(PojoFactory.CreateFournis(
												result.getInt(1),
												result.getString(2),
												result.getString(3),
												result.getInt(4),
												result.getString(5),
												result.getString(6),
												result.getInt(7)));
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return fournisseurs;
	};
	
	//Update elmnt
	public Fournis Update(Fournis element){
		Fournis elCherche = null;
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("UPDATE FOURNIS SET NOMFOU = ?, RUEFOU = ?, POSFOU = ?, VILFOU = ?, CONFOU = ?, SATISF = ?  WHERE NUMFOU = ?");
			
			//inserer paramètres
			prepare.setString(1,element.getNomfou());
			prepare.setString(2, element.getRuefou());
			prepare.setInt(3, element.getPosfour());
			prepare.setString(4, element.getVilfou());
			prepare.setString(5, element.getConfou());
			prepare.setInt(6, element.getSatisf());
			prepare.setInt(7, element.getNumfou());
			
			//exec
			prepare.executeUpdate();
			
			System.out.println("Fournisseur modifié");
			//chercher le nouvelle objet
			elCherche = Read(element.getNumfou());
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	};
	
	//Delete elmnt
	public void Delete(Fournis element){
		
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("DELETE FROM FOURNIS WHERE NUMFOU = ?");
			
			//inserer paramètres
			prepare.setInt(1, element.getNumfou());
			
			//exec
			prepare.executeUpdate();
			System.out.println("Fournisseur  supprimé ");
			
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
	};


}
