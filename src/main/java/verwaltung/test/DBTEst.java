package verwaltung.test;

import java.util.HashSet;
import java.util.Set;
import verwaltung.db.Dao;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
//eine Klasse, um die DatenbankKonnektion zu testen
public class DBTEst {
	
	
	//testet das DatenbankConnection und die methode
	public static void main(String[] args) {
//		Dienstleister neu = new Dienstleister();
//		neu.setName("TestMensch");
//		FirmenAdresse ad = new FirmenAdresse();
//		neu.setAdresse(ad);
//		//Dienstleister hinzufügen
//		Dao.saveDienstleister(neu);
//		System.out.println("Dienstleister wurde gespeichert...");
//		Bewertung bw = new Bewertung();
//		bw.setBewerter("TestBewerter");
////		bw.setDienstleister(neu);
//		Set<Bewertung> set = new HashSet<Bewertung>();
//		set.add(bw);
//		neu.setBewertungen(set);
//		Dao.saveDienstleister(neu);
	
		Dienstleister d = Dao.getOneById(4);
		Set<Bewertung> set = new HashSet<Bewertung>();
		set = d.getBewertungen();
		for (Bewertung bewertung : set) {
			System.out.println(bewertung.getBewerter());
		}
		
	}

}
