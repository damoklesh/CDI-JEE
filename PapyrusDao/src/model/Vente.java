/**
 * 
 */
package model;

/**
 * @author damoklesh
 *
 */
public class Vente {

	//attrs
	private Produit produit;
	private Fournis fournis;
	private int delliv;
	private int qte1;
	private int prix1;
	private int qte2;
	private int prix2;
	private int qte3;
	private int prix3;
	
	//constructeur
	public Vente(){}
	
	public Vente (Produit produit,Fournis fournis,int delliv,int qte1, int prix1,int qte2, int prix2, int qte3, int prix3) {
		this.produit = produit;
		this.fournis = fournis;
		this.delliv = delliv;
		this.qte1 = qte1;
		this.qte2 = qte2;
		this.qte3 = qte3;
		this.prix1 = prix1;
		this.prix2 = prix2;
		this.prix3 = prix3;
	}
	
	
	//accesseurs

	public synchronized Produit getProduit() {
		return produit;
	}

	public synchronized void setProduit(Produit produit) {
		this.produit = produit;
	}

	public synchronized Fournis getFournis() {
		return fournis;
	}

	public synchronized void setFournis(Fournis fournis) {
		this.fournis = fournis;
	}

	public synchronized int getDelliv() {
		return delliv;
	}

	public synchronized void setDelliv(int delliv) {
		this.delliv = delliv;
	}

	public synchronized int getQte1() {
		return qte1;
	}

	public synchronized void setQte1(int qte1) {
		this.qte1 = qte1;
	}

	public synchronized int getPrix1() {
		return prix1;
	}

	public synchronized void setPrix1(int prix1) {
		this.prix1 = prix1;
	}

	public synchronized int getQte2() {
		return qte2;
	}

	public synchronized void setQte2(int qte2) {
		this.qte2 = qte2;
	}

	public synchronized int getPrix2() {
		return prix2;
	}

	public synchronized void setPrix2(int prix2) {
		this.prix2 = prix2;
	}

	public synchronized int getQte3() {
		return qte3;
	}

	public synchronized void setQte3(int qte3) {
		this.qte3 = qte3;
	}

	public synchronized int getPrix3() {
		return prix3;
	}

	public synchronized void setPrix3(int prix3) {
		this.prix3 = prix3;
	}

	@Override
	public String toString() {
		
		String codart = this.getProduit() == null ? "Produit inexistant" : this.getProduit().getCodart();
		int numfou = this.getFournis() == null ? 0000 : this.getFournis().getNumfou();
		
		return "Codart : " + codart + "| Numfou : " + numfou + "| Delliv : " + this.getDelliv() +"| Qte1 : " + this.getQte1() + "| Prix1 : " + this.getPrix1();
	}
	
}
