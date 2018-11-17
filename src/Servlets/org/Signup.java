package Servlets.org;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
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
		PropertiesReader p = new PropertiesReader("config.properties");
		String user = p.getValues("pguser");
		System.out.println("Hola" + user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		md5hasher hash = null;
		try {
			JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			ArrayList<String> campos = new ArrayList<String>();
			String pass2 = null;
			DBConnection db = new DBConnection();
			if(db.connect()) {
				System.out.println("Me conecte mami");
			}else {
				System.out.println("Lo siento soy una inutil");
			}
			try {
				hash = md5hasher.getInstance();
				pass2 = hash.hashString(reqBody.getString("password"));
				campos.add(pass2);
				campos.add(reqBody.getString("username"));
				campos.add(reqBody.getString("name"));
				campos.add(reqBody.getString("date"));
				campos.add(reqBody.getString("email"));
				if(db.insert(campos))
					System.out.println("Listo mami");
				else
					System.out.println("Lo siento mami");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
