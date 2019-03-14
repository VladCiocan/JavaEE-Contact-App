package ro.fii.onco.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ro.fii.onco.connection.DBConnection;
import ro.fii.onco.entities.Contact;
import ro.fii.onco.helpers.Sorter;

/**
 * Servlet implementation class api
 */
public class api extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public api() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doSomething(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doSomething(request, response);
	}

	public void doSomething(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI().replace("/OnCo/", "");
		System.out.println("User accessed : " + path);
		Gson gson = new GsonBuilder().create();

		if (path.equals("login")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int id = DBConnection.login(username, password);
			response.getWriter().write(id + "");
			
		} else if (path.equals("register")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			DBConnection.register(username, password);
			response.getWriter().write("OK");
			
		} else if (path.equals("del_contact")) {
			
			DBConnection.delContact(Integer.parseInt(request.getParameter("id")));
			
		} else if (path.equals("new_group")) {
			
			DBConnection.newGroup(request.getParameter("name"));
			
		} else if (path.equals("new_contact")) {
			
			Contact temp = new Contact(Integer.parseInt(request.getParameter("owner")), request.getParameter("fname"),
					request.getParameter("lname"), request.getParameter("pnumber"), request.getParameter("city"),
					request.getParameter("age"), request.getParameter("faculty"), request.getParameter("webadress"),
					request.getParameter("interests"), request.getParameter("pic_url"), request.getParameter("group"));
			DBConnection.newContact(temp);
			
		} else if (path.equals("update_contact")) {
			Contact contactTemporar = new Contact(Integer.parseInt(request.getParameter("owner")), request.getParameter("fname"),
					request.getParameter("lname"), request.getParameter("pnumber"), request.getParameter("city"),
					request.getParameter("age"), request.getParameter("faculty"), request.getParameter("webadress"),
					request.getParameter("interests"), request.getParameter("pic_url"), request.getParameter("group"));
			
			DBConnection.updateContact(Integer.parseInt(request.getParameter("id")), contactTemporar);
			
		} else if (path.equals("del_group")) {
			
			DBConnection.delGroup(Integer.parseInt(request.getParameter("id")));
			
		} else if (path.equals("get_sort")) {
			
			ArrayList<Contact> list = new ArrayList<>();
			list = Sorter.sort(Integer.parseInt(request.getParameter("sort")),
					Integer.parseInt(request.getParameter("id")));
			
			gson.toJson(list, response.getWriter());
		} else if (path.equals("get_all")) {
			try {
				gson.toJson(DBConnection.selectAll(Integer.parseInt(request.getParameter("owner"))), response.getWriter());
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
}
