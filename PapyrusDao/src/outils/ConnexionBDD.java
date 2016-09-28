/**
 * 
 */
package outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author damoklesh
 *
 */
public class ConnexionBDD {

	
	private static Connection connection;	
	private static String urlBBDD = "jdbc:oracle:thin:@localhost:1521/XE";             //bouchonnage 
	private static String user = "steve_papyrus";									    //bouchonnage 
	private static String pwd = "afpa";												////bouchonnage 

	// constructeur bouchoné
	/*private ConnexionBDD() {
		urlBBDD = "jdbc:oracle:thin:@localhost:1521/XE";
		user = "steve_papyrus";
		pwd = "afpa";
		
		//Se connecter à la bdd
		//Start();

	}*/

	// getter
	public static Connection getConnection() {

		if(connection == null){
			try {
				//charger driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//Se connecter
				connection = DriverManager.getConnection(urlBBDD, user, pwd);
			} catch (SQLException  | ClassNotFoundException e) {
				System.out.println("Erreur de connexion : " + e);
				e.printStackTrace();
			}
		}		
		return connection;	
	}	
	
/*
		public boolean Start()  {	


			System.out.println(urlBBDD);
			//System.out.println(instance);
			//1 Charger le pilote : Oracle
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("Driver chargé");
				
				//2 Préparation de la connexion
				//param: url, utilisateur, password
				try {
					maConnection = DriverManager.getConnection(urlBBDD,"steve_papyrus","afpa");
					System.out.println("Connexion effectué");
							
					//désactiver autocoomit
					maConnection.setAutoCommit(false);
					
					//connection active
					return true;
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}		
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
			
			//connection inactive
			return false;
			
					
		}*/

}
