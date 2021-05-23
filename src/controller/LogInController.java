package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AppointmentCalendar;
import model.Customer;
import model.Main;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    @FXML // fx:id="LogInZoneIDText"
    private Label LogInZoneIDText; // Value injected by FXMLLoader

    @FXML // fx:id="LogInUserIDText"
    private TextField LogInUserIDText; // Value injected by FXMLLoader

    @FXML // fx:id="LogInButon"
    private Button LogInButon; // Value injected by FXMLLoader

    @FXML // fx:id="LogInPasswordField"
    private PasswordField LogInPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="LogInExitAppButton"
    private Button LogInExitAppButton; // Value injected by FXMLLoader

    public static ZoneId userZoneID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogInZoneIDText.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            AppointmentCalendar.getAllAppointmentsFromDB();
            Customer.getCustomersFromDB();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML // TODO add password login
    public void OnLogInButton(ActionEvent event) throws IOException {

        MainAppointmentController.loadMain(event);
        User Michelle = new User(99, "Testing fix later");
        Michelle.setUserLoggedIn(Michelle);
    }

    @FXML
    public void OnLogInExitAppButton(ActionEvent event) {

        MainAppointmentController.closeApp(event);
    }


}

