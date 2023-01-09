package application.utilities;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConfirmationDialog extends Dialog<Boolean> {

    public ConfirmationDialog(String message) {
        setTitle("Confirmation");
        setHeaderText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

        ButtonType yesButtonType = new ButtonType("Oui", ButtonData.YES);
        ButtonType noButtonType = new ButtonType("Non", ButtonData.NO);
        getDialogPane().getButtonTypes().addAll(yesButtonType, noButtonType);

        VBox vbox = new VBox(new Label(message));
        getDialogPane().setContent(vbox);

        setResultConverter(dialogButton -> dialogButton == yesButtonType);
    }
}
