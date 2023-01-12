package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import application.GlobalControllers;
import application.Navigation;
import application.entities.FormationPost;
import application.entities.SchoolFormationPost;
import application.entities.SchoolInformations;
import application.entities.StudentInformations;
import application.services.CommonService;
import application.services.FormationService;
import application.services.SchoolService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SchoolScreenController implements Initializable{

	Navigation navigation = new Navigation();
	@FXML
	private Label identity;
    @FXML
    private TableColumn<SchoolFormationPost, String> action_grid_selection, disponible_grid_selection, etablissement_grid_selection, formation_grid_selection, occupee_grid_selection,residuelle_grid_selection ,nextselect_grid_selection;
    @FXML
    private TableColumn<FormationPost, String> cne_grid_etudiants, ville_grid_etudiants, email_grid_etudiants, formation_grid_etudiants, prenom_grid_etudiants, nom_grid_etudiants, statu_grid_etudiants;
    @FXML
    private TableColumn<FormationPost, String> vill_grid_candida, email_grid_candida, prenom_grid_candida, nom_grid_candida, cne_grid_candida,  formation_grid_candida;
    @FXML
    private ChoiceBox<String> school_candi_ville_filter, school_formaion_filter, school_etu_formation_filter, school_etu_statu_filter;
    @FXML
    private TableView<FormationPost> shcool_grid_candidats;
    @FXML
    private TableView<StudentInformations> shool_grid_etudiants;
    @FXML
    private TableView<SchoolFormationPost> shool_grid_section;
    @FXML
    private Button logoutbtn, school_candidateurbtn, school_selectionbtn, school_studentbtn;
    @FXML
    private ImageView school_logo;
    @FXML
    private Pane school_candidateurs, school_selection, school_etudiants;

    
    @FXML
    void handleButtonAction(ActionEvent event) {
    	
    	if(event.getSource()==school_candidateurbtn) {
    		school_candidateurs.toFront();
    		toggleStyleClass(school_candidateurbtn);
    	} else if(event.getSource()==school_selectionbtn) {
    		school_selection.toFront();
    		toggleStyleClass(school_selectionbtn);
    	} else if(event.getSource()==school_studentbtn) {
    		school_etudiants.toFront();
    		toggleStyleClass(school_studentbtn);
    	} else if(event.getSource()==logoutbtn) {
    		Navigation navigation = new Navigation();
    		Stage stage = (Stage) logoutbtn.getScene().getWindow();
    		navigation.backToLogin(stage);
    	}
    }

    public void updateTableViews() {
    	fillTheSelectionGrids();
		fillTheEtudiantsGrid(school_etu_statu_filter.getValue(), school_etu_formation_filter.getValue());
		fillTheCandidatsGrids(school_candi_ville_filter.getValue(),school_formaion_filter.getValue());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		toggleStyleClass(school_candidateurbtn);
		SchoolInformations school= SchoolService.getSchoolLogoByEmail(Navigation.email);
		Image logo = new Image(getClass().getResource(school.getLogo()).toExternalForm());
		school_logo.setImage(logo);
		GlobalControllers.schoolController = this;
		action_grid_selection.setCellValueFactory(new PropertyValueFactory<>("selection"));
		
		identity.setText(Navigation.email);
		CommonService.fillVilles(school_candi_ville_filter, true);
		FormationService.fillFormationsChoiceBox(school_formaion_filter, Navigation.email);
		FormationService.fillFormationsChoiceBox(school_etu_formation_filter, Navigation.email);
		school_formaion_filter.getItems().add("Toutes les formations");
		school_candi_ville_filter.setValue("Toutes les villes");
		school_formaion_filter.setValue("Toutes les formations");
		
		school_etu_statu_filter.getItems().add("Toutes les réponses");
		school_etu_statu_filter.setValue("Toutes les réponses");
		school_etu_statu_filter.getItems().add("Accepté");
		school_etu_statu_filter.getItems().add("En attendant");
		school_etu_statu_filter.getItems().add("Refusé");
		
		school_formaion_filter.setOnAction(e -> {
			fillTheCandidatsGrids(school_candi_ville_filter.getValue(),school_formaion_filter.getValue());
		});
		
		school_candi_ville_filter.setOnAction(e ->{
			fillTheCandidatsGrids(school_candi_ville_filter.getValue(),school_formaion_filter.getValue());
		});
		
		school_etu_statu_filter.setOnAction(e -> {
			fillTheEtudiantsGrid(school_etu_statu_filter.getValue(), school_etu_formation_filter.getValue());
		});
		
		school_etu_formation_filter.setOnAction(e -> {
			fillTheEtudiantsGrid(school_etu_statu_filter.getValue(), school_etu_formation_filter.getValue());
		});
		
		fillTheCandidatsGrids("Toutes les villes", "Toutes les formations");
		fillTheEtudiantsGrid("Toutes les réponses", "Toutes les formations");
		fillTheSelectionGrids();
	}
    
	public void fillTheCandidatsGrids(String ville, String formation) {
		cne_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_cne"));
		formation_grid_candida.setCellValueFactory(new PropertyValueFactory<>("formation"));
		vill_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_ville"));
		nom_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_nom"));
		prenom_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_prenom"));
		email_grid_candida.setCellValueFactory(new PropertyValueFactory<>("student_email"));
		SchoolService.fillCandidatslist(shcool_grid_candidats,Navigation.email, ville, formation);
	}
	
	public void fillTheSelectionGrids() {
		disponible_grid_selection.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_available"));
		etablissement_grid_selection.setCellValueFactory(new PropertyValueFactory<>("ecole_nom"));
		formation_grid_selection.setCellValueFactory(new PropertyValueFactory<>("formation_nom"));
		occupee_grid_selection.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_reserver"));
		residuelle_grid_selection.setCellValueFactory(new PropertyValueFactory<>("max_chaises"));
		nextselect_grid_selection.setCellValueFactory(new PropertyValueFactory<>("re_avant"));
		SchoolService.fillSelectionlist(shool_grid_section, Navigation.email);
	}
	
	public void fillTheEtudiantsGrid(String reponse, String formation) {
		cne_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("cne"));
		ville_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("city"));
		email_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("email"));
		formation_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("formationNom"));
		prenom_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		nom_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		statu_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("statuicon"));
		//statu_grid_etudiants.setCellValueFactory(new PropertyValueFactory<>("reponse"));
		SchoolService.fillSelectedStudentslist(shool_grid_etudiants, Navigation.email,formation, reponse);
	}
	public void toggleStyleClass(@SuppressWarnings("exports") Button event) {
		
		
		Image image1 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		Image image2 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		Image image3 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		ImageView imageView1 = new ImageView(image1);
		ImageView imageView2 = new ImageView(image2);
		ImageView imageView3 = new ImageView(image3);
		if (event == school_candidateurbtn) {
			school_candidateurbtn.getStyleClass().add("active");
			school_selectionbtn.getStyleClass().removeAll("active");
			school_studentbtn.getStyleClass().removeAll("active");
			image1 = new Image(getClass().getResourceAsStream("imgs/school_b.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/selec_w.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/student_w.png"));
		} else if (event == school_selectionbtn) {
			school_candidateurbtn.getStyleClass().removeAll("active");
			school_selectionbtn.getStyleClass().add("active");
			school_studentbtn.getStyleClass().removeAll("active");
			image1 = new Image(getClass().getResourceAsStream("imgs/school_w.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/selec_b.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/student_w.png"));
		} else if(event == school_studentbtn) {
			school_candidateurbtn.getStyleClass().removeAll("active");
			school_selectionbtn.getStyleClass().removeAll("active");
			school_studentbtn.getStyleClass().add("active");
			image1 = new Image(getClass().getResourceAsStream("imgs/school_w.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/selec_w.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/student_b.png"));
		}
		
		imageView1 = new ImageView(image1);
		imageView2 = new ImageView(image2);
		imageView3 = new ImageView(image3);
		imageView1.setFitWidth(24);
		imageView1.setFitHeight(24);
		imageView2.setFitWidth(24);
		imageView2.setFitHeight(24);
		imageView3.setFitWidth(24);
		imageView3.setFitHeight(24);
		school_candidateurbtn.setGraphic(imageView1);
		school_selectionbtn.setGraphic(imageView2);
		school_studentbtn.setGraphic(imageView3);
	}
}
