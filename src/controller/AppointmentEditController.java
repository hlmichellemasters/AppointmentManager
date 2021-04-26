/**
 * Sample Skeleton for 'AppointmentEditScreen.fxml' Controller Class
 */


package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AppointmentEditController {

        @FXML // fx:id="EditApptButton"
        private Button EditApptButton; // Value injected by FXMLLoader

        @FXML // fx:id="DeleteApptButton"
        private Button DeleteApptButton; // Value injected by FXMLLoader

        @FXML // fx:id="ApptEditTableView"
        private TableView<?> ApptEditTableView; // Value injected by FXMLLoader

        @FXML // fx:id="ApptIDColumn"
        private TableColumn<?, ?> ApptIDColumn; // Value injected by FXMLLoader

        @FXML // fx:id="DescriptionColumn"
        private TableColumn<?, ?> DescriptionColumn; // Value injected by FXMLLoader

        @FXML // fx:id="TypeColumn"
        private TableColumn<?, ?> TypeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="LocationColumn"
        private TableColumn<?, ?> LocationColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactColumn"
        private TableColumn<?, ?> ContactColumn; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerColumn"
        private TableColumn<?, ?> CustomerColumn; // Value injected by FXMLLoader

        @FXML // fx:id="StartDateTimeColumn"
        private TableColumn<?, ?> StartDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="EndDateTimeColumn"
        private TableColumn<?, ?> EndDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ApptIDText"
        private Label ApptIDText; // Value injected by FXMLLoader

        @FXML // fx:id="ApptDescriptionText"
        private TextField ApptDescriptionText; // Value injected by FXMLLoader

        @FXML // fx:id="ApptTypeText"
        private TextField ApptTypeText; // Value injected by FXMLLoader

        @FXML // fx:id="ApptLocationText"
        private TextField ApptLocationText; // Value injected by FXMLLoader

        @FXML // fx:id="ApptCustomerCombo"
        private ComboBox<?> ApptCustomerCombo; // Value injected by FXMLLoader

        @FXML // fx:id="ApptStartTimeCombo"
        private ComboBox<?> ApptStartTimeCombo; // Value injected by FXMLLoader

        @FXML // fx:id="ApptEndTimeCombo"
        private ComboBox<?> ApptEndTimeCombo; // Value injected by FXMLLoader

        @FXML // fx:id="ApptContactCombo"
        private ComboBox<?> ApptContactCombo; // Value injected by FXMLLoader

        @FXML // fx:id="ApptUserCombo"
        private ComboBox<?> ApptUserCombo; // Value injected by FXMLLoader

        @FXML // fx:id="ApptStartDatePicker"
        private DatePicker ApptStartDatePicker; // Value injected by FXMLLoader

        @FXML // fx:id="ApptEndDatePicker"
        private DatePicker ApptEndDatePicker; // Value injected by FXMLLoader

        @FXML // fx:id="ApptSaveButon"
        private Button ApptSaveButon; // Value injected by FXMLLoader

        @FXML // fx:id="ApptClearButton"
        private Button ApptClearButton; // Value injected by FXMLLoader

        @FXML // fx:id="ApptEditScreenExit"
        private Button ApptEditScreenExit; // Value injected by FXMLLoader

        @FXML
        void OnApptClearButton(ActionEvent event) {

        }

        @FXML
        void OnApptContactCombo(ActionEvent event) {

        }

        @FXML
        void OnApptCustomerCombo(ActionEvent event) {

        }

        @FXML
        void OnApptEditScreenExit(ActionEvent event) {

        }

        @FXML
        void OnApptEndDatePicker(ActionEvent event) {

        }

        @FXML
        void OnApptEndTimeCombo(ActionEvent event) {

        }

        @FXML
        void OnApptSaveButton(ActionEvent event) {

        }

        @FXML
        void OnApptStartDatePicker(ActionEvent event) {

        }

        @FXML
        void OnApptStartTimeCombo(ActionEvent event) {

        }

        @FXML
        void OnApptUserCombo(ActionEvent event) {

        }

        @FXML
        void OnDeleteApptButton(ActionEvent event) {

        }

        @FXML
        void OnSortApptEditTableView(ActionEvent event) {

        }

    }

