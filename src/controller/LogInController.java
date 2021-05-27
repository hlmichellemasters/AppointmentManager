package controller;

import databaseAccess.DbUsers;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    @FXML // fx:id="welcomeLabel"
    private Label welcomeLabel;

    @FXML // fx:id="userNameLabel"
    private Label userNameLabel;

    @FXML // fx:id="passwordLabel"
    private Label passwordLabel;

    @FXML // fx:id="zoneIDLabel"
    private Label zoneIDLabel;

    @FXML // fx:id="LogInZoneIDText"
    private Label LogInZoneIDText; // Value injected by FXMLLoader

    @FXML // fx:id="LogInUserNameText"
    private TextField LogInUserNameText; // Value injected by FXMLLoader

    @FXML // fx:id="LogInButton"
    private Button LogInButton; // Value injected by FXMLLoader

    @FXML // fx:id="LogInPasswordField"
    private PasswordField LogInPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="LogInExitAppButton"
    private Button LogInExitAppButton; // Value injected by FXMLLoader

    private String errorHeader = "Wrong username and password combination";
    private String errorContent = "Try again";
    Locale locale = new Locale("fr");
    // Locale locale = Locale.getDefault();
    ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle", locale);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LogInZoneIDText.setText(String.valueOf(ZoneId.systemDefault()));

        if(locale.getLanguage().equals("fr")) {
            welcomeLabel.setText(rb.getString("Welcome"));
            userNameLabel.setText(rb.getString("UserName"));
            passwordLabel.setText(rb.getString("Password"));
            zoneIDLabel.setText(rb.getString("ZoneID"));
            LogInButton.setText(rb.getString("LogIn"));
            LogInExitAppButton.setText(rb.getString("ExitApplication"));
            errorHeader = rb.getString("ErrorHeader");
            errorContent = rb.getString("ErrorContent");
        }

        try {
            AppointmentCalendar.getAllAppointmentsFromDB();
            Customer.getAllCustomersFromDB();
            User.getAllUsersFromDB();
            Contact.getAllContactsFromDB();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * when the user clicks the Log In button, the userID is retrieved, if it exists and the password is checked
     * if the password is correct it allows the user into the main application,
     * @param event
     */
    @FXML
    public void OnLogInButton(ActionEvent event) {

        try {
            int userID = (User.getUserIDFromUserName(LogInUserNameText.getText()));
            System.out.println("UserID: " + userID);
            System.out.println("Password: " + LogInPasswordField.getText());

            if ((userID > 0) && (DbUsers.checkPassword(userID, LogInPasswordField.getText()))) {
                System.out.println("Checked password");
                MainAppointmentController.loadMain(event);
                User loggedInUser = User.getUserByID(userID);
                loggedInUser.setUserLoggedIn(loggedInUser);
                logLogin("Success");
                AppointmentCalendar.checkForUpcomingAppts();
            }

           else {
                logLogin("Failure");

                if(locale.getLanguage().equals("fr")) {
                    errorHeader = rb.getString("ErrorHeader");
                    errorContent = rb.getString("ErrorContent");
                }
                ControllerUtilities.ErrorException(errorHeader, errorContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs any attempt at log in by recording timestamp (with date), userID used and success/failure
     * This code was adapted from Tim Buchalka's Java Programming Masterclass for Software Developers
     * @param logInOutcome this is a String passed to indicate whether log-in succeeded or failed
     */
    private void logLogin (String logInOutcome) {

        try {
            Path dataPath = FileSystems.getDefault().getPath("logFile.txt");
            String logData = "\n" + Instant.now() + "            " + LogInUserNameText.getText() +
                    "             " + logInOutcome;
            Files.write(dataPath, logData.getBytes("UTF-8"), StandardOpenOption.APPEND);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnLogInExitAppButton(ActionEvent event) {

        ControllerUtilities.closeApp(event);
    }


}

