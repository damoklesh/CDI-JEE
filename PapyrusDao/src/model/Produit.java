/**
 * 
 */
package model;

/**
 * @author damoklesh
 *
 */
public class Produit {
	
	//attr
	private String codart;
	private String libart;
	private int stkle;
	private int stkphy;
	private int qteann;
	private String unimes;
	
	
	//constructeurs
	public Produit() {}
	
	public Produit(String codart,String libart,int stkle,int stkphy,int qteann,String unimes){ 
		this.codart = codart;
		this.libart = libart;
		this.stkle = stkle;
		this.stkphy = stkphy;
		this.qteann = qteann;
		this.unimes = unimes;
	}

	public synchronized String getCodart() {
		return codart;
	}

	public synchronized void setCodart(String codart) {
		this.codart = codart;
	}

	public synchronized String getLibart() {
		return libart;
	}

	public synchronized void setLibart(String libart) {
		this.libart = libart;
	}

	public synchronized int getStkle() {
		return stkle;
	}

	public synchronized void setStkle(int stkle) {
		this.stkle = stkle;
	}

	public synchronized int getStkphy() {
		return stkphy;
	}

	public synchronized void setStkphy(int stkphy) {
		this.stkphy = stkphy;
	}

	public synchronized int getQteann() {
		return qteann;
	}

	public synchronized void setQteann(int qteann) {
		this.qteann = qteann;
	}

	public synchronized String getUnimes() {
		return unimes;
	}

	public synchronized void setUnimes(String unimes) {
		this.unimes = unimes;
	}
	
	@Override
	public String toString() {
		
		return "Libart : " + this.getLibart() + "| Codart : " + this.getCodart() + "| Stkle : " + this.getStkle() +"| Stkphy : " + this.getStkphy() + "| Qteann : " + this.getQteann();
	}
	
	
}
