package verwaltung.db;

import java.util.ArrayList;
import java.util.List;
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
		session.delete(dienstleister);
		tx.commit();
		session.close();
	}
	//einen Dienstleister nach Id holen
	public static Dienstleister getOneById(int id) {
		Dienstleister dienstleister = new Dienstleister();
		Session session = getSession();
//		Criteria crit = session.createCriteria(Dienstleister.class);
//		dienstleister = (Dienstleister) crit.add(Restrictions.eq("serviceId", id)).uniqueResult();
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
		return a.getBenutzerName();
	}
	public static String readPasswort(String passwort) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM User A WHERE A.passwort = :passwort";
		Query query = session.createQuery(hql);
		query.setParameter("passwort", passwort);
		User a = (User) query.getSingleResult();
		return a.getPasswort();
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
