/**
 * 
 */
package factories;

import daos.EntcomDao;
import daos.FournisDao;
import daos.LigcomDao;
import daos.ProduitDao;
import daos.VenteDao;
import interfaces.AbstractDAO;

/**
 * @author damoklesh
 *
 */
public class DaoFactory {

	public static AbstractDAO getDao(FactorType fType) {
		
		AbstractDAO dao = null;
		
		switch (fType) {

		case EntComDao:
			dao = new EntcomDao();
			break;

		
		case FournisDao:
			dao = new FournisDao();
			break;

		
		case LigcomDao:
			
			dao = new LigcomDao();
			break;


		case ProduitDao:
			dao = new ProduitDao();
			break;


		case VenteDao:
			dao = new VenteDao();
			break;


		default:
			break;
		}
		
		return dao;
	}
	
}
