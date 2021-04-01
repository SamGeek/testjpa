package jpa.business;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("interne")
public class CollaborateurInterne extends Collaborateur {

	private String departement ; 
	private String projet ; 
	
	public CollaborateurInterne(String nom, String prenom, String email , String departement, String projet) {
		super(nom, prenom, email);
		this.departement = departement;
		this.projet = projet;
	}


	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getProjet() {
		return projet;
	}

	public void setProjet(String projet) {
		this.projet = projet;
	}
	
	@Override
	public String toString() {
		return "collaborateur interne [id=" + super.getId() + ", nom=" + super.getNom() + ", prenom =" 
				+ super.getPrenom() +" ,email = "+super.getEmail()+" departement : "+ departement + " projet :  "+projet+  "] \n";
	}
	

}
