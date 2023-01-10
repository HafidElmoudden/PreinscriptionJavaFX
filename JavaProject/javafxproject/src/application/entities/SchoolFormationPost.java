package application.entities;

import java.util.Optional;

import application.GlobalControllers;
import application.services.FormationService;
import application.utilities.ImageUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class SchoolFormationPost{
	private String ecole_nom;
	private String max_chaises;
	private String nbr_chaises_reserver;
	private String nbr_chaises_available;
	private String formation_code;
	private String cp_code;
	private String formation_nom;
	private Button selection = new Button();
	
    public SchoolFormationPost(String ecole_code, String max_chaises, String nbr_chaises_reserver, String nbr_chaises_available, String formation_code, String cp_code, String formation_nom)
    {
    	ImageUtils.setButtonImage(getClass(), selection, "select.png", 20, 65, false);
    	selection.setOnAction(e -> {
    		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    		a.setContentText("êtes-vous sûr de vouloir affecter "+ nbr_chaises_available + " étudiants à cette formation");
    		Optional<ButtonType> answer = a.showAndWait();
    		if (answer.isPresent() && answer.get() == ButtonType.OK) {
    			FormationService.addCandidatesToAffectation(formation_code);
    			if(GlobalControllers.schoolController != null)
    				GlobalControllers.schoolController.updateTableViews();
    		}
    	});
        this.max_chaises = max_chaises;
        this.nbr_chaises_reserver = nbr_chaises_reserver;
        this.nbr_chaises_available = nbr_chaises_available;
        this.formation_code = formation_code;
        this.cp_code = cp_code;
        this.formation_nom = formation_nom;
    }

    
	public Button getSelection() {
		return selection;
	}


	public void setSelection(Button selection) {
		this.selection = selection;
	}


	public String getEcole_nom() {
		return ecole_nom;
	}

	public void setEcole_nom(String ecole_nom) {
		this.ecole_nom = ecole_nom;
	}


	public String getMax_chaises() {
		return max_chaises;
	}

	public void setMax_chaises(String max_chaises) {
		this.max_chaises = max_chaises;
	}

	public String getNbr_chaises_reserver() {
		return nbr_chaises_reserver;
	}

	public void setNbr_chaises_reserver(String nbr_chaises_reserver) {
		this.nbr_chaises_reserver = nbr_chaises_reserver;
	}

	public String getNbr_chaises_available() {
		return nbr_chaises_available;
	}

	public void setNbr_chaises_available(String nbr_chaises_available) {
		this.nbr_chaises_available = nbr_chaises_available;
	}

	public String getFormation_code() {
		return formation_code;
	}

	public void setFormation_code(String formation_code) {
		this.formation_code = formation_code;
	}

	public String getCp_code() {
		return cp_code;
	}

	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}

	public String getFormation_nom() {
		return formation_nom;
	}

	public void setFormation_nom(String formation_nom) {
		this.formation_nom = formation_nom;
	}    
    
}
