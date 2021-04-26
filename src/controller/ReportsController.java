/**
 * Sample Skeleton for 'Reports.fxml' Controller Class
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportsController {

        // Customer Report

        @FXML // fx:id="CustomerReportTab"
        private Tab CustomerReportTab; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerReportExitButton"
        private Button CustomerReportExitButton; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerReportTableView"
        private TableView<?> CustomerReportTableView; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerReportTypeColumn"
        private TableColumn<?, ?> CustomerReportTypeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerReportMonthColumn"
        private TableColumn<?, ?> CustomerReportMonthColumn; // Value injected by FXMLLoader

        @FXML // fx:id="CustomerReportTotalColumn"
        private TableColumn<?, ?> CustomerReportTotalColumn; // Value injected by FXMLLoader

        // Contact Report

        @FXML // fx:id="ContactReportTab"
        private Tab ContactReportTab; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportExitButton"
        private Button ContactReportExitButton; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportTableView"
        private TableView<?> ContactReportTableView; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportContactColumn"
        private TableColumn<?, ?> ContactReportContactColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportTitleColumn"
        private TableColumn<?, ?> ContactReportTitleColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportTypeColumn"
        private TableColumn<?, ?> ContactReportTypeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportDescriptionColumn"
        private TableColumn<?, ?> ContactReportDescriptionColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportLocationColumn"
        private TableColumn<?, ?> ContactReportLocationColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportApptIDColumn"
        private TableColumn<?, ?> ContactReportApptIDColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportStartDateTimeColumn"
        private TableColumn<?, ?> ContactReportStartDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportEndDateTimeColumn"
        private TableColumn<?, ?> ContactReportEndDateTimeColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ContactReportCustomerColumn"
        private TableColumn<?, ?> CustomerColumn; // Value injected by FXMLLoader

        // Productivity Report

        @FXML // fx:id="ProductivityReportTab"
        private Tab ProductivityReportTab; // Value injected by FXMLLoader

        @FXML // fx:id="ProductivityReportExitButton"
        private Button ProductivityReportExitButton; // Value injected by FXMLLoader

        @FXML // fx:id="ProductivityReportTableView"
        private TableView<?> ProductivityReportTableView; // Value injected by FXMLLoader

        @FXML // fx:id="ProductivityReportContactColumn"
        private TableColumn<?, ?> ProductivityReportContactColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ProductivityReportMonthColumn"
        private TableColumn<?, ?> ProductivityReportMonthColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ProductivityReportApptHoursColumn"
        private TableColumn<?, ?> ProductivityReportApptHoursColumn; // Value injected by FXMLLoader

        @FXML // fx:id="ProductivityReportPercentageColumn"
        private TableColumn<?, ?> ProductivityReportPercentageColumn; // Value injected by FXMLLoader

        /*
                Exit button for Reports screen
         */

        void OnReportExitButton(ActionEvent event) {

        }

        /*
                Contact Report event handlers
         */

        @FXML
        void OnContactReportExitButton(ActionEvent event) {

        }

        @FXML
        void OnContactReportTab(ActionEvent event) {

        }

        /*
                Customer Report event handlers
         */

        @FXML
        void OnCustomerReportExitButton(ActionEvent event) {

        }

        @FXML
        void OnCustomerReportTab(ActionEvent event) {

        }

        /*
                Productivity Report event handlers
         */

        @FXML
        void OnProductivityReportExitButton(ActionEvent event) {

        }

        @FXML
        void OnProductivityReportTab(ActionEvent event) {

        }

    }
