package application.entities;

public class SchoolInformations {
	private String etablissement;
	private String ville;
	private String email;
	private String phone;
	private String nombreFormations;
	private String ecole_code;
	private String description;
	private String adress;

	public SchoolInformations(String etablissement, String ville, String email, String phone, String nombreFormations) {
		this.etablissement = etablissement;
		this.ville = ville;
		this.email = email;
		this.phone = phone;
		this.nombreFormations = nombreFormations;
	}

	public SchoolInformations() {
		// TODO Auto-generated constructor stub
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEcole_code() {
		return ecole_code;
	}

	public void setEcole_code(String ecole_code) {
		this.ecole_code = ecole_code;
	}
}
