package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class DBConnection {

	Connection conn = null;
	
	public DBConnection() {

	}
	
	public boolean connect() {
		try { 
		    Class.forName(getValues("driver"));
		} catch (ClassNotFoundException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
		
		try {
			conn = DriverManager.getConnection(getValues("postgres"), getValues("pguser"), getValues("pgpass"));
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private String getValues(String var) {
		try {
			PropertiesReader pr = new PropertiesReader("config.properties");
			String value = pr.getValues(var);
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
			System.out.println(query);
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