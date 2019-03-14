package ro.fii.onco.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ro.fii.onco.entities.Contact;

public class DBConnection {

	private static Connection connect = null;

	public static Connection getConnection() {
		if (connect != null) {
			return connect;
		} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/<nume_baza_date>?" + "user=<user_db>&password=<parola_db>");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connect;
		}

	}

	public static ArrayList<Contact> selectAll(int id) throws Exception {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		getConnection();
		ps = connect.prepareStatement("select * from contacts where owner=?");
		ps.setInt(1, id);
		resultSet = ps.executeQuery();
		ArrayList<Contact> lista = new ArrayList<Contact>();
		while (resultSet.next()) {
			Contact c = new Contact();
			c.setFname(resultSet.getString("fname"));
			c.setLname(resultSet.getString("lname"));
			c.setAge(resultSet.getString("age"));
			c.setCity(resultSet.getString("city"));
			c.setFaculty(resultSet.getString("faculty"));
			c.setGroup(resultSet.getString("group"));
			c.setId(resultSet.getInt("id"));
			c.setInterests(resultSet.getString("interests"));
			c.setPic_url(resultSet.getString("pic_url"));
			c.setPnumber(resultSet.getString("pnumber"));
			c.setWebadress(resultSet.getString("webadress"));
			
			lista.add(c);

		}
		return lista;
	}
	public static int login(String user,String pass) {
		int temp=0;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		getConnection();
		try {
			ps = connect.prepareStatement("select id from users where username=? and password=?");
			ps.setString(1, user);
			ps.setString(2, pass);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				temp=resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	public static void register(String user,String pass) {
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = connect.prepareStatement("insert into  users values(?,?)");
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void newContact(Contact c) {
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = connect.prepareStatement("insert into  contacts(owner,fname,lname,pnumber,city,age,faculty,webadress,interests,pic_url,group) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, c.getOwner());
			ps.setString(2, c.getFname());
			ps.setString(3, c.getLname());
			ps.setString(4, c.getPnumber());
			ps.setString(5, c.getCity());
			ps.setString(6, c.getAge());
			ps.setString(7, c.getFaculty());
			ps.setString(8, c.getWebadress());
			ps.setString(9, c.getInterests());
			ps.setString(10, c.getPic_url());
			ps.setString(11, c.getGroup());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void updateContact(int id,Contact c) {
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = connect.prepareStatement("update contacts set owner=?,fname=?,lname,pnumber=?,city=?,age=?,faculty=?,webadress=?,interests=?,pic_url=?,group=?) where id=?");
			ps.setInt(1, c.getOwner());
			ps.setString(2, c.getFname());
			ps.setString(3, c.getLname());
			ps.setString(4, c.getPnumber());
			ps.setString(5, c.getCity());
			ps.setString(6, c.getAge());
			ps.setString(7, c.getFaculty());
			ps.setString(8, c.getWebadress());
			ps.setString(9, c.getInterests());
			ps.setString(10, c.getPic_url());
			ps.setString(11, c.getGroup());
			ps.setInt(12, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void newGroup(String name) {
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = connect.prepareStatement("insert into  Group(name) values(?)");
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delContact(int id) {
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = connect.prepareStatement("delete from contacts where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delGroup(int id) {
		PreparedStatement ps = null;
		getConnection();
		try {
			ps = connect.prepareStatement("delete from Group where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
