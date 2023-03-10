package application.entities;

import java.util.Optional;

import application.GlobalControllers;
import application.repositories.SchoolRepository;
import application.services.SchoolService;
import application.utilities.ConfirmationDialog;
import application.utilities.ImageUtils;
import application.utilities.SchoolEditDialog;
import application.utilities.SchoolEditDialog.SchoolEditData;
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
	private String logo;
	private String banner;
	private String website;
	private String fax;
	
    private Button deleteUser = new Button();
    private Button editUser = new Button();

    public SchoolInformations() {
    	
    }
    
	public SchoolInformations(String etablissement, String ville, String email, String phone, String nombreFormations) {
		this.etablissement = etablissement;
		this.ville = ville;
		this.email = email;
		this.phone = phone;
		this.nombreFormations = nombreFormations;
    	ImageUtils.setButtonImage(getClass(),deleteUser, "Delete.png", 20, 20, false);
    	ImageUtils.setButtonImage(getClass(),editUser, "edit.png", 20, 20, false);
    	deleteUser.setOnAction(e -> {
    		ConfirmationDialog confirmationDialog = new ConfirmationDialog("Êtes-vous sûr de vouloir supprimer cet école ?");
    		Optional<Boolean> result = confirmationDialog.showAndWait();
    		if (result.get() && result.isPresent()) {
    			String ecole_code = SchoolService.getEcoleCodeByEmail(email);
    			SchoolRepository.deleteSchool(ecole_code, email);
    			if(GlobalControllers.adminController != null)
    				GlobalControllers.adminController.updateTableViews();
    		}
    	});
    	editUser.setOnAction(e -> {
    		SchoolEditDialog editDialog = new SchoolEditDialog(this.etablissement, this.ville, this.email, this.phone);
    		Optional<SchoolEditData> result = editDialog.showAndWait();
    		if (result.isPresent()) {
    			String ecole_code = SchoolService.getEcoleCodeByEmail(email);
    		    SchoolEditData editData = result.get();
    		    SchoolRepository.updateSchool(ecole_code, email, editData.getEtablissement(), editData.getVille(), editData.getEmail(),editData.getTelephone());
    			if(GlobalControllers.adminController != null)
    				GlobalControllers.adminController.updateTableViews();
    		}
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
