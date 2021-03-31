package jpa;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpa.business.Collaborateur;
import jpa.business.CollaborateurExterne;
import jpa.business.CollaborateurInterne;
import jpa.business.EntityManagerHelper;
import jpa.business.FicheKanban;
import jpa.business.Section;
import jpa.business.TableauKanban;
import jpa.business.Tag;

public class JpaTest {

	/**
	 * @param args
	 */
	
	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	

	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
	
		JpaTest test = new JpaTest(manager);
		
		
		try {
			test.populateDatabase();
			test.testingInheritance();
			test.testRequeteNommee();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listCollaborateur();
			
   	 manager.close();
		System.out.println(".. done");
	}
	
	private void populateDatabase()
	{
		
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Population de la BDD");
		
		// creation de collaborateurs
		Collaborateur c1 =  new Collaborateur("Colla1","yte","Zack@email.com");
		Collaborateur c2 =  new Collaborateur("Math","Rom","Rom@email.com");
		Collaborateur c3 =  new Collaborateur("Euth","Seb","Seb@email.com");
		manager.persist(c1);
		manager.persist(c2);
	    manager.persist(c3);
		
		
		//Creation de Tags
		Tag t1 =  new Tag("Fiche n°1");
		Tag t2 =  new Tag("Fiche n°2");
		Tag t3 =  new Tag("Fiche n°3");
		Tag t4 =  new Tag("Fiche n°4");
		Tag t5 =  new Tag("Fiche n°5");
		manager.persist(t1);
		manager.persist(t2);
		manager.persist(t3);
		manager.persist(t4);
		manager.persist(t5);
		
		//liste des tags
		List<Tag> tags1 = Arrays.asList(t1, t2,t3,t4) ;
		List<Tag> tags2 = Arrays.asList(t2,t3,t4, t5) ;
		
		
		//Creation de Tableaux Kanban
		TableauKanban tableau = new TableauKanban( Arrays.asList(c1,c2,c3));
		manager.persist(tableau);
		

		//Creation de Sections
		Section section = new Section( "libelle", tableau);
		manager.persist(section);
		
		//creation de Fiches Kanban
		FicheKanban fiche1 = new FicheKanban(10,"Rennes",tags1,"url","note_explicative","libelle", new Date(0), 19, section);//section non renseignée
		FicheKanban fiche2 = new FicheKanban(15,"Nantes",tags2,"url2","note_explicative2","libelle2", new Date(0), 20, section);//section non renseignée
		
		manager.persist(fiche1);
		manager.persist(fiche2);
		
		
		System.out.println("\n\nFin population BDD\n\n");
		
	}
	
	
	
	private void testRequeteNommee()
	{
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Test requete nommée");
		
		System.out.println("Recupération de tous les tags");
		List<Tag> tagsWithNamedQuery = manager.createNamedQuery("Tag.touslestags")
			    .getResultList();
		
		//Boucle d'affichage des resultats
		for(Tag t : tagsWithNamedQuery  ) {
			t.toString();
		}
		
		
		System.out.println("Recupération de tag par libelle de tag");
		Tag tag = (Tag) manager.createNamedQuery("Tag.recherchetagparnom")
			    .setParameter("libelle", "Fiche n°1")
			    .getSingleResult();
		
		//affichage du tag n°1 recupérré par le NamedQuery
		System.out.println(tag.toString());
		
		System.out.println("\n\nFin tests requete nommee\n\n");
	}
	
	
	private void listCollaborateur() {
		
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Test liste des collaborateurs");
		
		List<Collaborateur> resultList = manager.createQuery("Select c From Collaborateur c", Collaborateur.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Collaborateur next : resultList) {
			System.out.println("next Collaborateur: " + next);
		}
		
		
		System.out.println("\n\nFin tests collaborateurs\n\n");
	}
	
	private void testingInheritance (){
		
		System.out.println("\n\n\nBegin testing Inheritance\n\n\n");
		
		//On crée 2 collaborateurs internes
		CollaborateurInterne henri = new CollaborateurInterne("LeBlanc", "Henri", "leblan.henri@lafayette.fr","logistique","renovation");
		CollaborateurInterne mathieu = new CollaborateurInterne("Archer", "Mathieur", "archer.mathieu@lafayette.fr","comptabilite","renovation");
		
		manager.persist(henri);
		manager.persist(mathieu);
		
		
		//on crée 2 collaborateurs  externes
		CollaborateurExterne anne = new CollaborateurExterne("Duchemin", "Anne","anne.duchemin@capgemini.com", "Capgemini","Ingénieur");
		CollaborateurExterne rose = new CollaborateurExterne("Latouche", "Rose","rose.latouche@capgemini.com" ,"Capgemini","RH");
		manager.persist(anne);
		manager.persist(rose);
		
		
		//on fait une requete interessantes pour recuperer les collabrateurs externes uniquement
		
		
		//on fait une requete polymorphe pour recupperer tous les collaborateurs
		
		
		
		//pour  finir on recherche les collaborateurs externes, d'un grade donné
		//utilisation du criteria querry comme demadé  dans la requete
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CollaborateurExterne> query = builder.createQuery(CollaborateurExterne.class);
		Root<CollaborateurExterne> from = query.from(CollaborateurExterne.class);
		
		
		query.where(builder.and(
		    builder.equal(from.get("grade"), "Ingénieur")));

		List<CollaborateurExterne> list  = manager.createQuery(query).getResultList();
		
		System.out.println("Les collaborateurs externes de grade ingénieur sont : ");
		for(CollaborateurExterne col : list) {
			col.toString();
		}
		
		
		//recuperer tous les collaborateurs internes dun grade donné
		CriteriaQuery<CollaborateurInterne> queryInterne = builder.createQuery(CollaborateurInterne.class);
		Root<CollaborateurInterne> fromInterne = queryInterne.from(CollaborateurInterne.class);
		
		
		queryInterne.where(builder.and(
		    builder.equal(fromInterne.get("departement"), "logistique")));

		List<CollaborateurInterne> listInterne  = manager.createQuery(queryInterne).getResultList();
		
		System.out.println("Les collaborateurs interne du departement logistique sont : ");
		for(CollaborateurInterne col : listInterne) {
			col.toString();
		}

		
		System.out.println("\n\n\nEnd testing Inheritance\n\n\n");
	}
	

	
}
