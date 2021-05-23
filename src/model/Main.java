package model;

import utilities.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LogInScreen.fxml"));
        primaryStage.setTitle("Appointment Manager Log In");
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("/view/CSS.css");
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {

        DbConnection.startConnection();

        launch(args);

//        ObservableList<Appointment> apptList = DbAppointments.getAppointments();
//        for(Appointment appt: apptList){
//            System.out.println(appt);
//        }

        DbConnection.closeConnection();
    }
}
