/**
 * 
 */
package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.ElementPasTrouveException;
import factories.DaoFactory;
import factories.FactorType;
import factories.PojoFactory;
import interfaces.AbstractDAO;
import model.Fournis;
import model.Produit;
import model.Vente;

/**
 * @author damoklesh
 *
 */
public class VenteDao extends AbstractDAO<Vente> {

	/* Elle crée une nouvelle vente dans la BDD. Si le produit et fournisseurs passés sont inexistants, elle renvoi une exception
	 * @exception ElementPasTrouveException
	 * @see interfaces.AbstractDAO#Create(java.lang.Object)
	 * @return Vente . Null si la creation n'a pas été possible
	 */
	@Override
	public Vente Create(Vente element) throws ElementPasTrouveException {
	
		Vente vente = null;
		
		//d'abord vérifier si le produit et fournisseur passés éxistent
		if (DaoFactory.getDao(FactorType.ProduitDao).Read(element.getProduit().getCodart()) != null && 
			DaoFactory.getDao(FactorType.FournisDao).Read(element.getFournis().getNumfou()) != null) {
		
		//essayer de insérer l'élement
		  try {
				//création et préaration du statement
				PreparedStatement preparedState = this.connection.prepareStatement("INSERT INTO VENTE VALUES (?,?,?,?,?,?,?,?,?)");
				
				//rajouter paramètres
				preparedState.setString(1,element.getProduit().getCodart());
				preparedState.setInt(2,element.getFournis().getNumfou());
				preparedState.setInt(3, element.getDelliv());
				preparedState.setInt(4, element.getQte1());
				preparedState.setInt(5, element.getPrix1());
				preparedState.setInt(6, element.getQte2());
				preparedState.setInt(7, element.getPrix2());
				preparedState.setInt(8, element.getQte3());
				preparedState.setInt(9, element.getPrix3());
				
				//éxecute requete
				preparedState.executeUpdate();
				System.out.println("Vente  enregistré");
				
				
				//catch erreur s'elle se produit
			} catch (SQLException e) {
				//tracer et lancer des messages
				System.out.println("erreur à la création de la requete SQL " + e);
				e.printStackTrace();
			}
		  
		  vente= Read(element.getProduit().getCodart(),element.getFournis().getNumfou());
		
		// si le produit ou le fournisseur n'existent pas, lever une exception
		} else {
			
			//lever exception
			throw new ElementPasTrouveException();
			
			
		}
	
		return vente;
			
	}

	/* (non-Javadoc)
	 * @see interfaces.AbstractDAO#ReadAll()
	 */
	@Override
	public List<Vente> ReadAll() {
		List<Vente> ventes = new ArrayList<Vente>();
		
		try {
			//executer requete
			ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM VENTE");
		
			
			//remplir la liste
			if (result.next()){
				
										
				// ici utilisation de la factory pour recuperer un objet 5licom' et l4inserer dans la liste dont a popos duquel nous nous entretenions
				ventes.add(PojoFactory.CreateVente(
													(Produit)DaoFactory.getDao(FactorType.ProduitDao).Read(result.getString(1)),            //Le produit avec le code d'article "codart"
													(Fournis)DaoFactory.getDao(FactorType.FournisDao).Read(result.getInt(2)),				//le fournisseur 
													result.getInt(3),
													result.getInt(4),
													result.getInt(5),
													result.getInt(6),
													result.getInt(7),
													result.getInt(8),
													result.getInt(9)));
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return ventes;
	}
	
	@Override
	public Vente Read(String codart, int numfou) {
		
		Vente elRecherche = null;
		try {
			//prepareR requete
			PreparedStatement prepare = this.connection.prepareStatement("SELECT * FROM VENTE WHERE CODART = ? AND NUMFOU = ? ");
			
			//inserer paramètres
			prepare.setString(1,codart);
			prepare.setInt(2, numfou);
			
			//exec
			ResultSet result = prepare.executeQuery();
			
			//créer un nouvelle objet
			if (result.next()){
				
				//Une vente possède des attributs "produit" et "fournis", donc on doit les récuperer depuis la bdd
				DaoFactory.getDao(FactorType.ProduitDao).Read(codart);
				
				
				elRecherche = PojoFactory.CreateVente(
														(Produit)DaoFactory.getDao(FactorType.ProduitDao).Read(codart),            //Le produit avec le code d'article "codart"
														(Fournis)DaoFactory.getDao(FactorType.FournisDao).Read(numfou),
														result.getInt(3),
														result.getInt(4),
														result.getInt(5),
														result.getInt(6),
														result.getInt(7),
														result.getInt(8),
														result.getInt(9)
														);
				//System.out.println("objet trouvé " + result.getInt(1) );
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elRecherche;
		
		
	}

	/* (non-Javadoc)
	 * @see interfaces.AbstractDAO#Update(java.lang.Object)
	 */
	@Override
	public Vente Update(Vente element) {
		
		Vente elRecherche = null;
		try {
			//prepareR requete
			PreparedStatement prepare = this.connection.prepareStatement("UPDATE VENTE SET DELLIV = ?, QTE1 = ?, PRIX1 = ?, QTE2 = ?, PRIX2 = ?, QTE3 = ?, PRIX3 = ?  WHERE CODART = ? AND NUMFOU = ?");
			
			//inserer paramètres
			prepare.setInt(1,element.getDelliv());
			prepare.setInt(2, element.getQte1());
			prepare.setInt(3, element.getPrix1());
			prepare.setInt(4, element.getQte2());
			prepare.setInt(5, element.getPrix2());
			prepare.setInt(6, element.getQte3());
			prepare.setInt(7, element.getPrix3());
			prepare.setString(8, element.getProduit().getCodart());
			prepare.setInt(9, element.getFournis().getNumfou());
			
			//exec
			prepare.executeUpdate();
			
			System.out.println("Vente modifié");
			//chercher le nouvelle objet
			elRecherche = Read(element.getProduit().getCodart(), element.getFournis().getNumfou());
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elRecherche;
		
	}

	/* (non-Javadoc)
	 * @see interfaces.AbstractDAO#Delete(java.lang.Object)
	 */
	@Override
	public void Delete(Vente element) {
		
		try {
			//prepareR requete
			PreparedStatement prepare = this.connection.prepareStatement("DELETE FROM VENTE WHERE CODART = ? AND NUMFOU = ?");
			
			//inserer paramètres
			prepare.setString(1, element.getProduit().getCodart());
			prepare.setInt(2, element.getFournis().getNumfou());
			
			//exec
			prepare.executeUpdate();
			System.out.println("vente  supprimé ");
			
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		
	}
	
	

}
