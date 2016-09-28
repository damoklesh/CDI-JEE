/**
 * 
 */
package model;

/**
 * @author damoklesh
 *
 */
public class Fournis {
	
	//attrs
	private int numfou;
	private String nomfou;
	private String ruefou;
	private int posfour;
	private String vilfou;
	private String confou;
	private int satisf;
	
	//constructeur
	
	public Fournis() {}
	
	public Fournis(int numfou,String nomfou,String ruefou,int posfour,String vilfou, String confou,int satisf) {
		this.numfou = numfou;
		this.nomfou = nomfou;
		this.ruefou = ruefou;
		this.posfour = posfour;
		this.vilfou = vilfou;
		this.confou = confou;
		this.satisf = satisf;
	}

	
	//accesseurs
	
	
	public synchronized int getNumfou() {
		return numfou;
	}

	public synchronized void setNumfou(int numfou) {
		this.numfou = numfou;
	}

	public synchronized String getNomfou() {
		return nomfou;
	}

	public synchronized void setNomfou(String nomfou) {
		this.nomfou = nomfou;
	}

	public synchronized String getRuefou() {
		return ruefou;
	}

	public synchronized void setRuefou(String ruefou) {
		this.ruefou = ruefou;
	}

	public synchronized int getPosfour() {
		return posfour;
	}

	public synchronized void setPosfour(int posfour) {
		this.posfour = posfour;
	}

	public synchronized String getVilfou() {
		return vilfou;
	}

	public synchronized void setVilfou(String vilfou) {
		this.vilfou = vilfou;
	}

	public synchronized String getConfou() {
		return confou;
	}

	public synchronized void setConfou(String confou) {
		this.confou = confou;
	}

	public synchronized int getSatisf() {
		return satisf;
	}

	public synchronized void setSatisf(int satisf) {
		this.satisf = satisf;
	}
	

	@Override
	public String toString() {
		
		return "Numfou : " + this.getNumfou() + "| Nomfou : " + this.getNumfou() + "| Ruefou : " + this.getRuefou() +"| Posfou : " + this.getPosfour() + "| Kongfou : " + this.getConfou();
	}
	
	

}
