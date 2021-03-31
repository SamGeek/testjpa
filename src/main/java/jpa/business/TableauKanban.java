package jpa.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TableauKanban {
	
	
	private Long id ; 
	private List<Section> sections ;
	private List<Collaborateur> collaborateurs ; 
	
	
	
	public TableauKanban(List<Collaborateur> collaborateurs) {
		super();
		this.collaborateurs = collaborateurs;
	}
	//Libelle 
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany (mappedBy = "tableaukanban")
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	
	@ManyToMany	
	public List<Collaborateur> getCollaborateurs() {
		return collaborateurs;
	}
	public void setCollaborateurs(List<Collaborateur> collaborateurs) {
		this.collaborateurs = collaborateurs;
	}
	
}
