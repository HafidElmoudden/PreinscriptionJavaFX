package application.entities;

public class SchoolInformations {
	private String etablissement;
	private String ville;
	private String email;
	private String phone;
	private String nombreFormations;
	//public String ecole_code;

	public SchoolInformations(String etablissement, String ville, String email, String phone, String nombreFormations) {
		this.etablissement = etablissement;
		this.ville = ville;
		this.email = email;
		this.phone = phone;
		this.nombreFormations = nombreFormations;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNombreFormations() {
		return nombreFormations;
	}

	public void setNombreFormations(String nombreFormations) {
		this.nombreFormations = nombreFormations;
	}
}
