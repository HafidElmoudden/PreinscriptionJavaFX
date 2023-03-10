package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import application.GlobalControllers;
import application.Navigation;
import application.entities.FormationPost;
import application.entities.SchoolInformations;
import application.entities.StudentInformations;
import application.repositories.StudentRepository;
import application.services.CommonService;
import application.services.FormationService;
import application.services.SchoolService;
import application.services.StudentService;
import application.utilities.DateParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentScreenController implements Initializable {

	Navigation navigation = new Navigation();
	@FXML
	private TableColumn<?, ?> actions_grid_home;

	@FXML
	private ChoiceBox<String> student_schoollist_ville_filter;
	@FXML
	private Label age_toshow;

	@FXML
	private Label bactype_toshow, bacyear_toshow, city_toshow, cne_toshow, email_toshow;

	@FXML
	private TableColumn<?, ?> etablissement_grid_home;

	@FXML
	private TableColumn<?, ?> formation_grid_home, school_code;

	@FXML
	private Label fullname_toshow;

	@FXML
	private TableColumn<?, ?> residuelle_grid_home;

	@FXML
	private Pane student_applications;

	@FXML
	private Button student_appsbtn;

	@FXML
	private TableView<FormationPost> student_grid_home, my_applications_grid, notifications_grid, dernier_choix_grid;

	@FXML
	private Pane student_home;

	@FXML
	private Button student_homebtn;

	@FXML
	private Pane student_infos;

	@FXML
	private Button student_infosbtn;

	@FXML
	private Button student_logout;

	@FXML
	private Pane student_notifications;

	@FXML
	private Button student_notifsbtn;

	@FXML
	private TableColumn<?, ?> ville_grid_home;
	
	@FXML
	private Label anglaisNote;
	@FXML
	private Label phyNote;
	@FXML
	private Label svtNote;
	@FXML
	private Label frenchNote;
	@FXML
	private Label mathNote;

	// Email fields
	@FXML
	private Label emailActuel, student_num;
	@FXML
	private TextField newEmailField;
	@FXML
	private TextField confirmEmailField;
	@FXML
	private Button changeEmailBtn;

	// Password fields
	@FXML
	private TextField currPassField;
	@FXML
	private TextField newPassField;
	@FXML
	private TextField confPassField;
	@FXML
	private Button changePassBtn;
	@FXML
	private TextField newPhoneField;
	@FXML
	private TextField confPhoneField;
	@FXML
	private Button changePhoneBtn;
	@FXML
	private TableColumn<?, ?> etablissement_notifs;
	@FXML
	private TableColumn<?, ?> formation_notifs, cpcode_notifs;
	@FXML
	private TableColumn<?, ?> ville_notifs, date_notifs;
	@FXML
	private TableColumn<?, ?> etablissement_apps, fromation_apps, ville_apps, note_apps, residuelle_apps, candidateur_code;
	
	//Table actions
	//Notifications table
	@FXML
	private TableColumn<?,?> accept_notifs;
	@FXML
	private TableColumn<?,?> decline_notifs;
	//My Applications table
	@FXML
	private TableColumn<?,?> delete_apps;
	//Home table
	@FXML
	private TableColumn<?,?> viewschool_grid_home, cp_code;
	@FXML
	private TableColumn<?,?> apply_grid_home;
	
	@FXML
	private TableColumn<?, ?> etablissement_dc, formation_dc, ville_dc, date_dc;
	@FXML
	private TableColumn<?,?> repondreAvant_notifs;
	public void fillDrenierChoix(String cne) {
		etablissement_dc.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		formation_dc.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_dc.setCellValueFactory(new PropertyValueFactory<>("ville"));
		date_dc.setCellValueFactory(new PropertyValueFactory<>("date_"));
		
		FormationService.fillMyDCGrid(dernier_choix_grid, cne);
	}
	@FXML
	void handleButtonAction(ActionEvent event) {

		if (event.getSource() == student_appsbtn) {
			student_applications.toFront();
			toggleStyleClass(student_appsbtn);
		}
		else if (event.getSource() == student_homebtn) {
			student_home.toFront();
			toggleStyleClass(student_homebtn);
		}
		else if (event.getSource() == student_infosbtn) {
			student_infos.toFront();
			toggleStyleClass(student_infosbtn);
		}
		else if (event.getSource() == student_notifsbtn) {
			student_notifications.toFront();
			toggleStyleClass(student_notifsbtn);
		}
		else if (event.getSource() == student_logout) {
			Stage stage = (Stage) student_logout.getScene().getWindow();
			navigation.backToLogin(stage);
		}
	}
	
	public void fillSchoolViewer(String ecole_code) {
		SchoolInformations school= SchoolService.getSchoolInformations(ecole_code);
		school_title.setText(school.getEtablissement());
		school_adress.setText(school.getAdress());
		school_telephone.setText(school.getPhone());
		school_email.setText(school.getEmail());
		school_description.setText(school.getDescription());
		school_fax.setText(school.getFax());
		school_website.setText(school.getWebsite());
		formation_inview_column.setCellValueFactory(new PropertyValueFactory<>("formation"));
		SchoolService.fillFomationView(formation_inview, ecole_code);
		
		Image banner = new Image(getClass().getResource(school.getBanner()).toExternalForm());
		Image logo = new Image(getClass().getResource(school.getLogo()).toExternalForm());
		school_logo.setImage(logo);
		school_bg.setImage(banner);
	}
	
	public String getSelectedsCode() {
		  TableView.TableViewSelectionModel<FormationPost> selectionModel = student_grid_home.getSelectionModel();
		  
		  int selectedIndex = selectionModel.getSelectedIndex();
		  if (selectedIndex < 0) {
		    return null;
		  }
		  
		  FormationPost selected = student_grid_home.getItems().get(selectedIndex);
		  
		  return selected.getEcole_code();
	}
	
    @FXML
    private ImageView school_logo= new ImageView(), school_bg= new ImageView();
	@FXML
	private Label school_title, school_description, school_adress, school_telephone, school_fax, school_email;
    @FXML
    private Hyperlink school_website;
    @SuppressWarnings("exports")
	@FXML
	public Pane view_school;
    @FXML
    private TableView<FormationPost> formation_inview;
    @FXML
    private TableColumn<?, ?>  formation_inview_column;
    @FXML
    private TableColumn<?, ?> imagein_frofilegrid;
    
    StudentInformations student;
    
    public void updateTableViews() {
    	if(student != null) {
    		fillOrUpdateMyApps((String) student.getCne());
    		fillNotifications((String) student.getCne());
    		fillDrenierChoix((String) student.getCne());
    		fillFormations();
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		toggleStyleClass(student_homebtn);
		GlobalControllers.studentController = this;
		imagein_frofilegrid.setCellValueFactory(new PropertyValueFactory<>("checkImage"));
		
		//Notifications table
		accept_notifs.setCellValueFactory(new PropertyValueFactory<>("acceptNotif"));
		decline_notifs.setCellValueFactory(new PropertyValueFactory<>("declineNotif"));
		
		//My Applications table
		delete_apps.setCellValueFactory(new PropertyValueFactory<>("deleteApp"));
		
		//Home table
		viewschool_grid_home.setCellValueFactory(new PropertyValueFactory<>("viewSchool"));
		apply_grid_home.setCellValueFactory(new PropertyValueFactory<>("applySchool"));
		
		emailActuel.setText(Navigation.email);
		student = StudentService.getStudentInformations(Navigation.email);
		// Header informations fill
		fullname_toshow.setText(student.getFirstName() + " " + student.getLastName());
		email_toshow.setText(student.getEmail());
		cne_toshow.setText(student.getCne());
		bactype_toshow.setText(student.getBac());
		LocalDate parseDateNai = null;
		if(student.getDateNaissance() != null)
			parseDateNai = DateParser.parseDate(student.getDateNaissance());
		if (parseDateNai != null) {
			Period age = Period.between(parseDateNai, LocalDate.now());
			age_toshow.setText(String.valueOf(age.getYears()));
		}

		bacyear_toshow.setText(student.getBacYear());
		city_toshow.setText(student.getCity());

		// Notes fill
		anglaisNote.setText(student.getBacInformations().note_anglais);
		phyNote.setText(student.getBacInformations().note_physic);
		svtNote.setText(student.getBacInformations().note_svt);
		frenchNote.setText(student.getBacInformations().note_francais);
		mathNote.setText(student.getBacInformations().note_math);
		
		// Email Change Action
		changeEmailBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.NONE);
			if(newEmailField.getText().length() == 0 || confirmEmailField.getText().length() == 0) {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("Veuillez remplir les champs d'email!");
				alert.show();
				return;
			}
				
			if (newEmailField.getText().equals(Navigation.email)) {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("L'email entr??e est l'e-mail actuel");
				alert.show();
			} else if (newEmailField.getText().equals(confirmEmailField.getText())) {
				StudentRepository.changeStudentEmail(Navigation.email, newEmailField.getText(), student.getCne());
				Navigation.email = newEmailField.getText();
				emailActuel.setText(newEmailField.getText()); 
				email_toshow.setText(newEmailField.getText());
				student.setEmail(newEmailField.getText());
				
				alert.setAlertType(Alert.AlertType.CONFIRMATION);
				alert.setContentText("l'email a chang?? avec succ??s");
				alert.show();
			} else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("V??rifiez les champs e-mail : " + newEmailField.getText() + " , "
						+ confirmEmailField.getText());
				alert.show();
			}

		});

		// Password change action
		changePassBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.NONE);

			if (currPassField.getText().equals(Navigation.password)
					&& newPassField.getText().equals(confPassField.getText())
					&& currPassField.getText().length() != 0
					&& newPassField.getText().length() != 0
					&& confPassField.getText().length() != 0) {
				StudentRepository.changeStudentPassword(Navigation.email, newPassField.getText());
				Navigation.password = newPassField.getText();
				alert.setAlertType(Alert.AlertType.CONFIRMATION);
				alert.setContentText("le mote de passe a chang?? avec succ??s");
				alert.show();
			} else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("V??rifiez les champs de mote de passe!");
				alert.show();
			}
		});

		// Phone change action
		changePhoneBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.NONE);
			
			if (newPhoneField.getText().equals(confPhoneField.getText()) && newPhoneField.getText().length() != 0 && confPhoneField.getText().length() != 0) {
				StudentRepository.changeStudentPhone(student.getCne(), newPhoneField.getText());
				student.setTelephone(newPhoneField.getText());
				student_num.setText(newPhoneField.getText());
				alert.setAlertType(Alert.AlertType.CONFIRMATION);
				alert.setContentText("le numero de telephone a chang?? avec succ??s");
				alert.show();
			} else {
				alert.setAlertType(Alert.AlertType.ERROR);
				alert.setContentText("V??rifiez les champs des numero de telephone!");
				alert.show();
			}
		});

		// Fill Grid
		fillFormations();
		CommonService.fillVilles(student_schoollist_ville_filter, true);
		student_schoollist_ville_filter.setValue("Toutes les villes");
		
		// My informations
		student_num.setText(student.getTelephone());
		emailActuel.setText(student.getEmail());

		// My applications
		fillOrUpdateMyApps((String) student.getCne());

		// My Notifications
		fillNotifications((String) student.getCne());
		fillDrenierChoix((String) student.getCne());
	}
	
	public void fillOrUpdateMyApps(String cne) {
		etablissement_apps.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		fromation_apps.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_apps.setCellValueFactory(new PropertyValueFactory<>("ville"));
		note_apps.setCellValueFactory(new PropertyValueFactory<>("classement_note"));
		residuelle_apps.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_available"));
		candidateur_code.setCellValueFactory(new PropertyValueFactory<>("candida_code"));

		FormationService.fillMyAppsGrid(my_applications_grid, cne);
	}

	private void fillFormations() {
		etablissement_grid_home.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		formation_grid_home.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_grid_home.setCellValueFactory(new PropertyValueFactory<>("ville"));
		residuelle_grid_home.setCellValueFactory(new PropertyValueFactory<>("nbr_chaises_available"));
		school_code.setCellValueFactory(new PropertyValueFactory<>("ecole_code"));
		cp_code.setCellValueFactory(new PropertyValueFactory<>("candida_code"));
		
		
		student_schoollist_ville_filter.setOnAction(event -> {
			String villeSelectedItem = (String) student_schoollist_ville_filter.getSelectionModel().getSelectedItem();
			FormationService.fillFormationPosts(student_grid_home, villeSelectedItem);
		});
		FormationService.fillFormationPosts(student_grid_home, null);
	}

	private void fillNotifications(String cne) {

		etablissement_notifs.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
		formation_notifs.setCellValueFactory(new PropertyValueFactory<>("formation"));
		ville_notifs.setCellValueFactory(new PropertyValueFactory<>("ville"));
		date_notifs.setCellValueFactory(new PropertyValueFactory<>("date_"));
		cpcode_notifs.setCellValueFactory(new PropertyValueFactory<>("candida_code"));
		repondreAvant_notifs.setCellValueFactory(new PropertyValueFactory<>("repondreAvant"));
		FormationService.fillMyNotifsGrid(notifications_grid, cne);
	}
	public void toggleStyleClass(@SuppressWarnings("exports") Button event) {
		
		
		Image image1 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		Image image2 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		Image image3 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		Image image4 = new Image(getClass().getResourceAsStream("imgs/default.jpg"));
		
		ImageView imageView1 = new ImageView(image1);
		ImageView imageView2 = new ImageView(image2);
		ImageView imageView3 = new ImageView(image3);
		ImageView imageView4 = new ImageView(image4);
		if (event == student_homebtn) {

			student_homebtn.getStyleClass().add("active");
			student_infosbtn.getStyleClass().removeAll("active");
			student_notifsbtn.getStyleClass().removeAll("active");
			student_appsbtn.getStyleClass().removeAll("active");
			
			image1 = new Image(getClass().getResourceAsStream("imgs/home_b.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/perso_w.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/notifs_w.png"));
			image4 = new Image(getClass().getResourceAsStream("imgs/appl_w.png"));

		}
		else if (event == student_infosbtn) {

			student_homebtn.getStyleClass().removeAll("active");
			student_infosbtn.getStyleClass().add("active");
			student_notifsbtn.getStyleClass().removeAll("active");
			student_appsbtn.getStyleClass().removeAll("active");
			
			image1 = new Image(getClass().getResourceAsStream("imgs/home_w.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/perso_b.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/notifs_w.png"));
			image4 = new Image(getClass().getResourceAsStream("imgs/appl_w.png"));
		}
		else if(event == student_notifsbtn) {
			
			student_homebtn.getStyleClass().removeAll("active");
			student_infosbtn.getStyleClass().removeAll("active");
			student_notifsbtn.getStyleClass().add("active");
			student_appsbtn.getStyleClass().removeAll("active");
			
			image1 = new Image(getClass().getResourceAsStream("imgs/home_w.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/perso_w.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/notifs_b.png"));
			image4 = new Image(getClass().getResourceAsStream("imgs/appl_w.png"));
		}
		else if(event == student_appsbtn) {
			
			student_homebtn.getStyleClass().removeAll("active");
			student_infosbtn.getStyleClass().removeAll("active");
			student_notifsbtn.getStyleClass().removeAll("active");
			student_appsbtn.getStyleClass().add("active");
			
			image1 = new Image(getClass().getResourceAsStream("imgs/home_w.png"));
			image2 = new Image(getClass().getResourceAsStream("imgs/perso_w.png"));
			image3 = new Image(getClass().getResourceAsStream("imgs/notifs_w.png"));
			image4 = new Image(getClass().getResourceAsStream("imgs/appl_b.png"));
		}
		
		imageView1 = new ImageView(image1);
		imageView2 = new ImageView(image2);
		imageView3 = new ImageView(image3);
		imageView4 = new ImageView(image4);

		imageView1.setFitWidth(24);
		imageView1.setFitHeight(24);

		imageView2.setFitWidth(24);
		imageView2.setFitHeight(24);
		
		imageView3.setFitWidth(24);
		imageView3.setFitHeight(24);
		
		imageView4.setFitWidth(24);
		imageView4.setFitHeight(24);
		
		student_homebtn.setGraphic(imageView1);
		student_infosbtn.setGraphic(imageView2);
		student_notifsbtn.setGraphic(imageView3);
		student_appsbtn.setGraphic(imageView4);
	}
}
