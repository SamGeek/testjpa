package jpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.business.Collaborateur;
import jpa.business.CollaborateurInterne;
import jpa.business.EntityManagerHelper;

@WebServlet(name = "mytest", urlPatterns = { "/collaborateur" })
public class CollaborateurServlet extends HttpServlet {

	EntityManager manager;
	EntityTransaction tx;

	public void init(ServletConfig config) throws ServletException {

		manager = EntityManagerHelper.getEntityManager();
		tx = manager.getTransaction();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = new PrintWriter(resp.getOutputStream());
		tx.begin();

		try {
			//on recupere les collaborateurs
			out.println("<HTML>\n<BODY>\n" );
			
			
			out.println("<FORM Method=\"POST\" Action=\"/collaborateur\">\n" + 
					"    Nom : 		<INPUT type=\"text\" size=\"50\" name=\"nom\"><BR><BR>\n" + 
					"    prenom : 	<INPUT type=\"text\" size=\"50\" name=\"prenom\"><BR><BR>\n" + 
					"    Email : 		<INPUT type=\"text\" size=\"50\" name=\"email\"><BR><BR>\n" + 
					"    Departement : 		<INPUT type=\"text\" size=\"50\" name=\"departement\"><BR><BR>\n" + 
					"    Projet : 		<INPUT type=\"text\" size=\"50\" name=\"projet\"><BR><BR>\n" + 
					"            <INPUT type=submit value=\"Enregistrer un collaborateur\">\n</FORM>");
			
			
			out.println("<H1>Recapitulatif des informations</H1>\n <UL>\n");
			
			out.println( "<BR>--------------------------------------------" );
			
			
			List<Collaborateur> resultList = manager.createQuery("Select c From Collaborateur c", Collaborateur.class).getResultList();
			for (Collaborateur collab : resultList) {
				
				
				
				out.println( "<BR><LI>Nom: "
						+ collab.getNom() + "\n" + " <LI>Prenom: " + collab.getPrenom()+ "<BR>"
						+ " <LI>Email: " + collab.getEmail() + "<BR>" );
				
				out.println( "--------------------------------------------" );
				
			}
			
			out.println("</UL>\n</BODY></HTML>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		out.flush();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String departement = request.getParameter("departement");
		String projet = request.getParameter("projet");

		// On cree un collaborateur et on linsere dans la base
		tx.begin();

		CollaborateurInterne collab = new CollaborateurInterne(nom, prenom, email, departement, projet);
		manager.persist(collab);

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		response.sendRedirect("/collaborateur");

	}
}
