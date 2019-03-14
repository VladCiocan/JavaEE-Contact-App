package ro.fii.onco.entities;

import java.util.ArrayList;
import java.util.Comparator;

public class Contact implements Comparable<Contact> {
	private int id, owner;
	private String fname, lname, pnumber, city, age, faculty, webadress, interests, pic_url, group;

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getWebadress() {
		return webadress;
	}

	public void setWebadress(String webadress) {
		this.webadress = webadress;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Contact() {

	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", owner=" + owner + ", fname=" + fname + ", lname=" + lname + ", pnumber="
				+ pnumber + ", city=" + city + ", age=" + age + ", faculty=" + faculty + ", webadress=" + webadress
				+ ", interests=" + interests + ", pic_url=" + pic_url + ", group=" + group + "]";
	}

	public Contact(int owner, String fname, String lname, String pnumber, String city, String age, String faculty,
			String webadress, String interests, String pic_url, String group) {
		super();
		this.owner = owner;
		this.fname = fname;
		this.lname = lname;
		this.pnumber = pnumber;
		this.city = city;
		this.age = age;
		this.faculty = faculty;
		this.webadress = webadress;
		this.interests = interests;
		this.pic_url = pic_url;
		this.group = group;
	}

	@Override
	public int compareTo(Contact c) {
		return this.getAge().compareTo(c.getAge());
	}

	public static Comparator<Contact> AgeComparator = new Comparator<Contact>() {

		public int compare(Contact c1, Contact c2) {

			String a1 = c1.getAge().toUpperCase();
			String a2 = c2.getAge().toUpperCase();

//ascending order
			return a1.compareTo(a2);

//descending order
//return a2.compareTo(a1);
		}

	};
	public static Comparator<Contact> FNComparator = new Comparator<Contact>() {
		public int compare(Contact c1, Contact c2) {
			String a1 = c1.getFname().toUpperCase();
			String a2 = c2.getFname().toUpperCase();
			return a1.compareTo(a2);
		}

	};
	public static Comparator<Contact> LNComparator = new Comparator<Contact>() {
		public int compare(Contact c1, Contact c2) {
			String a1 = c1.getLname().toUpperCase();
			String a2 = c2.getLname().toUpperCase();
			return a1.compareTo(a2);
		}

	};
	public static Comparator<Contact> GroupComparator = new Comparator<Contact>() {
		public int compare(Contact c1, Contact c2) {
			String a1 = c1.getGroup().toUpperCase();
			String a2 = c2.getGroup().toUpperCase();
			return a1.compareTo(a2);
		}

	};
	public static ArrayList<Contact> filterBy(int min , int max, ArrayList<Contact> listaOriginala){
		ArrayList<Contact> listaFiltrata=new ArrayList<>();
		for(Contact c:listaOriginala) {
			if(Integer.parseInt(c.getAge())<max && Integer.parseInt(c.getAge())>min ) {
				listaFiltrata.add(c);
			}
		}
		return listaFiltrata;
		
	}
	public static ArrayList<Contact> filterBy2(int min , int max, ArrayList<Contact> listaOriginala){
		ArrayList<Contact> listaFiltrata=new ArrayList<>();
		for(Contact c:listaOriginala) {
			if(Integer.parseInt(c.getAge())<max && Integer.parseInt(c.getAge())>min ) {
				listaFiltrata.add(c);
			}
		}
		return listaFiltrata;
		
	}
	//Exemplu
	ArrayList<Contact> t2=filterBy(1,2,filterBy2(1,2,new ArrayList<Contact>()));

}
