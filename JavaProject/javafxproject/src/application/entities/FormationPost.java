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
	//for student list grids in school screen 
	
	public String student_ville;
	public String student_nom;
	public String student_prenom;
	public String student_bac;
	public String student_cne;
    public String student_email;
	//to use in student application
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
    public FormationPost() {
		// TODO Auto-generated constructor stub
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
    public void setStudentNom(String studentNom) {
    	this.student_nom= studentNom;
    }
    public void setStudentPrenom(String studentPrenom) {
    	this.student_prenom= studentPrenom;
    }
    public void setStudentBac(String studentBac) {
    	this.student_bac= studentBac;
    }
    public void setStudentVille(String studentVille) {
    	this.student_ville= studentVille;
    }
    public void setstudentEmail(String studentEmail) {
    	this.student_email= studentEmail;
    }
    public void setStudentCne(String studentCne) {
    	this.student_cne= studentCne;
    }
    public String getStudent_prenom() {
		return student_prenom;
	}
    public String getStudent_nom() {
    	return student_nom;
    }
    public String getStudent_ville() {
    	return student_ville;
    }
    public String getStudent_email() {
    	return student_email;
    }
    public String getStudent_cne() {
    	return student_cne;
    }
	public FormationPost(String formation_nom, String ville, String email, String nom, String prenom,String cne, String a) {
		this.formation=formation_nom;
		this.student_email=email;
		this.student_prenom=prenom;
		this.student_nom=nom;
		this.student_ville=ville;
		this.student_cne=cne;
	}
	
}







