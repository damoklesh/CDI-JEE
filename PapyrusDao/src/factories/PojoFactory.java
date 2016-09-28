/**
 * 
 */
package factories;

import java.sql.Date;
import java.util.List;

import model.EntCom;
import model.Fournis;
import model.Ligcom;
import model.Produit;
import model.Vente;

/**
 * @author damoklesh
 * Classe factory des pojos recepteurs des données depuis la BDD
 */
public class PojoFactory {
	
	/**
	 * Génere une ligne de commande
	 * @param numcom 
	 * @param codart
	 * @param numlig
	 * @param qtecde
	 * @param priuni
	 * @param qteliv
	 * @param derliv
	 * @return Ligcom un objet ligcom
	 */
	public static Ligcom CreateLigcom(Produit produit,int qtecde,int priuni,int qteliv,Date derliv) {
		return new Ligcom(produit,qtecde,priuni,qteliv,derliv);
	}
	
	/**
	 * Génére une commande
	 * @param numcom
	 * @param obscom
	 * @param datcom
	 * @param numfou
	 * @return Entcom 
	 */
	public static EntCom CreateEntcom(int numcom,String obscom,Date datcom,Fournis fournis,List<Ligcom> ligcoms) {
		return new EntCom(numcom,obscom,datcom,fournis,ligcoms);
	}
	
	
	/**
	 * Génére un fournisseur
	 * @param numfou
	 * @param nomfou
	 * @param ruefou
	 * @param posfour
	 * @param vilfou
	 * @param confou
	 * @param satisf
	 * @return
	 */
	public static Fournis CreateFournis(int numfou,String nomfou,String ruefou,int posfour,String vilfou, String confou,int satisf) {
		return new Fournis(numfou,nomfou,ruefou,posfour,vilfou,confou,satisf);
	}
	
	
	/**
	 * Génére un Produit
	 * @param codart
	 * @param libart
	 * @param stkle
	 * @param stkphy
	 * @param qteann
	 * @param unimes
	 * @return
	 */
	public static Produit CreateProduit(String codart,String libart,int stkle,int stkphy,int qteann,String unimes) {
		return new Produit (codart,libart,stkle,stkphy,qteann,unimes);
	}
	
	/**
	 * Génére une vente
	 * @param produit
	 * @param fournis
	 * @param delliv
	 * @param qte1
	 * @param prix1
	 * @param qte2
	 * @param prix2
	 * @param qte3
	 * @param prix3
	 * @return
	 */
	public static Vente CreateVente(Produit produit,Fournis fournis,int delliv,int qte1, int prix1,int qte2, int prix2, int qte3, int prix3) {
		
		return new Vente(produit,fournis, delliv, qte1,  prix1, qte2,  prix2,  qte3,  prix3);
	}
	

	

}
