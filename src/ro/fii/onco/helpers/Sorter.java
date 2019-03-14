package ro.fii.onco.helpers;

import java.util.ArrayList;
import java.util.Collections;

import ro.fii.onco.connection.DBConnection;
import ro.fii.onco.entities.Contact;


public class Sorter {
	public static ArrayList<Contact> sort(int tipSortare, int owner) {
		ArrayList<Contact> oldList = null;
		try {
			oldList = DBConnection.selectAll(owner);

			if (tipSortare == 1) {
				Collections.sort(oldList, Contact.AgeComparator);
				return oldList;
			} else if (tipSortare == 2) {
				Collections.sort(oldList, Contact.FNComparator);
				return oldList;
			} else if (tipSortare == 3) {
				Collections.sort(oldList, Contact.LNComparator);
				return oldList;
			} else if (tipSortare == 4) {
				Collections.sort(oldList, Contact.GroupComparator);
				return oldList;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldList;
	}
}
