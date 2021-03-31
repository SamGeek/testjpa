package jpa.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity
@NamedQueries(
		{
			@NamedQuery(name = "Tag.touslestags", query = "Select t from Tag t"),
			@NamedQuery(name = "Tag.recherchetagparnom", query = "Select t from Tag t where t.libelle = :libelle")
		})
public class Tag {
	
	
	private Long id; 
	private String libelle ;
	private List<FicheKanban> fichekanbans ; 
	
	public Tag(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	
	@javax.persistence.Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	} 
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@ManyToMany (mappedBy ="tags")
	public List<FicheKanban> getFichekanbans() {
		return fichekanbans;
	}
	public void setFichekanbans(List<FicheKanban> fichekanbans) {
		this.fichekanbans = fichekanbans;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Je suis le tag d'id "+this.id+" et de libellé "+this.libelle+" \n ";
	}
	
	
	
	
	
	
}
