/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * Controller for 'Reports.fxml'
 */

package controller;

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
import model.AppointmentCalendar;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for 'Reports.fxml'
 */
public class ReportsController {

        /********************************* Appts By Type and Month FXML **********************************************/

        @FXML // fx:id="reportApptsByTypeMonthChart"
        private StackedBarChart<String, Integer> reportApptsByTypeMonthChart; // Value injected by FXMLLoader

        /********************************* Contact Schedule FXML *****************************************************/

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


        /*********************************Productivity FXML*****************************************************/

        @FXML // fx:id="reportContactProductivityCombo"
        private ComboBox<Contact> reportContactProductivityCombo; // Value injected by FXMLLoader

        @FXML // fx:id="reportContactProductivityChart"
        private PieChart reportContactProductivityChart; // Value injected by FXMLLoader

        /********************************* Lists *****************************************************************/

        private ObservableList<Appointment> contactApptList = FXCollections.observableArrayList();
        private ObservableList<Contact> contactList = FXCollections.observableArrayList();
        private ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        /**
         * loads the report scene including loading each report (chart, table and contact combos)
         * @param event passed from report button on main appointment scene
         * @throws IOException for any input-output issue
         */
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

        /**
         * loads main appointment scene when any of the report tabs exit buttons are pressed
         * @param event passed from the exit buttons of the reports scene
         * @throws IOException for any loading errors
         */
        void OnReportExitButton(ActionEvent event) throws IOException {

                MainAppointmentController.loadMain(event);
        }

        /**
         * initializes the report contact combo boxes by getting the contact list and setting it in the combos
         */
        public void initializeReportContactCombos() {

                try {
                        contactList = Contact.provideContactList();
                        reportContactScheduleCombo.setItems(contactList);
                        reportContactProductivityCombo.setItems(contactList);

                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }


/*********************************Customer Report event handlers*****************************************************/

        /**
         * loads a stacked bar chart of the appointments by month (stacked by type) and USES LAMBDA to add types to list
         * some inspiration from https://www.youtube.com/watch?v=hRk_zrng4VY
         * the forEach lambda expression simplifies the code for adding the appointment types into the type list
         * lambda help from https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
         */
        private void loadApptByTypeMonthChart() {
                try {
                        // get all appointments
                        appointments.addAll(AppointmentCalendar.provideApptList());

                        // create a set and gather all unique types using lambda
                        Set<String> typeSet = new HashSet<>(appointments.size());
                        appointments.stream().filter(appt -> typeSet.add(appt.getType())).collect(Collectors.toList());
                        Iterator<String> typeIterator = typeSet.iterator();

                        while(typeIterator.hasNext()) {

                                // create a series for each unique type
                                String seriesName = typeIterator.next();
                                XYChart.Series<String, Integer> series = new XYChart.Series<>();

                                // count the number of appointments for each type
                                int counter = 0;

                                // Add the data to the series object
                                for (Appointment appt: appointments) {

                                        if (appt.getType().equals(seriesName)) {
                                                counter++;
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

        /**
         * calls the exit report function (to main scene) when report exit button pressed
         * @param event passed to the exit report function
         * @throws IOException for any loading errors
         */
        @FXML
        void OnCustomerReportExitButton(ActionEvent event) throws IOException {
                OnReportExitButton(event);
        }

/********************************* Contact Schedule event handlers ***************************************************/

        /**
         * loads the contact schedule table by getting the appointment list (full list until contact selected)
         */
        public void loadContactScheduleTable() {

                try {
                        contactApptList = AppointmentCalendar.provideApptList();

                        reportContactScheduleTableView.setItems(contactApptList);

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
         */
        public void OnContactScheduleSelection() {
                ObservableList<Appointment> filteredApptsByContact;
                filteredApptsByContact = AppointmentCalendar.filterApptByContact(reportContactScheduleCombo.
                        getSelectionModel().getSelectedItem());
                reportContactScheduleTableView.setItems(filteredApptsByContact);
        }

        /**
         * calls the exit report function (to main scene) when report exit button pressed
         * @param event passed to the exit report function
         * @throws IOException for any loading errors
         */
        @FXML
        void OnContactScheduleExitButton(ActionEvent event) throws IOException {
                OnReportExitButton(event);
        }

/********************************* Contact Productivity event handlers ***********************************************/

        /**
         * calls loading the contact's productivity pie chart upon a user selecting a contact from the combo box
         */
        public void OnContactProductivitySelection(ActionEvent event) {

                loadContactProductivityPieChart(reportContactProductivityCombo.getSelectionModel().getSelectedItem(), event);
        }

        /**
         * loads the contact's productivity pie chart (CONTAINS 2 LAMBDAS)
         * calculates the number of month's in the appointment calendar and the hours of appointments the contact has
         * finds the average contact hours per month and subtracts from 160 hours (average hours per month) in order
         * to display the percentage of time the contact is in appointments vs doing other work
         * also displays a tool tip on the pie chart in order to see the exact percentages
         * forEach lambda simplifies the set-up of the tooltip to display the percentages on the piechart
         * stream.filter lambda adds each month-year to the month-year hash set in a more concise way than alternatives
         * @param contact
         * @param event
         */
        private void loadContactProductivityPieChart(Contact contact, ActionEvent event) {

                try {
                        // get all appointments
                        appointments = AppointmentCalendar.provideApptList();

                        // create a list and gather all unique types
                        double contactHours = 0;
                        int WORK_HOURS_PER_MONTH = 160;

                        for (Appointment appt: appointments) {
                                if (appt.getContact().equals(contact)) {
                                        // get hours from appointment and add to contactHours
                                        contactHours += appt.getHours();
                                }
                        }

                        // create a hashset of the distinct appointment month-years
                        Set<String> monthSet = new HashSet<>(appointments.size());
                        appointments.stream().filter(month -> monthSet.add(month.getMonthYear())).collect(Collectors.toList());

                        // calculate average contact hours / month by dividing total contact hours by size of month set
                        double averageContactHoursPerMonth = contactHours / monthSet.size();

                        // and subtract this from 160 to get number of hours spent doing other work
                        double averageContactHoursOtherStuff = WORK_HOURS_PER_MONTH - averageContactHoursPerMonth;

                        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Appointment", (averageContactHoursPerMonth * 100/ 160)),
                        new PieChart.Data("Other work", (averageContactHoursOtherStuff * 100/ 160)));

                        reportContactProductivityChart.setData(pieChartData);

                        reportContactProductivityChart.setTitle("" + contact + " Appointment Productivity");

                        reportContactProductivityChart.getData().forEach(data -> {
                                String percentage = String.format("%.2f%%", (data.getPieValue()));
                                Tooltip tooltip = new Tooltip(percentage);
                                Tooltip.install(data.getNode(), tooltip);
                        });

                } catch (Exception exception) {
                        exception.printStackTrace();
                }
        }

        /**
         * calls the exit report function (to main scene) when report exit button pressed
         * @param event passed to the exit report function
         * @throws IOException for any loading errors
         */
        @FXML
        void OnProductivityReportExitButton(ActionEvent event) throws IOException {
                OnReportExitButton(event);
        }
}
