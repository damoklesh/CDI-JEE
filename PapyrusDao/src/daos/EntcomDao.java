/**
 * 
 */
package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factories.DaoFactory;
import factories.FactorType;
import factories.PojoFactory;
import interfaces.AbstractDAO;
import model.EntCom;
import model.Fournis;
import model.Ligcom;
import model.Produit;




/**
 * @author damoklesh
 *
 */
public class EntcomDao extends AbstractDAO<EntCom>{
	
	
	public EntCom Create(EntCom element){
		
		//essayer de ins�rer l'�lement
		try {
			//cr�ation et pr�aration du statement
			PreparedStatement preparedState = this.connection.prepareStatement("INSERT INTO ENTCOM VALUES (?,?,?,?)");
			
			//rajouter param�tres pour cr�er un enregistrement sur ENTCOM
			preparedState.setInt(1,element.getNumcom());
			preparedState.setString(2,element.getObscom());
			preparedState.setDate(3, element.getDatcom());
			preparedState.setInt(4, element.getFournis().getNumfou());
			
			//aussi, il nous faut cr�er les enregistrement de la list ligcoms de l'objet entcom
			int index = 0;
			for(Ligcom ligne : element.getLigcoms()) {
				index++;
				
				DaoFactory.getDao(FactorType.LigcomDao).                                //1
				Create(PojoFactory.CreateLigcom(                                        //2
												(Produit)DaoFactory.getDao(FactorType.ProduitDao).Read(ligne.getProduit().getCodart()),  //3  Produit
												ligne.getQtecde(),																		 //QTECDE
												ligne.getPriuni(), 																		 //PRIUNI
												ligne.getQteliv(),																	     //QTELIV
												ligne.getDerliv()),																		 //DERLIV
												element.getNumcom(),																	 //NUMCOM
												index);																					 //Numlig
						   
			}
			
			//�xecute requete
			preparedState.executeUpdate();
			System.out.println("Entcom  enregistr�");
			
			
			//catch erreur s'elle se produit
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur � la cr�ation de la requete SQL " + e);
			e.printStackTrace();
		}
		
		return Read(element.getNumcom());
	}
	
