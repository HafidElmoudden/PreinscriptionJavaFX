package application.utilities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StudentEditDialog extends Dialog<application.utilities.StudentEditDialog.StudentEditData> {

    private TextField nomField;
    private TextField prenomField;
    private TextField emailField;
    private TextField villeField;

    public StudentEditDialog(String nom, String prenom, String email, String ville) {
        setTitle("Modification des informations");
        setHeaderText("Modifiez les informations de l'étudiant");

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));



        nomField = new TextField();
        nomField.setText(nom);
        prenomField = new TextField();
        prenomField.setText(prenom);
        emailField = new TextField();
        emailField.setText(email);
        villeField = new TextField();
        villeField.setText(ville);
        
        nomField.setPrefWidth(300);
        prenomField.setPrefWidth(300);
        emailField.setPrefWidth(300);
        villeField.setPrefWidth(300);
        
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Prénom:"), 0, 1);
        grid.add(prenomField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Ville:"), 0, 3);
        grid.add(villeField, 1, 3);

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() || villeField.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to save changes");
                    alert.setContentText("Please fill in all fields before saving.");

                    alert.showAndWait();
                    return null;
                } else {
                    return new StudentEditData(nomField.getText(), prenomField.getText(), emailField.getText(), villeField.getText());
                }
            }
            return null;
        });
    }
    
    public class StudentEditData {
        private String nom;
        private String prenom;
        private String email;
        private String ville;

        public StudentEditData(String nom, String prenom, String email, String ville) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.ville = ville;
        }

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}
    }

}
