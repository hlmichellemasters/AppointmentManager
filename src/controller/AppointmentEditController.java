/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * controller for the AppointmentEdit Scene
 */
package controller;

import databaseAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *  controller for 'AppointmentEditScreen.fxml'
 *  controls the appointment tables and the buttons to edit, delete appointments
 *  also controls the form for adding and editing appointments, including the save and clear buttons
 *  provides exit back to main appointment screen
 */
public class AppointmentEditController {

        @FXML // fx:id="apptTableView"
        private TableView<Appointment> apptTableView; // Value injected by FXMLLoader

        @FXML // fx:id="startDateTimeColumn"
        private TableColumn<Appointment, String> startDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="apptIDColumn"
        private TableColumn<Appointment, Integer> apptIDColumn; // Value injected by FXMLLoader

        @FXML // fx:id="apptTitleColumn"
        private TableColumn<Appointment, String> apptTitleColumn; // Value injected by FXMLLoader

        @FXML // fx:id="descriptionColumn"
        private TableColumn<Appointment, String> descriptionColumn; // Value injected by FXMLLoader

        @FXML // fx:id="typeColumn"
        private TableColumn<Appointment, String> typeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="locationColumn"
        private TableColumn<Appointment, String> locationColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactColumn"
        private TableColumn<Appointment, String> contactColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerColumn"
        private TableColumn<Appointment, String> customerColumn; // Value injected by FXMLLoader

        @FXML // fx:id="endDateTimeColumn"
        private TableColumn<Appointment, String> endDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="apptIDAutoGeneratedText"
        private Label apptIDAutoGeneratedText; // Value injected by FXMLLoader

        @FXML // fx:id="apptTitleText"
        private TextField apptTitleText; // Value injected by FXMLLoader

        @FXML // fx:id="apptDescriptionText"
        private TextField apptDescriptionText; // Value injected by FXMLLoader

        @FXML // fx:id="apptTypeText"
        private TextField apptTypeText; // Value injected by FXMLLoader

        @FXML // fx:id="apptLocationText"
        private TextField apptLocationText; // Value injected by FXMLLoader

        @FXML // fx:id="apptCustomerCombo"
        private ComboBox<Customer> apptCustomerCombo; // Value injected by FXMLLoader

        @FXML // fx:id="ApptStartTimeCombo"
        private ComboBox<LocalTime> apptStartTimeCombo; // Value injected by FXMLLoader

        @FXML // fx:id="apptEndTimeCombo"
        private ComboBox<LocalTime> apptEndTimeCombo; // Value injected by FXMLLoader

        @FXML // fx:id="apptContactCombo"
        private ComboBox<Contact> apptContactCombo; // Value injected by FXMLLoader

        @FXML // fx:id="apptUserCombo"
        private ComboBox<User> apptUserCombo; // Value injected by FXMLLoader

        @FXML // fx:id="apptStartDatePicker"
        private DatePicker apptStartDatePicker; // Value injected by FXMLLoader

        @FXML // fx:id="apptEndDatePicker"
        private DatePicker apptEndDatePicker; // Value injected by FXMLLoader

        private ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
        private ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();

        // these static final variables represent the start and end business times in EST, and the num hours open
        // (the times available for making appointments)
        private static final int startHourEST = 8;
        private static final int startMinuteEST = 0;
        private static final int hoursOpen = 14;
        private static final int endHourEST = 8;
        private static final int endMinuteEST = 15;

