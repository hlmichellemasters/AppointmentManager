/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * utilities for the controller classes
 */
package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * contains the utilities used by one or more of the controllers to reduce repetitions.
 */
public class ControllerUtilities {

    /**
     * centers the stage of the scenes so that they stay in the middle of the user's computer screen.
     * @param stage which is to be centered
     * @param width the width of the scene
     * @param height the height of the scene
     */
    public static void centerStage(Stage stage, double width, double height) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }

    /**
     * closes the application
     * @param event
     */
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
     * @param headerText is the heading or category of the information
     * @param contentText is the details passed from calling method regarding the information.
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
