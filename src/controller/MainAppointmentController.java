/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 *  Controller for 'MainAppointment.fxml'
 */

package controller;

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
import model.Appointment;
import model.AppointmentCalendar;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * controller for 'MainAppointment.fxml'
 */
public class MainAppointmentController {

        @FXML // fx:id="NewAppointmentButton"
        private Button NewAppointmentButton; // Value injected by FXMLLoader

        @FXML // fx:id="weeklyRadio"
        private RadioButton weeklyRadio; // Value injected by FXMLLoader

        @FXML // fx:id="monthlyRadio"
        private RadioButton monthlyRadio; // Value injected by FXMLLoader

        @FXML // fx:id="allRadio"
        private RadioButton allRadio; // Value injected by FXMLLoader

        @FXML // fx:id="ApptWeeklyMonthlyAll"
        private ToggleGroup ApptWeeklyMonthlyAll; // Value injected by FXMLLoader

        @FXML // fx:id="ApptPreviousButton"
        private Button ApptPreviousButton; // Value injected by FXMLLoader

        @FXML // fx:id="ApptNextButton"
        private Button ApptNextButton; // Value injected by FXMLLoader

        @FXML // fx:id="CustomersButton"
        private Button CustomersButton; // Value injected by FXMLLoader

        @FXML // fx:id="ReportsButton"
        private Button ReportsButton; // Value injected by FXMLLoader

        @FXML // fx:id="AppExitButton"
        private Button AppExitButton; // Value injected by FXMLLoader

        @FXML // fx:id="apptMainTableView"
        private TableView<Appointment> apptTableView; // Value injected by FXMLLoader

        @FXML // fx:id="startDateTimeColumn"
        private TableColumn<Appointment, String> startDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="apptIDColumn"
        private TableColumn<Appointment, Integer> apptIDColumn; // Value injected by FXMLLoader

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

        private ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        private ObservableList<Appointment> filteredList = FXCollections.observableArrayList();
        private LocalDateTime dateStart = LocalDateTime.now();
        private int timeCounter = 1;

        /**
         * loads the Main Appointments table upon the scene loading with all appointments that are in the database
         */
        private void loadAppointmentsTable() {

                try {

                        apptList = AppointmentCalendar.provideApptList();

                        apptTableView.setItems(apptList);

                        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStart"));
                        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
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
         * reloads the appointments table given a filtered list of appointments (weekly, monthly)
         * @param filteredList passed from the weekly or monthly radio action functions
         */
        private void reloadAppointmentsTable(ObservableList<Appointment> filteredList) {

                try {

                        apptTableView.setItems(filteredList);

                        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStart"));
                        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
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
         * loads the appointments table (with all appointments) when the all radio button is pressed
         */
        @FXML
        private void OnAllRadio() {
                loadAppointmentsTable();
        }

        /**
         * loads a filtered list of appointments based on the current month
         * the month loaded can be adjusted with the prev and next buttons which adjusts the timeCounter
         */
        @FXML
        private void OnMonthlyRadio() {

                LocalDateTime end = dateStart.plusMonths(timeCounter);
                System.out.println("end is: " + end);
                LocalDateTime start = dateStart.plusMonths(timeCounter - 1);
                filterApptsBetween(start, end);
                System.out.println("filtered appts is: " + filteredList);
                reloadAppointmentsTable(filteredList);
        }

        /**
         * loads a filtered list of appointments based on the current week
         * the week loaded can be adjusted with the prev and next buttons which adjusts the timeCounter
         */
        @FXML
        private void OnWeeklyRadio() {

                LocalDateTime end = dateStart.plusWeeks(timeCounter);
                System.out.println("end is: " + end);
                LocalDateTime start = dateStart.plusWeeks(timeCounter - 1);
                filterApptsBetween(start, end);
                System.out.println("filtered appts is: " + filteredList);
                reloadAppointmentsTable(filteredList);
        }

        /**
         * increments time counter when next button pressed
         * this increments the week or month depending on which radio button is selected
         */
        @FXML
        private void OnApptNextButton() {

                timeCounter++;

                if (weeklyRadio.isSelected()) OnWeeklyRadio();
                if (monthlyRadio.isSelected()) OnMonthlyRadio();
        }

        /**
         * decrements time counter when previous button pressed
         * this decrements the week or month depending on which radio button is selected
         */
        @FXML
        private void OnApptPreviousButton() {

                timeCounter--;

                if (weeklyRadio.isSelected()) OnWeeklyRadio();
                if (monthlyRadio.isSelected()) OnMonthlyRadio();
        }

        /**
         * USES LAMBDA EXPRESSION to filter and collect appointments that are between two LocalDateTimes
         * the use of the lambda stream filter greatly simplifies the number of expressions and makes clear
         * the goal of the function (to filter and collect applicable dates from a list to a list)
         * @param filterStart the LocalDateTime to begin the time period
         * @param filterEnd the LocalDateTime to end the time period
         */
        private void filterApptsBetween(LocalDateTime filterStart, LocalDateTime filterEnd) {

                filteredList.clear();
                filteredList.addAll(apptList.stream().filter(dates -> dates.isAfter(filterStart) &&
                        dates.isBefore(filterEnd)).collect(Collectors.toList()));
        }

        /**
         * loads the customer scene when the customer button is pressed
         * @param event passed to the customer controller to load the customer scene
         */
        @FXML
        void OnCustomersButton(ActionEvent event) {

                try {
                        CustomerController.loadCustomerScene(event);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         * loads the appointment edit scene when the new or edit appointment button is pressed
         * @param event passed to the appointment edit controller to load add/edit appointment scene
         */
        @FXML
        void OnNewOrEditApptButton(ActionEvent event) {

                try {
                        AppointmentEditController.loadAddEditAppt(event);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         * loads the report scene when the reports button is pressed
         * @param event passed to the reports controller to load the reports scene
         */
        @FXML
        void OnReportsButton(ActionEvent event) {

                try {
                        ReportsController.loadReportsScene(event);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         * loads the main appointment scene including calling the load appointments table
         * @param event passed from any button or action that calls for loading the main appointments scene
         * @throws IOException throws an input-output exception for any issues loading
         */
        public static void loadMain(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppointmentController.class.getResource("/view/MainAppointment.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                MainAppointmentController controller = loader.getController();
                controller.loadAppointmentsTable();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                ControllerUtilities.centerStage(stage,1400, 845);
                stage.setTitle("Main Appointment");
                stage.setScene(scene);
                stage.show();
        }

        /**
         * closes the application when user presses exit application button
         * @param event
         */
        @FXML
        void OnAppExitButton(ActionEvent event) {
                ControllerUtilities.closeApp(event);
        }

    }

