/**
 * 'MainAppointment.fxml' Controller Class
 */

package controller;

import javafx.application.Platform;
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

public class MainAppointmentController {

        @FXML // fx:id="NewAppointmentButton"
        private Button NewAppointmentButton; // Value injected by FXMLLoader

        @FXML // fx:id="WeeklyRadio"
        private RadioButton WeeklyRadio; // Value injected by FXMLLoader

        @FXML // fx:id="ApptWeeklyOrMonthly"
        private ToggleGroup ApptWeeklyOrMonthly; // Value injected by FXMLLoader

        @FXML // fx:id="MonthlyRadio"
        private RadioButton MonthlyRadio; // Value injected by FXMLLoader

        @FXML // fx:id="AllRadio"
        private RadioButton AllRadio; // Value injected by FXMLLoader

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

        ObservableList<Appointment> apptList = FXCollections.observableArrayList();

        public void loadAppointmentsTable() {

                try {

                        apptList.addAll(AppointmentCalendar.provideApptList());
                        System.out.println("Got all Appointments from Database");

//                        for (Appointment appt: apptList) {
//                                System.out.println(appt);
//                        }

                        apptTableView.setItems(apptList);
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
        void OnAllRadio(ActionEvent event) {

        }

        @FXML
        void OnAppExitButton(ActionEvent event) {

        }

        @FXML
        void OnApptNextButton(ActionEvent event) {

        }

        @FXML
        void OnApptPreviousButton(ActionEvent event) {

        }

        @FXML
        void OnCustomersButton(ActionEvent event) throws IOException {

                CustomerController.loadCustomerScene(event);
        }

        @FXML
        void OnMonthlyRadio(ActionEvent event) {

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

        @FXML
        void OnSortApptMainTableView(ActionEvent event) {

        }

        @FXML
        void OnWeeklyRadio(ActionEvent event) {

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

        public static void closeApp(ActionEvent event) {

                Platform.exit();
        }


    }

