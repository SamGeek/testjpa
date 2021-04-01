package jpa.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Collaborateur {
		
	private Long id ; 
	private List<TableauKanban> tableaukanban ; 
	private String nom ; 	
	private String prenom ;
	private String email ; 
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public Collaborateur() {
		super();
	}

	public Collaborateur(  String nom, String prenom, String email) {
		super();	
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	@ManyToMany (mappedBy ="collaborateurs")
	public List<TableauKanban> getTableaukanban() {
		return tableaukanban;
	}
	public void setTableaukanban(List<TableauKanban> tableaukanban) {
		this.tableaukanban = tableaukanban;
	}
	
	@Override
	public String toString() {
		return "collaborateur [id=" + id + ", nom=" + nom + ", prenom =" 
				+ prenom +" ,email = "+email+  "] \n";
	}

	

}
