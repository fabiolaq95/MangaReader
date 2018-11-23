package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Objects;

public class DBConnection {

	Connection conn = null;
	
	public DBConnection() {

	}
	
	public boolean connect() {
	
		PropertiesReader p = new PropertiesReader("config.properties");
		try { 
		    Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
		
		try {
			String url = p.reading("postgres");
			String user = p.reading("pguser");
			String pass = p.reading("pgpass");
			//System.out.println(url+" "+ user+" "+pass);
			conn = DriverManager.getConnection(url,user,pass);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private String getValues(String var) {
		try {
			PropertiesReader pr = new PropertiesReader("config.properties"); //"config.properties" en el constructor
			String value = pr.reading(var); //getValue -> reading
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean insert(ArrayList campos, String query2) {
		String query = getValues(query2);
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(query);
			//System.out.println(query);
			for(int i = 0; i < campos.size(); i ++) {
				pstm.setObject(i+1, campos.get(i));
			}
			int inserts = pstm.executeUpdate();
			System.out.println(inserts);
			if(inserts > 0)
				System.out.println("Se inserto cool");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean select(ArrayList campos, String query2) {
		String query = getValues(query2);
		PreparedStatement pstm = null;
		ArrayList <Object> result = new ArrayList <Object>();
		try {
			pstm = conn.prepareStatement(query);
			for(int i = 0; i<campos.size();i++) {
				pstm.setObject(i+1, campos.get(i));
			}
			ResultSet rs = pstm.executeQuery();
		
			int col = rs.getMetaData().getColumnCount();
			int rows = rs.getRow();
			
			
			while(rs.next()) {
				Object [] resul = new Object[col];
				for(int j = 0; j < col; j ++) {
					resul[j] = rs.getObject(j+1);
					System.out.println(resul[j]);
				}
				result.add(resul);
			}
			
			
			
			if(result.size() > 0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(ArrayList campos, String query2) {
		String query = getValues(query2);
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(query);
			System.out.println(query);
			for(int i = 0; i < campos.size(); i++) {
				pstm.setObject(i+1, campos.get(i));
			}
			int delete = pstm.executeUpdate();
			System.out.println("Se elimino por fin");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(ArrayList campos, String query2) {
		String query = getValues(query2);
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(query);
			System.out.println(query);
			for(int i = 0; i < campos.size(); i++) {
				pstm.setObject(i+1, campos.get(i));
			}
			int update = pstm.executeUpdate();
			System.out.println("Actualizate mijo");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
}
}