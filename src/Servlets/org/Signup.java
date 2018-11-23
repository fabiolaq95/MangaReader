package Servlets.org;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Objects;
//import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import utilidades.DBConnection;
import utilidades.PropertiesReader;
import utilidades.md5hasher;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PropertiesReader p = new PropertiesReader("config.properties"); //Lo mismo que en DBConnection
		String user = p.reading("pguser");
		System.out.println("Hola " + user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			JSONObject resJson = new JSONObject();
			//JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			ArrayList<Object> campos = new ArrayList<Object>();
			String pass2 = null;
			md5hasher hash = null;
			DBConnection db = new DBConnection();
			
			
			String user = request.getParameter("user").trim();
			String pass = request.getParameter("password").trim();
			String name = request.getParameter("name").trim();
			String date = request.getParameter("date").trim();
			String email = request.getParameter("email").trim();
			
			if(db.connect()) {
				System.out.println("Me conecte mami");
			}else {
				System.out.println("Lo siento soy una inutil");
			}
			try {
				hash = md5hasher.getInstance();
				pass2 = hash.hashString(pass);
				campos.add(pass2);
				campos.add(user);
				campos.add(name);
				campos.add(date);
				campos.add(email);
				String query = "register";
				if(db.insert(campos, query)) {
					System.out.println("Listo mami");
					resJson.put("status", 200).put("res", "session stored");
				}else {
					System.out.println("Lo siento mami");
					resJson.put("status", 404).put("res", "something went wrong");
				}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
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
	}

}
