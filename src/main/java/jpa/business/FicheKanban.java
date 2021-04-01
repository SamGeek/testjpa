package jpa.business;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class FicheKanban {

	
	private Long id; 
	private int temps_necessaire ; 	
	private String lieu ; 
	private List<Tag> tags ; 
	private String url ; 
	private String note_explicative ;
    private String libelle ; 	
	private Date date_butoire ; 
	private int position ; 
	private Section section ;
	
	
	
	public FicheKanban() {
		super();
	}
	public FicheKanban(int temps_necessaire, String lieu, List<Tag> tags, String url, String note_explicative,
			String libelle, Date date_butoire, int position, Section section) {
		super();
		this.temps_necessaire = temps_necessaire;
		this.lieu = lieu;
		this.tags = tags;
		this.url = url;
		this.note_explicative = note_explicative;
		this.libelle = libelle;
		this.date_butoire = date_butoire;
		this.position = position;
		this.section = section;
	}
	@Id
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
	public int getTemps_necessaire() {
		return temps_necessaire;
	}
	public void setTemps_necessaire(int temps_necessaire) {
		this.temps_necessaire = temps_necessaire;
	}
	public String getNote_explicative() {
		return note_explicative;
	}
	public void setNote_explicative(String note_explicative) {
		this.note_explicative = note_explicative;
	}
	
	public Date getDate_butoire() {
		return date_butoire;
	}
	public void setDate_butoire(Date date_butoire) {
		this.date_butoire = date_butoire;
	}

	public int getTemps_resolu() {
		return temps_necessaire;
	}
	
	public void setTemps_resolu(int temps_resolu) {
		this.temps_necessaire = temps_resolu;
	}
	
	public String getLieu() {
		return lieu;
	}
	
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url; 
	}
	public String getNote() {
		return note_explicative;
	}
	public void setNote(String note) {
		this.note_explicative = note;
	}
		
	
	@ManyToMany 
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@ManyToOne
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	} 
	
	
	
}
