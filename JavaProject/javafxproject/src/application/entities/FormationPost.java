package application.entities;



public class FormationPost
{
    public String etablissement;
    public String formation;
    public String ville;
    public String residuelle;
    public String formation_code;
	public String candida_code;
	public String nbr_chaises_available;
	//to use in student application gridview
    public String classement_note;
    
    
    public String getNbr_chaises_available() {
		return nbr_chaises_available;
	}
	public void setNbr_chaises_available(String nbr_chaises_available) {
		this.nbr_chaises_available = nbr_chaises_available;
	}

    public FormationPost(String formation_code, String etablissement, String formation, String ville, String residuelle)
    {
        this.formation_code = formation_code;
        this.etablissement = etablissement;
        this.formation = formation;
        this.ville = ville;
        this.residuelle = residuelle;
    }
    public FormationPost(String etablissement, String formation, String classement_note, String ville, String residuelle, String candida_code)
    {
        this.etablissement = etablissement;
        this.formation = formation;
        this.ville = ville;
        this.residuelle = residuelle;
        this.classement_note = classement_note;
        this.candida_code = candida_code;
    }
    public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getResiduelle() {
		return residuelle;
	}
	public void setResiduelle(String residuelle) {
		this.residuelle = residuelle;
	}
	public String getFormation_code() {
		return formation_code;
	}
	public void setFormation_code(String formation_code) {
		this.formation_code = formation_code;
	}
	public String getClassement_note() {
		return classement_note;
	}
	public void setClassement_note(String classement_note) {
		this.classement_note = classement_note;
	}
	public String getCandida_code() {
		return candida_code;
	}
	public void setCandida_code(String candida_code) {
		this.candida_code = candida_code;
	}
    public FormationPost() {
    	
    }

}
