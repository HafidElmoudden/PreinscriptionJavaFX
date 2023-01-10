package application.entities;

import java.util.Optional;

import application.GlobalControllers;
import application.Navigation;
import application.repositories.FormationRepository;
import application.repositories.StudentRepository;
import application.services.FormationService;
import application.services.StudentService;
import application.utilities.ImageUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class FormationPost
{
    public String etablissement;
    public String ecole_code;
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
    //Notifications table actions
    private Button acceptNotif = new Button();
    private Button declineNotif = new Button();
    private String date_;
    private String repondreAvant;
    
    //My Applications table actions
    private Button deleteApp = new Button();
    
    //Home table actions
    private Button viewSchool = new Button();
    private Button applySchool = new Button();
    
    //School Profile Formation List Check Image
    private Button checkImage = new Button();
    
    public String getNbr_chaises_available() {
		return nbr_chaises_available;
	}
	public void setNbr_chaises_available(String nbr_chaises_available) {
		this.nbr_chaises_available = nbr_chaises_available;
	}

    public String getRepondreAvant() {
		return repondreAvant;
	}
	public void setRepondreAvant(String repondreAvant) {
		this.repondreAvant = repondreAvant;
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
    	ImageUtils.setButtonImage(getClass(),acceptNotif, "accept.png", 20, 20, false);
    	ImageUtils.setButtonImage(getClass(),declineNotif, "decline.png", 20, 20, false);
    	
    	ImageUtils.setButtonImage(getClass(),checkImage, "check_small.png", 20, 20, false);

    	ImageUtils.setButtonImage(getClass(),deleteApp, "Delete.png", 20, 20, false);
    	
    	deleteApp.setOnAction(e -> {
    		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    		a.setContentText("Êtes-vous sûr de vouloir supprimer l'application dans la formation : " + formation);
    		Optional<ButtonType> answer = a.showAndWait();
    		if (answer.isPresent() && answer.get() == ButtonType.OK) {
    			StudentRepository.deleteMyApplication(candida_code);
    			if(GlobalControllers.studentController != null)
    				GlobalControllers.studentController.updateTableViews();
    		}
    	});
    	
    	ImageUtils.setButtonImage(getClass(),applySchool, "Postuler.png", 21, 60, false);
    	ImageUtils.setButtonImage(getClass(),viewSchool, "Voir.png", 21, 60, false);
    	viewSchool.setOnContextMenuRequested(e->{
    		System.out.println(this.ecole_code);
    	});
    	applySchool.setOnAction(e -> {
    		
    		StudentInformations student = StudentService.getStudentInformations(Navigation.email);
    		String cp_code = this.candida_code;
    		System.out.println("cp_cpde"+cp_code);
    		System.out.println(student.getCne());
            String bac = student.getBacInformations().baccalaureat;

            
            
            //Cofes Notes
            FormationService cofs = FormationService.getCofs(cp_code, bac);
            System.out.println(cofs.cof_math + cofs.cof_physic + cofs.cof_svt + cofs.cof_fran);
            //Studet Notes
            float nm= Float.parseFloat(student.getBacInformations().note_math),
            nph= Float.parseFloat(student.getBacInformations().note_physic),
            ns= Float.parseFloat(student.getBacInformations().note_svt),
            nf= Float.parseFloat(student.getBacInformations().note_francais);
            System.out.println(nph+" "+ns+" "+nm+" "+nf);
            //Calculate Note
            float candidateur_note = ((Float.parseFloat(cofs.cof_math) * nm) + (Float.parseFloat(cofs.cof_physic) * nph) + (Float.parseFloat(cofs.cof_svt) * ns) + (Float.parseFloat(cofs.cof_fran) * nf)) / ((Float.parseFloat(cofs.cof_fran)) + (Float.parseFloat(cofs.cof_svt)) + (Float.parseFloat(cofs.cof_physic)) + (Float.parseFloat(cofs.cof_math)));
            System.out.println(candidateur_note);
            FormationRepository.insertIntoApplications(Integer.parseInt(cp_code), student.getCne(),candidateur_note);
			if(GlobalControllers.studentController != null)
				GlobalControllers.studentController.updateTableViews();
    	});
    	
    	declineNotif.setOnAction(e->{
    		StudentInformations student = StudentService.getStudentInformations(Navigation.email);
    		String cp_code = this.candida_code;
    		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    		a.setContentText("Êtes-vous sûr de rejeter cette admission ? " + formation);
    		Optional<ButtonType> answer = a.showAndWait();
    		if (answer.isPresent() && answer.get() == ButtonType.OK) {
    			FormationRepository.setDecline((String) student.getCne(),cp_code);
    			if(GlobalControllers.studentController != null)
    				GlobalControllers.studentController.updateTableViews();
    		}
    	});
    	
    	acceptNotif.setOnAction(e->{
    		StudentInformations student = StudentService.getStudentInformations(Navigation.email);
    		String cp_code = this.candida_code;
    		
    		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    		a.setContentText("Si vous acceptez cette admission, toutes les admissions seront annulées et vous ne pourrez pas postuler pour d'autres formation : " + formation);
    		Optional<ButtonType> answer = a.showAndWait();
    		if (answer.isPresent() && answer.get() == ButtonType.OK) {
    			FormationRepository.setAccept((String) student.getCne(),cp_code);
    			FormationRepository.updateFormationOccupeNumber(cp_code, String.valueOf(1));
    			FormationRepository.updateChaisesAvailableNumber(cp_code, String.valueOf(1));
    			if(GlobalControllers.studentController != null)
    				GlobalControllers.studentController.updateTableViews();
    		}
    	});
    	
    	viewSchool.setOnAction(e -> {
    		if(GlobalControllers.studentController != null) {
    			GlobalControllers.studentController.fillSchoolViewer(ecole_code);
    			GlobalControllers.studentController.view_school.toFront();    			
    		}
    	});
    	
	}
    
    
    
	public Button getCheckImage() {
		return checkImage;
	}
	public void setCheckImage(Button checkImage) {
		this.checkImage = checkImage;
	}
	public Button getAcceptNotif() {
		return acceptNotif;
	}
	public void setAcceptNotif(Button acceptNotif) {
		this.acceptNotif = acceptNotif;
	}
	public Button getDeclineNotif() {
		return declineNotif;
	}
	public void setDeclineNotif(Button declineNotif) {
		this.declineNotif = declineNotif;
	}
	public Button getDeleteApp() {
		return deleteApp;
	}
	public void setDeleteApp(Button deleteApp) {
		this.deleteApp = deleteApp;
	}
	public Button getViewSchool() {
		return viewSchool;
	}
	public void setViewSchool(Button viewSchool) {
		this.viewSchool = viewSchool;
	}
	public Button getApplySchool() {
		return applySchool;
	}
	public void setApplySchool(Button applySchool) {
		this.applySchool = applySchool;
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
	public String getEcole_code() {
		return ecole_code;
	}
	public void setEcole_code(String ecole_code) {
		this.ecole_code = ecole_code;
	}
	public String getDate_() {
		return date_;
	}
	public void setDate_(String date_) {
		this.date_ = date_;
	}
	
}







