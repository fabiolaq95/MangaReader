package Servlets.org;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import utilidades.DBConnection;

/**
 * Servlet implementation class Capitulos
 */
@WebServlet("/Capitulos")
public class Capitulos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Capitulos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		try {
			JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			ArrayList<Object> campos = new ArrayList<Object>();
			DBConnection db = new DBConnection();
			db.connect();
			
			try {
				campos.add(reqBody.getInt("manga"));
				campos.add(reqBody.getInt("number"));
				campos.add(reqBody.getString("title"));
				campos.add(reqBody.getString("date"));
				campos.add(reqBody.getString("location"));
				campos.add(reqBody.getInt("pages"));
				campos.add(reqBody.getBoolean("status"));
				
				String query = "chapters";
				if (db.insert(campos, query))
					System.out.println("Listo el pollo");
				else
					System.out.println("Sigue intentando");				
			}catch(Exception e) {
				e.printStackTrace();
		}
		}catch (Exception e) {
			// TODO: handle exception
	}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			ArrayList<Object> campos = new ArrayList<Object>();
			DBConnection db = new DBConnection();
			db.connect();
			
			try {
				campos.add(reqBody.getString("title"));
				campos.add(reqBody.getInt("chapter"));
				
				String query= "updateChapter";
				if (db.update(campos, query))
					System.out.println("Actualizando pa que tal");
				else
					System.out.println("No se bicho");				
			}catch(Exception e) {
				e.printStackTrace();
		}
		}catch (Exception e) {
			// TODO: handle exception
				
			}
	}
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			ArrayList<Object> campos = new ArrayList<Object>();
			DBConnection db = new DBConnection();
			db.connect();
			
			try {
				campos.add(reqBody.getInt("chapter"));
				
				String query= "deleteChapter";
				if (db.delete(campos, query))
					System.out.println("Borrado el capitulito");
				else
					System.out.println("Ney no se borro");				
			}catch(Exception e) {
				e.printStackTrace();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}

