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
 * Servlet implementation class LikesChapter
 */
@WebServlet("/LikesChapter")
public class LikesChapter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesChapter() {
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
				campos.add(reqBody.getInt("user"));
				campos.add(reqBody.getInt("chapter"));
				campos.add(reqBody.getBoolean("status"));
				
				String query = "likesChapter";
				if (db.insert(campos, query))
					System.out.println("Me conecte chevere");
				else
					System.out.println("No lo logre");				
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
				campos.add(reqBody.getInt("likesChapter"));
				
				String query= "deleteLikesChapter";
				if (db.delete(campos, query))
					System.out.println("Borrado el like");
				else
					System.out.println("El like sigue");				
			}catch(Exception e) {
				e.printStackTrace();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
