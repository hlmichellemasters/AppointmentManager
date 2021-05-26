package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class ControllerUtilities {

    public static void centerStage(Stage stage, double width, double height) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }

    public static void closeApp(ActionEvent event) {

        Platform.exit();
    }

    /**
     * displays an error dialog box with the header text and content text details from calling method.
     * @param headerText is the heading or category of the error
     * @param contentText is the details passed from calling method regarding the error.
     */
    public static void ErrorException (String headerText, String contentText) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * displays an error dialog box with the header text and content text details from calling method.
     * @param headerText is the heading or category of the error
     * @param contentText is the details passed from calling method regarding the error.
     */
    public static void InformationalDialog (String headerText, String contentText) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * displays a delete confirmation dialog box and displays a method's context text.
     * @param contentText is the text from the method with more information regarding details of delete
     * @return boolean true if the okay button is pressed for deleting, and false if cancelled.
     */
    public static boolean DeleteConfirmation(String contentText) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete item?");
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
}
