package jpa.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Section {
	
	
	private long id ;
	private List<FicheKanban> fichekanbans ; 
	private TableauKanban tableaukanban ; 
	private String libelle ;
	
	
	
	public Section(String libelle, TableauKanban tableauKanban) {
		super();
		this.tableaukanban = tableauKanban;
		this.libelle = libelle;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	@OneToMany (mappedBy = "section")
	public List<FicheKanban> getFichekanbans() {
		return fichekanbans;
	}
	public void setFichekanbans(List<FicheKanban> fichekanbans) {
		this.fichekanbans = fichekanbans;
	}
	
	
	@ManyToOne
	public TableauKanban getTableaukanban() {
		return tableaukanban;
	}
	public void setTableaukanban(TableauKanban tableaukanban) {
		this.tableaukanban = tableaukanban;
	}
	
	


}
