/**
 * Sample Skeleton for 'Reports.fxml' Controller Class
 */

package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReportsController {
        
        // Customer Appts By Type and MonthReport

        @FXML // fx:id="reportsApptTypeMonthTab"
        private Tab reportsApptTypeMonthTab; // Value injected by FXMLLoader

        @FXML // fx:id="reportApptsByTypeMonthChart"
        private StackedBarChart<String, Integer> reportApptsByTypeMonthChart; // Value injected by FXMLLoader

        @FXML // fx:id="reportsApptTypeMonthExitButton"
        private Button reportsApptTypeMonthExitButton; // Value injected by FXMLLoader

        
        // Contact Report

        @FXML // fx:id="contactScheduleTab"
        private Tab contactScheduleTab; // Value injected by FXMLLoader

        @FXML // fx:id="reportContactScheduleCombo"
        private ComboBox<Contact> reportContactScheduleCombo; // Value injected by FXMLLoader

        @FXML // fx:id="reportsContactScheduleTableView"
        private TableView<Appointment> reportContactScheduleTableView; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleContactColumn"
        private TableColumn<Appointment, String> contactScheduleContactColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleTitleColumn"
        private TableColumn<Appointment, String> contactScheduleTitleColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleTypeColumn"
        private TableColumn<Appointment, String> contactScheduleTypeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleDescriptionColumn"
        private TableColumn<Appointment, String> contactScheduleDescriptionColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleLocationColumn"
        private TableColumn<Appointment, String> contactScheduleLocationColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleApptIDColumn"
        private TableColumn<Appointment, Integer> contactScheduleApptIDColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleStartDateTimeColumn"
        private TableColumn<Appointment, String> contactScheduleStartDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleEndDateTimeColumn"
        private TableColumn<Appointment, String> contactScheduleEndDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="contactScheduleCustomerColumn"
        private TableColumn<Appointment, String> contactScheduleCustomerColumn; // Value injected by FXMLLoader

        @FXML // fx:id="reportScheduleExitButton"
        private Button reportsScheduleExitButton; // Value injected by FXMLLoader



        // Productivity Report

        @FXML // fx:id="reportContactProductivityCombo"
        private ComboBox<Contact> reportContactProductivityCombo; // Value injected by FXMLLoader

        @FXML // fx:id="reportContactProductivityChart"
        private PieChart reportContactProductivityChart; // Value injected by FXMLLoader

        @FXML // fx:id="reportsProductivityExitButton"
        private Button reportsProductivityExitButton; // Value injected by FXMLLoader



        ObservableList<Appointment> contactApptList = FXCollections.observableArrayList();
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();



        public static void loadReportsScene(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(CustomerController.class.getResource("/view/Reports.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                ReportsController controller = loader.getController();
                controller.loadApptByTypeMonthChart();
                controller.loadContactScheduleTable();
                controller.initializeReportContactCombos();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                ControllerUtilities.centerStage(stage, 1283, 774);
                stage.setTitle("Reports");
                stage.setScene(scene);
                stage.show();
        }



        public void initializeReportContactCombos() {

                try {
                        contactList.addAll(Contact.getAllContacts());
                        reportContactScheduleCombo.setItems(contactList);
                        reportContactProductivityCombo.setItems(contactList);

                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }

  /*
                Customer Report event handlers
         */
        /**
         * creates a stacked bar chart of the appointments by month (stacked by type)
         * instructions followed from https://www.youtube.com/watch?v=hRk_zrng4VY
         */
        private void loadApptByTypeMonthChart() {
                try {
                        // get all appointments

                        appointments.addAll(Appointment.getAllAppointments());

                        // create a list and gather all unique types
                        ArrayList<String> typeList = new ArrayList<>();

                        for (Appointment appt: appointments) {
                                typeList.add(appt.getType());
                        }

                        List<String> distinctTypes = typeList.stream().distinct().collect(Collectors.toList());
                        System.out.println("The unique types are: " + distinctTypes);

                        // loop through all unique types
                        for (int i = 0; i < distinctTypes.size(); i++) {

                                // create a series for each unique type
                                String seriesName = distinctTypes.get(i);
                                XYChart.Series<String, Integer> series = new XYChart.Series<>();

                                // count the number of appointments for each type
                                int counter = 1;

                                // Add the data to the series object
                                for (Appointment appt: appointments) {

                                        if (appt.getType().equals(seriesName)) {
                                                series.getData().add(new XYChart.Data<>(appt.getMonthYear(), counter));
                                        }
                                }

                                // Set series name
                                series.setName(seriesName);

                                // Add the series to the Stacked Bar Chart object
                                reportApptsByTypeMonthChart.getData().add(series);
                        }

                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }

        @FXML
        void OnCustomerReportExitButton(ActionEvent event) throws IOException {
                OnReportExitButton(event);
        }



        /*
                Contact Report event handlers
         */

        public void loadContactScheduleTable() {

                try {
                        contactApptList.addAll(Appointment.getAllAppointments());
                        System.out.println("Got all Appointments from Database");

                        for (Appointment appt : contactApptList) {
                                System.out.println(appt);
                        }

                        reportContactScheduleTableView.setItems(contactApptList);
                        System.out.println("Set list in tableview");

                        contactScheduleContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
                        contactScheduleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
                        contactScheduleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                        contactScheduleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
                        contactScheduleLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
                        contactScheduleApptIDColumn.setCellValueFactory(new PropertyValueFactory<>("apptID"));
                        contactScheduleStartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStart"));
                        contactScheduleEndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedEnd"));
                        contactScheduleCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));

                        System.out.println("Set Up Contact Schedule Report TableView");

                } catch (Exception e) {
                        System.out.println("Exception Occurred");
                        e.printStackTrace();
                }
        }

        /**
         * when a contact is selected the schedule tableview of appointments is filtered for only that contact
         * @param actionEvent
         */
        public void OnContactScheduleSelection(ActionEvent actionEvent) {
                ObservableList<Appointment> filteredApptsByContact;
                filteredApptsByContact = Appointment.filterApptByContact(reportContactScheduleCombo.getSelectionModel().getSelectedItem());
                reportContactScheduleTableView.setItems(filteredApptsByContact);
        }

        @FXML
        void OnContactScheduleExitButton(ActionEvent event) throws IOException {
                OnReportExitButton(event);
        }

      

        /*
                Productivity Report event handlers
         */

        public void OnContactProductivitySelection(ActionEvent actionEvent) {
                Appointment.filterApptByContact(reportContactScheduleCombo.getSelectionModel().getSelectedItem());
                // TODO refresh chart
        }


        @FXML
        void OnProductivityReportExitButton(ActionEvent event) throws IOException {
                OnReportExitButton(event);
        }



        /*
                Exit buttons for Reports screen
         */

        void OnReportExitButton(ActionEvent event) throws IOException {

                MainAppointmentController.loadMain(event);
        }


}
