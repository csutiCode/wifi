package verwaltung.db;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import javafx.util.Callback;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
import verwaltung.repository.FirmenAdresse;
import verwaltung.repository.User;
//diese Klasse ist zuständig, um die SQL-Queries durchzuführen
public class Dao {

	//speichert einen Dienstleister in der Datenbank
	public static void saveDienstleister(Dienstleister neu) {
		Session session = getSession();		 
		Transaction tx = session.beginTransaction();
		session.save(neu);
		tx.commit();
		session.close();	     		
	}
	//holt alle die Daten aus der Datenbank
	public static List<Dienstleister> getAll() {
		Session session = getSession();		
		Query query = session.createQuery("FROM Dienstleister");
		List<Dienstleister> result = query.list();
		session.close();
		return result;
	}
	//gibt eine Liste von Dienstleister, die zu einem Id gehören
	public static List<Dienstleister> getAllByUserId(int userId) {
		List<Dienstleister> result = getAll().stream()
				.filter(d -> d.getUser().getUserId()==userId)
				.collect(Collectors.toList());
		return result;
	}
	
	
	// ändert ein Objekt, das bereits in der Datenbank ist
	public static void editDienstleister(Dienstleister dienstleister) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.merge(dienstleister);
		tx.commit();
		session.close();
	}
	
	//löscht ein Objekt aus der Datenbank
	public static void deleteDienstleister(Dienstleister dienstleister) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();		
		deleteBewertung(dienstleister);
		String hql = "delete from Dienstleister b where b.serviceId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", dienstleister.getServiceId()).executeUpdate();
		tx.commit();
		session.close();
	}
	//einen Dienstleister nach Id holen
	public static Dienstleister getOneById(int id) {
		Dienstleister dienstleister = new Dienstleister();
		Session session = getSession();
		dienstleister = session.get(Dienstleister.class, id);
		session.close();
		return dienstleister;	
	}
	//speichert die Bewertung in der Datenbank
	public static void saveBewertung(Dienstleister dienstleister) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.update(dienstleister);
		tx.commit();
		session.close();
	}
	//um BenutzerName und Passwort zu kontrollieren
	public static String readBenutzerName(String benutzerName) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM User A WHERE A.benutzerName = :benutzerName";
		Query query = session.createQuery(hql);
		query.setParameter("benutzerName", benutzerName);
		User a = (User) query.getSingleResult();
		session.close();
		return a.getBenutzerName();
	}
	//lest das Passwort bei Anmeldung
	public static String readPasswort(String passwort) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM User A WHERE A.passwort = :passwort";
		Query query = session.createQuery(hql);
		query.setParameter("passwort", passwort);
		User a = (User) query.getSingleResult();
		session.close();
		return a.getPasswort();
	}
	//lest das Id von einem User
	public static int getUserId(String benutzerName) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM User A WHERE A.benutzerName = :benutzerName";
		Query query = session.createQuery(hql);
		query.setParameter("benutzerName", benutzerName);
		User a = (User) query.getSingleResult();
		session.close();
		return a.getUserId();
	}
	//holt das User aus der Datenbank
	public static User getUserById(int id) {
		User user = new User();
		Session session = getSession();
		user = session.get(User.class, id);
		session.close();
		return user;
	}
		
	//löscht eine Bewertung aus der Datenbank
	public static void deleteBewertung(Dienstleister dienstleister) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Bewertung b where b.serviceId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", dienstleister.getServiceId()).executeUpdate();
		session.close();
		
	}
	
	
	//die Datenbankverbindung auf zu bauen
	public static Session getSession() {
		Configuration con = new Configuration().configure().addAnnotatedClass(Dienstleister.class)
				.addAnnotatedClass(Bewertung.class)
				.addAnnotatedClass(FirmenAdresse.class)
				.addAnnotatedClass(User.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);
		return sf.openSession();
	}
	
	
	
	
		
	
}
