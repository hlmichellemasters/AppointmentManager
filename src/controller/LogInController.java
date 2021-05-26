package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
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
    private int logCounter = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogInZoneIDText.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            AppointmentCalendar.getAllAppointmentsFromDB();
            Customer.getAllCustomersFromDB();
            User.getAllUsersFromDB();
            Contact.getAllContactsFromDB();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML // TODO add password login
    public void OnLogInButton(ActionEvent event) throws IOException {

        MainAppointmentController.loadMain(event);
        User Michelle = new User(99, "Testing fix later");
        Michelle.setUserLoggedIn(Michelle);
        logLogin("Success");
    }

    /**
     * Logs any attempt at log in by recording log-in number, timestamp (with date), userID used and success/failure
     * This code was adapted from Tim Bulchaka's Java Programming Masterclass for Software Developers
     * @param logInOutcome this is a String passed to indicate whether log-in succeeded or failed
     */
    private void logLogin (String logInOutcome) {

        try {
            Path dataPath = FileSystems.getDefault().getPath("logFile.txt");
            String logData = "\n " + logCounter + "     " + Instant.now() + "   " + LogInUserIDText.getText() +
                    "             " + logInOutcome;
            Files.write(dataPath, logData.getBytes("UTF-8"), StandardOpenOption.APPEND);
            logCounter++;

//            List<String> lines = Files.readAllLines(dataPath);
//            for(String line : lines) {
//                System.out.println(line);
//            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnLogInExitAppButton(ActionEvent event) {

        ControllerUtilities.closeApp(event);
    }


}

