/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * Main class for Appointment Manager application
 */
package model;

import utilities.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * main class starts the application and loads the log in screen
 */
public class Main extends Application {

    /**
     * overrides default JavaFX start method to load the log in screen
     * @param primaryStage is the stage to load the screen on
     * @throws Exception for loading errors
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LogInScreen.fxml"));
        primaryStage.setTitle("Appointment Manager Log In");
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("/view/CSS.css");
        primaryStage.show();
    }

    /**
     * starts the database connection, launches the application, and at the application's close, closes database connection
     * @param args main application parameter
     */
    public static void main(String[] args) {

        DbConnection.startConnection();

        launch(args);

        DbConnection.closeConnection();
    }

}
