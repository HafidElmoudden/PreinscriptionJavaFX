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

public class SchoolEditDialog extends Dialog<application.utilities.SchoolEditDialog.SchoolEditData> {

    private TextField etablissementField;
    private TextField villeField;
    private TextField emailField;
    private TextField telephoneField;

    public SchoolEditDialog(String etablissement, String ville, String email, String telephone) {
        setTitle("Modification des informations");
        setHeaderText("Modifiez les informations de l'étudiant");

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        etablissementField = new TextField();
        etablissementField.setText(etablissement);
        villeField = new TextField();
        villeField.setText(ville);
        emailField = new TextField();
        emailField.setText(email);
        telephoneField = new TextField();
        telephoneField.setText(telephone);
        
        etablissementField.setPrefWidth(300);
        villeField.setPrefWidth(300);
        emailField.setPrefWidth(300);
        telephoneField.setPrefWidth(300);
        
        grid.add(new Label("Etablissement:"), 0, 0);
        grid.add(etablissementField, 1, 0);
        grid.add(new Label("Ville:"), 0, 1);
        grid.add(villeField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Téléphone:"), 0, 3);
        grid.add(telephoneField, 1, 3);

        getDialogPane().setContent(grid);

        
        setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (etablissementField.getText().isEmpty() || villeField.getText().isEmpty() || emailField.getText().isEmpty() || telephoneField.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to save changes");
                    alert.setContentText("Please fill in all fields before saving.");

                    alert.showAndWait();
                } else {
                    return new SchoolEditData(etablissementField.getText(), villeField.getText(), emailField.getText(), telephoneField.getText());
                }
            }
            return null;
        });
    }
    
    public class SchoolEditData {
        private String etablissement;
        private String ville;
        private String email;
        private String telephone;

        public SchoolEditData(String etablissement, String ville, String email, String telephone) {
            this.etablissement = etablissement;
            this.ville = ville;
            this.email = email;
            this.telephone = telephone;
        }

        public String getEtablissement() {
            return etablissement;
        }

        public String getVille() {
            return ville;
        }

        public String getEmail() {
            return email;
        }

        public String getTelephone() {
            return telephone;
        }
    }

}
