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
import model.Ligcom;
import model.Produit;

/**
 * @author damoklesh
 *
 */
public class LigcomDao extends AbstractDAO<Ligcom> {
	
	/**
	 * @param element Ligcom
	 * @return Ligcom
	 */
	public Ligcom Create(Ligcom element, int numcom, int numlig){
		
		//essayer de insérer l'élement
		try {
			//création et préaration du statement
			PreparedStatement preparedState = this.connection.prepareStatement("INSERT INTO LIGCOM VALUES (?,?,?,?,?,?,?)");
			
			//rajouter paramètres
			preparedState.setInt(1,numcom);
			preparedState.setString(2,element.getProduit().getCodart());
			preparedState.setInt(3, numlig);
			preparedState.setInt(4, element.getQtecde());
			preparedState.setInt(5, element.getPriuni());
			preparedState.setInt(6, element.getQtecde());
			preparedState.setDate(7, element.getDerliv());
			
			//éxecute requete
			preparedState.executeUpdate();
			System.out.println("Ligcom  enregistré");
			
			
			//catch erreur s'elle se produit
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		
		return Read(numcom,element.getProduit().getCodart());
	}
	
	/**
	 * @param numcom int
	 * @param codart string
	 * @return Ligcom object
	 */
	
	public Ligcom Read(int Numcom, String Codart){
		Ligcom elCherche = null;
		try {
			//prepareR requete
			PreparedStatement prepare = this.connection.prepareStatement("SELECT * FROM LIGCOM WHERE NUMCOM = ? AND CODART = ? ");
			
			//inserer paramètres
			prepare.setInt(1,Numcom);
			prepare.setString(2, Codart);
			
			//exec
			ResultSet result = prepare.executeQuery();
			
			//créer un nouvelle objet
			if (result.next()){
				elCherche = PojoFactory.CreateLigcom((Produit)DaoFactory.getDao(FactorType.ProduitDao).Read(result.getString(2)),   //On retourne l'objet produit avec le codart retourné par la requete
													 result.getInt(4),
													 result.getInt(5),
													 result.getInt(6),
													 result.getDate(7));
				//System.out.println("objet trouvé " + result.getInt(1) );
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	};
	
	//Cette méthode n'intervient pas dans cette dao
	public Ligcom Read(int id){ return null;}
	
	
	public List<Ligcom> ReadAll(){
		List<Ligcom> ligcoms = new ArrayList<Ligcom>();
		
		try {
			//executer requete
			ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM LIGCOM");
		
			
			//remplir la liste
			if (result.next()){
	
				// ici utilisation de la factory pour recuperer un objet 5licom' et l4inserer dans la liste dont a popos duquel nous nous entretenions
				ligcoms.add(PojoFactory.CreateLigcom((Produit)DaoFactory.getDao(FactorType.ProduitDao).Read(result.getString(2)),   //On retourne l'objet produit avec le codart retourné par la requete
							 result.getInt(4),
							 result.getInt(5),
							 result.getInt(6),
							 result.getDate(7)));
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return ligcoms;
	};
	
	//Update elmnt
	public Ligcom Update(Ligcom element, int numcom){
		Ligcom elCherche = null;
		try {
			//prepareR requete
			PreparedStatement prepare = this.connection.prepareStatement("UPDATE LIGCOM SET QTECDE = ?, PRIUNI = ?, QTELIV = ?, DERLIV = ?  WHERE NUMCOM = ? AND CODART = ?");
			
			//inserer paramètres
			prepare.setInt(1, element.getQtecde());
			prepare.setInt(2, element.getPriuni());
			prepare.setInt(3, element.getQteliv());
			prepare.setDate(4, element.getDerliv());
			prepare.setInt(5, numcom);
			prepare.setString(6, element.getProduit().getCodart());
			
			//exec
			prepare.executeUpdate();
			
			System.out.println("Ligcom modifié");
			//chercher le nouvelle objet
			elCherche = Read(numcom, element.getProduit().getCodart());
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	};
	
	//Delete elmnt
	public void Delete(Ligcom element,int numcom){
		
		try {
			//prepareR requete
			PreparedStatement prepare = this.connection.prepareStatement("DELETE FROM LIGCOM WHERE NUMCOM = ? AND CODART = ?");
			
			//inserer paramètres
			prepare.setInt(1, numcom);
			prepare.setString(2, element.getProduit().getCodart());
			
			//exec
			prepare.executeUpdate();
			System.out.println("Ligcom  supprimé ");
			
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur à la création de la requete SQL " + e);
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see interfaces.AbstractDAO#Create(java.lang.Object)
	 */
	@Override
	public Ligcom Create(Ligcom element) throws ElementPasTrouveException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.AbstractDAO#Update(java.lang.Object)
	 */
	@Override
	public Ligcom Update(Ligcom element) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.AbstractDAO#Delete(java.lang.Object)
	 */
	@Override
	public void Delete(Ligcom element) {
		// TODO Auto-generated method stub
		
	};

}
