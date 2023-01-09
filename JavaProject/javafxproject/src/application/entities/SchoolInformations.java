package application.entities;

import application.utilities.ImageUtils;
import javafx.scene.control.Button;

public class SchoolInformations {
	private String etablissement;
	private String ville;
	private String email;
	private String phone;
	private String nombreFormations;
	private String ecole_code;
	private String description;
	private String adress;
    private Button deleteUser = new Button();
    private Button editUser = new Button();

    public SchoolInformations() {
    	ImageUtils.setButtonImage(getClass(),deleteUser, "Delete.png");
    	ImageUtils.setButtonImage(getClass(),editUser, "edit.png");
    	deleteUser.setOnAction(e -> {
    		System.out.println("yo yoyoyo Remove clicked : " + this.etablissement + " " + this.ville +" Phone : " + this.phone + " email : " + this.email);
    	});
    	editUser.setOnAction(e -> {
    		System.out.println("yo yoyoyo Edit clicked : " + this.etablissement + " " + this.ville+" Phone : " + this.phone + " email : " + this.email);
    	});
    }
    
	public SchoolInformations(String etablissement, String ville, String email, String phone, String nombreFormations) {
		this.etablissement = etablissement;
		this.ville = ville;
		this.email = email;
		this.phone = phone;
		this.nombreFormations = nombreFormations;
    	ImageUtils.setButtonImage(getClass(),deleteUser, "Delete.png");
    	ImageUtils.setButtonImage(getClass(),editUser, "edit.png");
    	deleteUser.setOnAction(e -> {
    		System.out.println("yo yoyoyo Remove clicked : " + this.etablissement + " " + this.ville +" Phone : " + this.phone + " email : " + this.email);
    	});
    	editUser.setOnAction(e -> {
    		System.out.println("yo yoyoyo Edit clicked : " + this.etablissement + " " + this.ville+" Phone : " + this.phone + " email : " + this.email);
    	});
	}

	
	public Button getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(Button deleteUser) {
		this.deleteUser = deleteUser;
	}

	public Button getEditUser() {
		return editUser;
	}

	public void setEditUser(Button editUser) {
		this.editUser = editUser;
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