        /**
         * loads the Appointment Edit scene, including calling to load the appointments table and the form
         * @param event provided by the calling button on another scene
         * @throws IOException back to the caller if there is a loading error
         */
        public static void loadAddEditAppt(ActionEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AppointmentEditController.class.getResource("/view/AppointmentEditScreen.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                AppointmentEditController controller = loader.getController();
                controller.loadAppointmentsTable();
                controller.initializeAddEditAppt();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                ControllerUtilities.centerStage(stage,1400, 845);
                stage.setTitle("Add or Edit Appointment");
                stage.setScene(scene);
                stage.show();
        }

        /**
         * this performs the initial load of the main appointments table, grabbing the appt data from the database
         */
        public void loadAppointmentsTable() {

                try {

                        apptTableView.setItems(AppointmentCalendar.provideApptList());
                        System.out.println("Set list in tableview");

                        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStart"));
                        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
                        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
                        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
                        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
                        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
                        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedEnd"));

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * initializes the add or edit form for appointments, including setting combo boxes and business times available
         */
        public void initializeAddEditAppt() {
                try {
                        apptIDAutoGeneratedText.setText(String.valueOf(DbAppointments.getNextApptID()));
                        apptContactCombo.setItems(Contact.provideContactList());
                        apptCustomerCombo.setItems(CustomerList.provideCustomerList());
                        apptUserCombo.setItems(User.provideUserList());

                        startTimeList.addAll(utilities.TimeZoneConversions.getBusinessTimes(startHourEST, startMinuteEST, hoursOpen));
                        apptStartTimeCombo.setItems(startTimeList);
                        endTimeList.addAll(utilities.TimeZoneConversions.getBusinessTimes(endHourEST, endMinuteEST, hoursOpen));
                        apptEndTimeCombo.setItems(endTimeList);

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * loads the appointment selected into the edit form on the right if an appointment is selected
         * otherwise throws an error dialog to instruct the user to select an appointment first
         */
        @FXML
        void OnEditApptButton() {

                Appointment appointment = apptTableView.getSelectionModel().getSelectedItem();

                if (appointment != null) {
                        apptIDAutoGeneratedText.setText(String.valueOf(appointment.getApptID()));
                        apptTitleText.setText(appointment.getTitle());
                        apptDescriptionText.setText(appointment.getDescription());
                        apptTypeText.setText(appointment.getType());
                        apptLocationText.setText(appointment.getLocation());
                        apptContactCombo.setValue(appointment.getContact());
                        apptCustomerCombo.setValue(appointment.getCustomer());
                        apptUserCombo.setValue(appointment.getUser());
                        apptStartDatePicker.setValue(appointment.getStartDate());
                        apptStartTimeCombo.setValue(appointment.getStartTime());
                        apptEndDatePicker.setValue(appointment.getEndDate());
                        apptEndTimeCombo.setValue(appointment.getEndTime());
                }

                else {
                        ControllerUtilities.ErrorException("No Appointment Selected", "Select an appointment in order to" +
                                " edit an appointment.");
                }

        }

        /**
         * deletes a selected appointment if one is selected and the user confirms that they want to delete
         * otherwise informs the user to select an appointment in order to delete an appointment
         */
        @FXML
        void OnDeleteApptButton() {

                Appointment selectedAppt = apptTableView.getSelectionModel().getSelectedItem();

                if (selectedAppt != null && ControllerUtilities.DeleteConfirmation("Confirm that you wish " +
                                "to delete " + selectedAppt)) {

                        String deletedApptInfo = "ID: " + selectedAppt.getApptID() + " and type: " + selectedAppt.getType();

                        DbAppointments.removeAppointment(selectedAppt);
                        AppointmentCalendar.deleteAppointment(selectedAppt);

                        ControllerUtilities.InformationalDialog("Deleted Appointment", "the " +
                                "appointment " + deletedApptInfo + " was deleted ");
                }

                else {
                        ControllerUtilities.ErrorException("No Appointment Selected", "Select an appointment in order to" +
                                " delete an appointment.");
                }

        }

        /**
         * saves the appointment with the information that is in the edit form to the database
         * and displays in the tableview
         * checks for any conflicting customer appointments before saving and shows error message,
         * if there is overlap (StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
         * @throws Exception
         */
        @FXML
        void OnApptSaveButton() {

                if (apptIDAutoGeneratedText.getText().isBlank() || apptTitleText.getText().isBlank() ||
                        apptDescriptionText.getText().isBlank() || apptTypeText.getText().isBlank() ||
                        apptLocationText.getText().isBlank() || apptContactCombo.getSelectionModel().getSelectedItem() == null ||
                        apptCustomerCombo.getSelectionModel().getSelectedItem() == null || apptUserCombo.getSelectionModel().getSelectedItem() == null ||
                        apptStartDatePicker.getValue() == null || apptStartTimeCombo.getSelectionModel().getSelectedItem() == null ||
                        apptEndDatePicker.getValue() == null || apptEndTimeCombo.getSelectionModel().getSelectedItem() == null) {

                        ControllerUtilities.ErrorException("Blank fields in appointment information", "Please put at least " +
                                "one character in every text field and select a value for each combo box and date picker " +
                                "to save the appointment");

                        return;
                }

                int apptID = Integer.valueOf(apptIDAutoGeneratedText.getText());
                String apptTitle = apptTitleText.getText();
                String apptDescription = apptDescriptionText.getText();
                String apptType = apptTypeText.getText();
                String apptLocation = apptLocationText.getText();
                int apptContactID = apptContactCombo.getSelectionModel().getSelectedItem().getContactID();
                int apptCustomerID = apptCustomerCombo.getSelectionModel().getSelectedItem().getCustomerID();
                int apptUserID = apptUserCombo.getSelectionModel().getSelectedItem().getUserID();
                LocalDate startDate = apptStartDatePicker.getValue();
                LocalTime startTime = apptStartTimeCombo.getValue();
                LocalDate endDate = apptEndDatePicker.getValue();
                LocalTime endTime = apptEndTimeCombo.getValue();

                LocalDateTime apptStart = startDate.atTime(startTime);
                LocalDateTime apptEnd = endDate.atTime(endTime);
                User apptUser = User.getUserByID(apptUserID);
                Contact apptContact = Contact.getContactByID(apptContactID);
                Customer apptCustomer = CustomerList.getCustomerByID(apptCustomerID);

                // check if end datetime is before start datetime
                if (apptStart.isAfter(apptEnd)) {
                        ControllerUtilities.ErrorException("Appointment end must be after start", " " +
                                "The start datetime " + Appointment.displayDateTime(apptStart) + " is before the end datetime " +
                                Appointment.displayDateTime(apptEnd) + ".  To save the appointment please make the end datetime after " +
                                " the start.");
                        return;
                }

                // check if customer's other appts overlap
                if (AppointmentCalendar.apptOverlapsForCustomer(apptCustomer, apptStart, apptEnd, apptID)) {
                        ControllerUtilities.ErrorException(apptCustomer + " is already booked for that time", "Please " +
                                "find a different time for their appointment.");
                        return;
                }

                Appointment appointment = new Appointment(apptID, apptTitle, apptDescription, apptLocation,
                        apptType, apptStart, apptEnd, apptUser, apptContact, apptCustomer);

                // if new appointment (the next appt ID minus 1 because just saved it to DB) add directly to calendar
                if (apptID == (DbAppointments.getNextApptID())) {
                        AppointmentCalendar.addApptToCalendar(appointment);
                }

                // else update to appointment and remove old and add new appointment information in calendar
                else {
                        AppointmentCalendar.updateAppointment(appointment);
                        System.out.println("updated appointment list");
                }

                //save to database and to application list
                DbAppointments.saveApptToDB(apptID, apptTitle, apptDescription, apptType, apptLocation, apptContactID, apptCustomerID,
                        apptUserID, startDate, startTime, endDate, endTime);

                clearApptAddEdit();
                apptTableView.refresh();
        }

        /**
         * clears the form by calling clearApptAddEdit()
         */
        @FXML
        public void OnApptClearButton() {
                clearApptAddEdit();
        }

        /**
         * clears all of the text fields and combo boxes of the edit form
         */
        public void clearApptAddEdit() {
                apptIDAutoGeneratedText.setText(String.valueOf(DbAppointments.getNextApptID()));
                apptTitleText.clear();
                apptDescriptionText.clear();
                apptTypeText.clear();
                apptLocationText.clear();
                apptContactCombo.getSelectionModel().clearSelection();
                apptContactCombo.setValue(null);
                apptCustomerCombo.getSelectionModel().clearSelection();
                apptCustomerCombo.setValue(null);
                apptUserCombo.getSelectionModel().clearSelection();
                apptUserCombo.setValue(null);
                apptStartDatePicker.setValue(null);
                apptStartTimeCombo.getSelectionModel().clearSelection();
                apptStartTimeCombo.setValue(null);
                apptEndDatePicker.setValue(null);
                apptEndTimeCombo.getSelectionModel().clearSelection();
                apptEndTimeCombo.setValue(null);
                apptTableView.getSelectionModel().clearSelection();
        }

        /**
         * reloads the main appointment scene upon the user clicking the Exit button
         * @param event passed to load the main appointment scene
         * @throws IOException if there is an input output issue
         */
        @FXML
        void OnApptEditScreenExit(ActionEvent event) throws IOException {

                MainAppointmentController.loadMain(event);
        }
    }

