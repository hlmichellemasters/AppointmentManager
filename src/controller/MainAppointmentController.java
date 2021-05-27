/**
 * 'MainAppointment.fxml' Controller Class
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
         *
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
                        System.out.println("Exception Occurred");
                        e.printStackTrace();
                }
        }

        private void reloadAppointmentsTable(ObservableList<Appointment> filteredList) {

                try {

                        apptTableView.setItems(filteredList);
                        System.out.println("Set list in tableview");

                        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStart"));
                        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
                        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
                        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
                        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
                        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
                        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedEnd"));

                        System.out.println("Set Up Appointments TableView");

                } catch (Exception e) {
                        System.out.println("Exception Occurred");
                        e.printStackTrace();
                }

        }
        @FXML
        private void OnAllRadio() {
                loadAppointmentsTable();
        }

        @FXML
        private void OnMonthlyRadio() {

                LocalDateTime end = dateStart.plusMonths(timeCounter);
                System.out.println("end is: " + end);
                LocalDateTime start = dateStart.plusMonths(timeCounter - 1);
                filterApptsBetween(start, end);
                System.out.println("filtered appts is: " + filteredList);
                reloadAppointmentsTable(filteredList);
        }

        @FXML
        private void OnWeeklyRadio() {

                LocalDateTime end = dateStart.plusWeeks(timeCounter);
                System.out.println("end is: " + end);
                LocalDateTime start = dateStart.plusWeeks(timeCounter - 1);
                filterApptsBetween(start, end);
                System.out.println("filtered appts is: " + filteredList);
                reloadAppointmentsTable(filteredList);
        }

        @FXML
        private void OnApptNextButton(ActionEvent event) {

                timeCounter++;

                if (weeklyRadio.isSelected()) OnWeeklyRadio();
                if (monthlyRadio.isSelected()) OnMonthlyRadio();
        }

        @FXML
        private void OnApptPreviousButton(ActionEvent event) {

                timeCounter--;

                if (weeklyRadio.isSelected()) OnWeeklyRadio();
                if (monthlyRadio.isSelected()) OnMonthlyRadio();
        }

        private void filterApptsBetween(LocalDateTime filterStart, LocalDateTime filterEnd) {

                filteredList.clear();
                filteredList.addAll(apptList.stream()
                        .filter(dates -> dates.isAfter(filterStart) && dates.isBefore(filterEnd))
                        .collect(Collectors.toList()));

                System.out.println(filteredList);
        }

        @FXML
        void OnCustomersButton(ActionEvent event) throws IOException {

                CustomerController.loadCustomerScene(event);
        }


        @FXML
        void OnNewOrEditApptButton(ActionEvent event) throws IOException {

                AppointmentEditController.loadAddEditAppt(event);
        }


        @FXML
        void OnReportsButton(ActionEvent event) {

                try {
                        ReportsController.loadReportsScene(event);

                } catch (IOException e) {

                        e.printStackTrace();
                }
        }


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

        @FXML
        void OnAppExitButton(ActionEvent event) {
                ControllerUtilities.closeApp(event);
        }

    }

