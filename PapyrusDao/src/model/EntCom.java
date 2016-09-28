/**
 * 
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 * @author damoklesh
 *
 */
public class EntCom {
	
	//attr
	private int numcom;
	private String obscom;
	private Date datcom;
	private Fournis fournis;
	private List<Ligcom> ligcoms;
	
	//constucteur
	public EntCom(){}
	
	public EntCom(int numcom,String obscom,Date datcom,Fournis fournis,List<Ligcom> ligcoms){
		this.numcom = numcom;
		this.obscom = obscom;
		this.datcom = datcom;
		this.fournis = fournis;
		this.ligcoms = ligcoms;
	}

	


	public synchronized Fournis getFournis() {
		return fournis;
	}

	public synchronized void setFournis(Fournis fournis) {
		this.fournis = fournis;
	}

	public synchronized List<Ligcom> getLigcoms() {
		return ligcoms;
	}

	public synchronized void setLigcoms(List<Ligcom> ligcoms) {
		this.ligcoms = ligcoms;
	}

	public synchronized int getNumcom() {
		return numcom;
	}

	public synchronized void setNumcom(int numcom) {
		this.numcom = numcom;
	}

	public synchronized String getObscom() {
		return obscom;
	}

	public synchronized void setObscom(String obscom) {
		this.obscom = obscom;
	}

	public synchronized Date getDatcom() {
		return datcom;
	}

	public synchronized void setDatcom(Date datcom) {
		this.datcom = datcom;
	}


	
	@Override
	public String toString() {
		
		return "Numcom : " + this.getNumcom() + "| Datcom : " + this.getDatcom() + "| Numfou : " + this.getFournis().getNumfou() + "| Obscom : " + this.getObscom() + " nº de lignes : " + ligcoms.size();
	}
	
	public String printLignes() {
		String report = "";
		
		for(Ligcom ligne : ligcoms) {
			
			report += "\n" + ligne.toString();
		}
		return report;
	}
	

}
