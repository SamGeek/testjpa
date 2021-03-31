package jpa.business;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("externe")
public class CollaborateurExterne extends Collaborateur {

	private String entreprise ; 
	private String grade ; 
	
	public CollaborateurExterne(String nom, String prenom, String email, String entreprise, String grade) {
		super(nom, prenom, email);
		this.entreprise = entreprise;
		this.grade = grade;
	}


	public String getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "collaborateur externe [id=" + super.getId() + ", nom=" + super.getNom() + ", prenom =" 
				+ super.getPrenom() +" ,email = "+super.getEmail()+" entreprise : "+ entreprise + " grade :  "+grade+  "] \n";
	}

}
