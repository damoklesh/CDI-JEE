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
import interfaces.AbstractDAO;
import model.Fournis;
import model.Produit;

/**
 * @author damoklesh
 *
 */
public class ProduitDao extends AbstractDAO<Produit> {
	

	/**
	 * @param element Fournisseur
	 * @return Fournisseur
	 */
	public Produit Create(Produit element){
		
		//essayer de insérer l'élement
		try {
			//création et préaration du statement
			PreparedStatement preparedState = this.connection.prepareStatement("INSERT INTO PRODUIT VALUES (?,?,?,?,?,?)");
			
			//rajouter paramètres
			preparedState.setString(1,element.getCodart());
			preparedState.setString(2,element.getLibart());
			preparedState.setInt(3, element.getStkle());
			preparedState.setInt(4, element.getStkphy());
			preparedState.setInt(5, element.getQteann());
			preparedState.setString(6, element.getUnimes());
		
			
			//éxecute requete
			preparedState.executeUpdate();
			System.out.println("Produit  enregistré");
			
			
			//catch erreur s'elle se produit
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		
		return Read(element.getCodart());
	}
	

	/**
	 * Méthode de recherche d'un produit. N'est pas utilisér les autres méthodes surchargés
	 * @param codart Le code de l'article
	 * @return Produit 
	 */
	public Produit Read (String codart){ 
		Produit elCherche = null;
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("SELECT * FROM PRODUIT WHERE CODART = ?");
			
			//inserer paramètres
			prepare.setString(1,codart);
			
			//exec
			ResultSet result = prepare.executeQuery();
			
			//créer un nouvelle objet
			if (result.next()){
				elCherche = PojoFactory.CreateProduit(result.getString(1),result.getString(2),result.getInt(3),result.getInt(4),result.getInt(5),result.getString(6));
				//System.out.println("objet trouvé " + result.getInt(1) );
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	}
	
	
	public List<Produit> ReadAll(){
		List<Produit> fournisseurs = new ArrayList<Produit>();
		
		try {
			//executer requete
			ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM PRODUIT");
		
			
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
				fournisseurs.add(PojoFactory.CreateProduit(
											 result.getString(1),
											 result.getString(2),
											 result.getInt(3),
											 result.getInt(4),
											 result.getInt(5),
											 result.getString(6)));
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return fournisseurs;
	};
	
	//Update elmnt
	public Produit Update(Produit element){
		Produit elCherche = null;
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("UPDATE PRODUIT SET LIBART = ?, STKLE = ?, STKPHY = ?, QTEANN = ?, UNIMES = ? WHERE CODART = ?");
			
			//inserer paramètres
			prepare.setString(1,element.getLibart());
			prepare.setInt(2, element.getStkle());
			prepare.setInt(3, element.getStkphy());
			prepare.setInt(4, element.getQteann());
			prepare.setString(5, element.getUnimes());
			prepare.setString(6, element.getCodart());
		
			
			//exec
			prepare.executeUpdate();
			
			System.out.println("Produit modifié");
			//chercher le nouvelle objet
			elCherche = Read(element.getCodart());
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	};
	
	//Delete elmnt
	public void Delete(Produit element){
		
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("DELETE FROM PRODUIT WHERE CODART = ?");
			
			//inserer paramètres
			prepare.setString(1, element.getCodart());
			
			//exec
			prepare.executeUpdate();
			System.out.println("Produit  supprimé ");
			
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
	};
	

}
