/**
 * Sample Skeleton for 'Customer.fxml' Controller Class
 */

package controller;

import databaseAccess.DbCustomers;
import databaseAccess.DbLocations;
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
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerController {

        @FXML // fx:id="EditCustomerButton"
        private Button EditCustomerButton; // Value injected by FXMLLoader

        @FXML // fx:id="DeleteCustomerButton"
        private Button DeleteCustomerButton; // Value injected by FXMLLoader

        @FXML // fx:id="customerTableView"
        private TableView<Customer> customerTableView; // Value injected by FXMLLoader

        @FXML // fx:id="customerIDColumn"
        private TableColumn<Customer, Integer> customerIDColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerNameColumn"
        private TableColumn<Customer, String> customerNameColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerPhoneNumberColumn"
        private TableColumn<Customer, String> customerPhoneNumberColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerAddressColumn"
        private TableColumn<Customer, String> customerAddressColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerPostalCodeColumn"
        private TableColumn<Customer, String> customerPostalCodeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerCountryColumn"
        private TableColumn<Customer, String> customerCountryColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerFirstLevelDivisionColumn"
        private TableColumn<Customer, Integer> customerFirstLevelDivisionColumn; // Value injected by FXMLLoader

        @FXML // fx:id="customerDivisionLabel"
        private Label customerDivisionLabel; // Value injected by FXMLLoader

        @FXML // fx:id="customerIDAutoGeneratedText"
        private Label customerIDAutoGeneratedText; // Value injected by FXMLLoader

        @FXML // fx:id="customerNameText"
        private TextField customerNameText; // Value injected by FXMLLoader

        @FXML // fx:id="customerPhoneNumberText"
        private TextField customerPhoneNumberText; // Value injected by FXMLLoader

        @FXML // fx:id="customerAddressText"
        private TextField customerAddressText; // Value injected by FXMLLoader

        @FXML // fx:id="customerPostalCodeText"
        private TextField customerPostalCodeText; // Value injected by FXMLLoader

        @FXML // fx:id="customerCountryCombo"
        private ComboBox<Country> customerCountryCombo; // Value injected by FXMLLoader

        @FXML // fx:id="customerDivisionCombo"
        private ComboBox<Division>  customerDivisionCombo; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerSaveButton"
        private Button CustomerSaveButton; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerClearButton"
        private Button CustomerClearButton; // Value injected by FXMLLoader

        @FXML // fx:id="ExitCustomerScreenButton"
        private Button ExitCustomerScreenButton; // Value injected by FXMLLoader


        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        ObservableList<Division> divisionList = FXCollections.observableArrayList();

        public void loadCustomersTable() {

                try {

                customerList.addAll(Customer.getAllCustomers());
                System.out.println("Got all Customers from Database");

                for (Customer customer: customerList) {
                        System.out.println(customer);
                }

                customerTableView.setItems(customerList);
                System.out.println("Set list in tableview");

                customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
                customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                customerPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
                customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
                customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
                customerCountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
                customerFirstLevelDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));

                System.out.println("Set Up Customers TableView");

                } catch (Exception e) {
                        System.out.println("Exception Occurred");
                        e.printStackTrace();
                }

        }

        public void initializeAddEditCustomer() {
                try {
                        customerIDAutoGeneratedText.setText(String.valueOf(DbCustomers.getNextCustomerID()));
                        countryList.addAll(DbLocations.getAllCountries());
                        customerCountryCombo.setItems(countryList);

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        void OnCustomerClearButton(ActionEvent event) {
//TODO add more checks for new or editting when clear button pressed
                customerIDAutoGeneratedText.setText(String.valueOf(DbCustomers.getNextCustomerID()));
                customerNameText.clear();
                customerPhoneNumberText.clear();
                customerAddressText.clear();
                customerPostalCodeText.clear();
                customerCountryCombo.getSelectionModel().clearSelection();
                customerCountryCombo.setValue(null);
                customerDivisionCombo.getSelectionModel().clearSelection();
                customerDivisionLabel.setVisible(false);
                customerDivisionCombo.setVisible(false);
                customerTableView.getSelectionModel().clearSelection();

        }

        @FXML
        void OnCustomerCountryCombo(ActionEvent event) {

                Country selectedCountry;
                System.out.println("Changed Country Combo");

                try {
                        selectedCountry = customerCountryCombo.getSelectionModel().getSelectedItem();

                        if (selectedCountry != null) {
                                divisionList.clear();
                                // clearing division list so that only divisions from current country show
                                customerDivisionCombo.getSelectionModel().clearSelection();
                                customerDivisionCombo.setValue(null);
                                // clearing current division;

                                divisionList = DbLocations.getDivisions(selectedCountry);
                                customerDivisionCombo.setItems(divisionList);
                                customerDivisionLabel.setVisible(true);
                                customerDivisionCombo.setVisible(true);
                                System.out.println("Current selected division is "
                                        + customerDivisionCombo.getSelectionModel().getSelectedItem());
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }



        }

        @FXML
        void OnCustomerSaveButton(ActionEvent event) throws Exception {

                int customerID = Integer.valueOf(customerIDAutoGeneratedText.getText());
                String customerName = customerNameText.getText();
                String customerPhoneNum = customerPhoneNumberText.getText();
                String customerAddress = customerAddressText.getText();
                String customerPostal = customerPostalCodeText.getText();
                int customerDivisionID = customerDivisionCombo.getSelectionModel().getSelectedItem().getDivisionID();

                DbCustomers.saveCustomertoDB(customerID, customerName, customerPhoneNum, customerAddress,
                        customerPostal, customerDivisionID);

                customerTableView.setItems(Customer.getAllCustomers());

        }

        @FXML
        void OnDeleteCustomerButton(ActionEvent event) throws Exception {

                if (customerTableView.getSelectionModel().getSelectedItem() != null) {
                        DbCustomers.RemoveCustomer(customerTableView.getSelectionModel().getSelectedItem());

                        customerTableView.setItems(Customer.getAllCustomers());
                }

        }

        @FXML
        void OnEditCustomerButton(ActionEvent event) {
                Customer customer = customerTableView.getSelectionModel().getSelectedItem();
                customerIDAutoGeneratedText.setText(String.valueOf(customer.getCustomerID()));
                customerNameText.setText(customer.getName());
                customerPhoneNumberText.setText(customer.getPhoneNum());
                customerAddressText.setText(customer.getAddress());
                customerPostalCodeText.setText(customer.getPostalCode());
                customerCountryCombo.setValue(customer.getCountry());
                customerDivisionCombo.setValue(customer.getDivision());
                System.out.println("current Division combo selection is " + customerDivisionCombo.getSelectionModel().getSelectedItem());
        }

        @FXML
        void OnExitCustomerScreenButton(ActionEvent event) throws IOException {

                MainAppointmentController.loadMain(event);

        }

        @FXML
        void OnSortCustomerTableView(ActionEvent event) {

        }

        public static void loadCustomerScene(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(CustomerController.class.getResource("/view/Customer.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                CustomerController controller = loader.getController();
                controller.loadCustomersTable();
                controller.initializeAddEditCustomer();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                ControllerUtilities.centerStage(stage, 1283, 774);
                stage.setTitle("Customers");
                stage.setScene(scene);
                stage.show();
        }

    }