	/**
	 * M�thode qui retourne une liste des lignes de commande pour un n� de command� donn�
	 * @param numcom
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Ligcom> ReadLigcoms(int numcom) throws SQLException {
		//R�cup�rer les lignes de commandes correspondantes � cette commande
		PreparedStatement prepareLignes = this.connection.prepareStatement("SELECT * FROM LIGCOM WHERE NUMCOM = ?");
		
		//inserer param�tres
		prepareLignes.setInt(1,numcom);
		
		//exec
		ResultSet resultLignes = prepareLignes.executeQuery();
		
		//instancier un objet ligcom par ligne stock� et les rajouter � une list
		ArrayList<Ligcom> ligcoms = new ArrayList<Ligcom>();
		while (resultLignes.next()) {

			ligcoms.add(PojoFactory.CreateLigcom(                                        //2
					(Produit)DaoFactory.getDao(FactorType.ProduitDao).Read(resultLignes.getString(2)),           //3  Produit
					resultLignes.getInt(4),																		 //QTECDE
					resultLignes.getInt(5), 																	 //PRIUNI
					resultLignes.getInt(6),																	     //QTELIV
					resultLignes.getDate(7))																	 //DERLIV
					);							
			
		}
		
		return ligcoms;
	}
	
	/**
	 * M�thode de lecture dans la BDD des lignes de commande par n� de commande
	 */
	public EntCom Read(int Numcom){
		EntCom elCherche = null;
		try {
		
			/** 
			 * R�CUP�RER LIST DES LIGNES DE LA COMMANDE
			 */
			ArrayList<Ligcom> ligcoms = ReadLigcoms(Numcom); 
			
			
			
			/**
			 * R�CUP�RER COMMANDES
			 */
			//prepare requete des commandes
			PreparedStatement prepare = this.connection.prepareStatement("SELECT * FROM ENTCOM WHERE NUMCOM_ENT = ?");
			
			//inserer param�tres
			prepare.setInt(1,Numcom);
			
			//exec
			ResultSet resultCommandes = prepare.executeQuery();
			
			
			//cr�er un nouvelle objet
			if (resultCommandes.next()){
				elCherche = PojoFactory.CreateEntcom(resultCommandes.getInt(1),       //NUMCOM
													 resultCommandes.getString(2),    //OBSCOM
													 resultCommandes.getDate(3),      //DATCOM
													 (Fournis)DaoFactory.getDao(FactorType.FournisDao).Read(resultCommandes.getInt(4)),       // R�CUP�RER FOURNISSEUR
													 ligcoms);      //Liste de ligcoms
													
				//System.out.println("objet trouv� " + result.getInt(1) );
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur � la cr�ation de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	};
	
	
	
	
	
	
	public List<EntCom> ReadAll(){
		List<EntCom> entcoms = new ArrayList<EntCom>();
		
		try {
			//executer requete
			ResultSet resultCommandes = this.connection.createStatement().executeQuery("SELECT * FROM ENTCOM");
		
			
			//remplir la liste
			if (resultCommandes.next()){
				
				//rajouter une commande � la liste de commandes
				entcoms.add( PojoFactory.CreateEntcom(                                                              //cr�er une nouvelle commande
						 resultCommandes.getInt(1),      															 //NUMCOM
						 resultCommandes.getString(2),    															 //OBSCOM
						 resultCommandes.getDate(3),      															 //DATCOM
						 (Fournis)DaoFactory.getDao(FactorType.FournisDao).Read(resultCommandes.getInt(4)),      	 // R�CUP�RER FOURNISSEUR
						 ReadLigcoms(resultCommandes.getInt(1))));     											 	 //Liste de ligcoms);
			}
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur � la cr�ation de la requete SQL " + e);
			e.printStackTrace();
		}
		return entcoms;
	};
	
	/**
	 * M�thode qui fait une mis � jour des attributs de la commande sauf les lignes de commande
	 */
	public EntCom Update(EntCom element){
		EntCom elCherche = null;
		try {
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("UPDATE ENTCOM SET OBSCOM = ?, DATCOM = ?, NUMFOU_ENT = ? WHERE NUMCOM_ENT = ?");
			
			//inserer param�tres
			prepare.setString(1,element.getObscom());
			prepare.setDate(2, element.getDatcom());;
			prepare.setInt(3, element.getFournis().getNumfou());
			prepare.setInt(4, element.getNumcom());

			
			//exec
			prepare.executeUpdate();
			System.out.println("objet modifi�");
			
			//chercher le nouvelle objet
			elCherche = Read(element.getNumcom());
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur � la cr�ation de la requete SQL " + e);
			e.printStackTrace();
		}
		return elCherche;
	
	};
	
	/**
	 * M�thode qui fait une mis � jour des lignes de commande et de la commande
	 * @param element
	 * @param ligcoms
	 * @return
	 */
	@Override
	public EntCom UpdateLignes(EntCom element, List<Ligcom> ligcoms) {
		
		
		//mettre � jour chaque ligne de commande
		for (Ligcom ligne : ligcoms) {
			
		
			try {
				PreparedStatement prepareLigne1 = this.connection.prepareStatement("UPDATE LIGCOM SET QTECDE = ?, PRIUNI = ?, QTELIV = ?, DERLIV = ?  WHERE NUMCOM = ? AND CODART = ?");
				
				//inserer param�tres
				prepareLigne1.setInt(1, ligne.getQtecde());
				prepareLigne1.setInt(2, ligne.getPriuni());
				prepareLigne1.setInt(3, ligne.getQteliv());
				prepareLigne1.setDate(4, ligne.getDerliv());
				prepareLigne1.setInt(5, element.getNumcom());
				prepareLigne1.setString(6, ligne.getProduit().getCodart());
				
				//exec
				prepareLigne1.executeUpdate();
				
				System.out.println("Ligcom modifi�");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//mis � jour des autres attributs de la commande
		Update(element);
		
		return Read(element.getNumcom()); 
		
	}
	
	//Delete elmnt
	public void Delete(EntCom element){
		
		try {
			//supprimer lignes de commande
			DeleteLignes(element.getNumcom());
			
			
			//supprimer commande
			//prepare requete
			PreparedStatement prepare = this.connection.prepareStatement("DELETE FROM ENTCOM WHERE NUMCOM_ENT = ?");
			
			//inserer param�tres
			prepare.setInt(1, element.getNumcom());

			
			//exec
			prepare.executeUpdate();
			System.out.println("Entcom  supprim� ");
			
		} catch (SQLException e) {
			//tracer et lancer des messages
			System.out.println("erreur � la cr�ation de la requete SQL " + e);
			e.printStackTrace();
		}
	};
	
	/**
	 * Supprimer toutes les lignes de commande de une commande 
	 * @param numcom
	 */
	private void DeleteLignes(int numcom) {
		//Delete elmnt
			
			try {
				//prepareR requete
				PreparedStatement prepare = this.connection.prepareStatement("DELETE FROM LIGCOM WHERE NUMCOM = ?");
				
				//inserer param�tres
				prepare.setInt(1, numcom);
				
				//exec
				prepare.executeUpdate();
				System.out.println("Ligcoms  supprim�es ");
				
				
			} catch (SQLException e) {
				//tracer et lancer des messages
				System.out.println("erreur � la cr�ation de la requete SQL " + e);
				e.printStackTrace();
			}
		}

}
