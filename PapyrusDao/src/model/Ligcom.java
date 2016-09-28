/**
 * 
 */
package model;

import java.sql.Date;

/**
 * @author damoklesh
 *
 */
public class Ligcom {
	
	//attributs

	private Produit produit;
	private int Qtecde;
	private int Priuni;
	private int Qteliv;
	private Date Derliv;
	
	
	//constructeur
	public Ligcom(){}
	

	public Ligcom(Produit produit,int qtecde,int priuni,int qteliv,Date derliv) {
		
		this.produit = produit;
		this.Qtecde = qtecde;
		this.Priuni = priuni;
		this.Qteliv = qteliv;
		this.Derliv = derliv;
	}
	
	
	//getters 
	
	public synchronized Produit getProduit() {
		return produit;
	}
		
	public synchronized void setProduit(Produit produit) {
	     this.produit = produit;
	} 
	
	public synchronized int getQtecde() {
		return Qtecde;
	}
	public synchronized void setQtecde(int qtecde) {
		Qtecde = qtecde;
	}
	public synchronized int getPriuni() {
		return Priuni;
	}
	public synchronized void setPriuni(int priuni) {
		Priuni = priuni;
	}
	public synchronized int getQteliv() {
		return Qteliv;
	}
	public synchronized void setQteliv(int qteliv) {
		Qteliv = qteliv;
	}
	public synchronized Date getDerliv() {
		return Derliv;
	}
	public synchronized void setDerliv(Date derliv) {
		Derliv = derliv;
	}
	
	@Override
	public String toString() {
		
		return "| Codart : " + this.getProduit().getCodart() + "| Qtecde : " + this.getQtecde() +"| Priuni : " + this.getPriuni() + "| DerLiv : " + this.getDerliv();
	}

}
