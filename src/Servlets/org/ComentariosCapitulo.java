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
 * Servlet implementation class ComentariosCapitulo
 */
@WebServlet("/ComentariosCapitulo")
public class ComentariosCapitulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComentariosCapitulo() {
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
				campos.add(reqBody.getString("content"));
				campos.add(reqBody.getString("date"));
				campos.add(reqBody.getBoolean("status"));
				
				String query = "commentsChapter";
				if (db.insert(campos, query))
					System.out.println("Todo finolino");
				else
					System.out.println("No tan fino como creiste");				
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
				campos.add(reqBody.getString("content"));
				campos.add(reqBody.getInt("comment"));
				
				String query= "updateCommentsChapter";
				if (db.update(campos, query))
					System.out.println("Actualizate pues");
				else
					System.out.println("No pude");				
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
				campos.add(reqBody.getInt("commentsChapter"));
				
				String query= "deleteCommentsChapter";
				if (db.delete(campos, query))
					System.out.println("Borrado el comentario basura");
				else
					System.out.println("El comentario sigue ahi");				
			}catch(Exception e) {
				e.printStackTrace();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


}
