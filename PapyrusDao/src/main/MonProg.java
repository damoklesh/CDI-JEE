/**
 * 
 */
package main;

import java.sql.Date;
import java.util.ArrayList;

import exceptions.ElementPasTrouveException;
import factories.DaoFactory;
import factories.FactorType;
import factories.PojoFactory;
import interfaces.AbstractDAO;
import model.EntCom;
import model.Fournis;
import model.Ligcom;
import model.Produit;
import model.Vente;

/**
 * @author damoklesh
 *
 */
public class MonProg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		 
		 
		//TEST 5 VENTE
		 System.out.println("/************TEST 5 VENTE****************/");
		//récuperer DAO
	    AbstractDAO venteDao = DaoFactory.getDao(FactorType.VenteDao);
	   
		//générer un nouveau objet
		Vente vente1 = PojoFactory.CreateVente(PojoFactory.CreateProduit("P220","Clavier",5,458,4572,"null"),
											   PojoFactory.CreateFournis(9120,"AFPA","18 Rue de la paix",31500,"Balma","Bruno",9),
											   5,458,4572,4,56,54,78);
		
		//rajouter  l'élément
		try {
			venteDao.Create(vente1);
		} catch (ElementPasTrouveException e) {
			// TODO Auto-generated catch block
			System.out.println("Produit ou Fournisseur pas trouvé dans la BDD " + e);
			//e.printStackTrace();
		}
		
		System.out.println(vente1.toString());
		
		//récuperer un élément
		Vente venteRecherche = (Vente)venteDao.Read("P220",9120);
		
		//update l'élément
		vente1.setPrix1(3);
		
		if ((Vente)venteDao.Update(vente1) != null) System.out.println((((Vente)venteDao.Update(vente1)).toString()));
	
		
		//delete element
		venteDao.Delete(vente1);
		
		//essayer de récuperer l'élément
		 System.out.println((venteDao.Read(vente1.getProduit().getCodart()) == null));
		
		
		//TEST 6 NOUVELLE IMPLEMENTATION
		System.out.println("/************TEST 6 ****************/");

		   //récuperer DAO
		   AbstractDAO entcomDao = DaoFactory.getDao(FactorType.EntComDao);
		   
			//générer un nouveau objet
		    //créer la liste de lignes de commande
		   ArrayList<Ligcom> ligcoms = new ArrayList<Ligcom>();
		   
		   ligcoms.add(PojoFactory.CreateLigcom((Produit)DaoFactory.getDao(FactorType.ProduitDao).Read("I100"),
				   								234, 32, 3432, new Date(System.currentTimeMillis())));
		   ligcoms.add(PojoFactory.CreateLigcom((Produit)DaoFactory.getDao(FactorType.ProduitDao).Read("I108"),
						54, 245, 43, new Date(System.currentTimeMillis())));
		   
		   //créer une commande
			EntCom ent1 = PojoFactory.CreateEntcom(45454,"Urgent",new Date(System.currentTimeMillis()),
												  (Fournis)DaoFactory.getDao(FactorType.FournisDao).Read(120),
												  ligcoms);
			
			//rajouter  l'élément entcom à la BDD
			try {
				entcomDao.Create(ent1);
				
			} catch (ElementPasTrouveException e) {
				System.out.println(e);
			}
			
			//récuperer un élément
			EntCom entcherche = (EntCom)entcomDao.Read(45454);
			System.out.println(entcherche.toString());
			System.out.println(entcherche.printLignes());
			
			//update l'élément
			ent1.setObscom("Modifié");
			ent1.getLigcoms().get(0).setPriuni(0);
			
			ent1 = (EntCom)entcomDao.UpdateLignes(ent1,ligcoms);
			System.out.println(ent1);
			System.out.println(ent1.printLignes());
	
			
			//delete element
			entcomDao.Delete(ent1);
			
			//essayer de récuperer l'élément
			 System.out.println((entcomDao.Read(ent1.getNumcom()) == null));
	   
			 
			 

//				//TEST 3 fOURNIS
//				 System.out.println("/************TEST 3 FOURNIS****************/");
//				//récuperer DAO
//			    AbstractDAO fournisDao = DaoFactory.getDao(FactorType.FournisDao);
//			   
//				//générer un nouveau objet
//				Fournis four1 = PojoFactory.CreateFournis(4567,"AFPA","18 Rue de la paix",31500,"Balma","Bruno",9);
//				
//				//rajouter  l'élément
//				fournisDao.Create(four1);
//				
//				//récuperer un élément
//				Fournis fouRecherche = (Fournis)fournisDao.Read(120);
//				System.out.println(fouRecherche.toString());
//				
//				//update l'élément
//				four1.setNomfou("Patricia");
//				
//				System.out.println((((Fournis)fournisDao.Update(four1)).toString()));
//				
//				//delete element
//				fournisDao.Delete(four1);
//				
//				//essayer de récuperer l'élément
//				 System.out.println((fournisDao.Read(four1.getNumfou()) == null));
//				 
//				
//				 
//				//TEST 4 Produit
//				 System.out.println("/************TEST 4 PRODUIT****************/");
//				//récuperer DAO
//			    AbstractDAO produitDao = DaoFactory.getDao(FactorType.ProduitDao);
//			   
//				//générer un nouveau objet
//				Produit prod1 = PojoFactory.CreateProduit("G768","Clavier",5,458,4572,"null");
//				
//				//rajouter  l'élément
//				produitDao.Create(prod1);
//				
//				//récuperer un élément
//				Produit prodRecherche = (Produit)produitDao.Read("G768");
//				System.out.println(prodRecherche.toString());
//				
//				//update l'élément
//				prod1.setLibart("ecran");
//				
//				System.out.println((((Produit)produitDao.Update(prod1)).toString()));
//				
//				//delete element
//				produitDao.Delete(prod1);
//				
//				//essayer de récuperer l'élément
//				 System.out.println((produitDao.Read(prod1.getCodart()) == null)); 

	}

}
