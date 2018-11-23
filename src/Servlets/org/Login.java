package Servlets.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

//import com.fasterxml.jackson.databind.ObjectMapper;

//import models.Response;
import utilidades.DBConnection;
import utilidades.md5hasher;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//ObjectMapper objMapper = new ObjectMapper();
		JSONObject json = new JSONObject(); 
		
		if(session.isNew()) {
			if(session.getAttribute("username") == null){
				System.out.println(session.getAttribute("usuario"));
				System.out.println("sesion no iniciada");
//				Response<?> resp = new Response<>();
//				resp.setMessage("session not started");
//				resp.setStatus(403);
				try {
					json.put("status", "403").put("res", "session not started").put("value", "");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.invalidate();
				}
			else if(session.getAttribute("username") != null) {
				System.out.println(session.getAttribute("usuario"));
				System.out.println("Sesion iniciada");
				System.out.println("usuario: "+session.getAttribute("username"));
				try {
//					Response<?> resp = new Response<>();
//					resp.setMessage("session not started");
//					resp.setStatus(200);
//					String res = objMapper.writeValueAsString(resp);
//					System.out.println(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resp));
//					response.getWriter().print(res);
					json.put("status", "200").put("type", session.getAttribute("user_type")).put("username", session.getAttribute("username"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			out.print(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		HttpSession session = request.getSession();
		JSONObject resJson = new JSONObject();
//		JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		ArrayList<Object> campos = new ArrayList<Object>();
		md5hasher hash = null;
		
		String user;
		String pass;
		PrintWriter out = response.getWriter();
		DBConnection db = new DBConnection();
		
		user = request.getParameter("user").trim();
		pass = request.getParameter("password").trim();
		
		if(db.connect()) {
			System.out.println("Me conecte papi");
			//System.out.println(user);
			//System.out.println(pass);
		}else {
			System.out.println("Lo siento soy una inutil (login)");
		}
		
		try {
			hash = md5hasher.getInstance();
			String pass2 = hash.hashString(pass);
			campos.add(pass2);
			campos.add(user);
			
			if(db.select(campos, "login")) {
				resJson.put("status", 200).put("res", "session stored");
			}
			else {
				resJson.put("status", 404).put("res", "invalid credentials");
				session.invalidate();
			}
		}catch(Exception e) {
			e.printStackTrace();
			session.invalidate();
		}
		
		out.println(resJson);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
