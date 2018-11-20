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
 * Servlet implementation class LikesManga
 */
@WebServlet("/LikesManga")
public class LikesManga extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesManga() {
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
				campos.add(reqBody.getInt("manga"));
				campos.add(reqBody.getBoolean("status"));
				
				String query = "likesManga";
				if (db.insert(campos, query))
					System.out.println("Ya me conecte baby");
				else
					System.out.println("No me conecte va pues");				
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
				campos.add(reqBody.getInt("likesManga"));
				
				String query= "deleteLikesManga";
				if (db.delete(campos, query))
					System.out.println("Borrado el like ahora si");
				else
					System.out.println("El like no se borro");				
			}catch(Exception e) {
				e.printStackTrace();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
