READ ME for Appointment Manager

Purpose: Graphical User Interface application for creating, managing, and analyzing appointments for a software company

Author: H. L. Michelle Masters, Hmaste4@wgu.edu
Version: 3.001
Date: 6/2/21

IDE: IntelliJ IDEA Community Edition 2020.3.2
JDK: Amazon Corretto version 11.0.10
JavaFX: JavaFX-SDK-11.0.2
MySQL Connector: mysql-connector-java-8.0.23


Directions to run the program:


Beginning

Execute the application to begin and a Welcome screen will appear for logging in.
Enter your log in username and password.
You will see your Zone ID appear below your credentials.
Then press the Log In button.

Upon successful log in, you will receive a notification if there is an appointment either in progress or starting within
the next 15 minutes.
Press the ok button to clear the notification or the red exit button on the top left of the dialog box.

The Main appointment screen will be displayed at that point.  It will display by default all of the currently scheduled
appointments in order of earliest date to latest.  You can re-order and re-size the column by clicking on or dragging
the columns, respectively.



Filtering by Weekly or Monthly

To filter these appointments select the “Weekly” or “Monthly” radio button at the top of the screen.
This will by default give you the upcoming week or upcoming month.
To see the previous week or month select the “Previous” or “Next” buttons which are to the right of the radio buttons.

To reset the filter at any time select the “All” radio button.



Add, Edit, or Delete Appointments

To add, edit, or delete appointments, select the “Add or Edit Appointment” button on the top left of the
Main appointment screen. This will load the Add or Edit Appointment screen.
By default, this table will also display the appointments from earliest to latest.
You can again re-order and re-size the column by clicking on or dragging the columns, respectively.

To add an appointment, fill-in the textboxes and make the combo box selections on the right-side of the screen,
then press “Save”.  By default, you will only be allowed to make time selections that fit within the Business hours EST.
If the customer you wish to use for the appointment is not yet available, please see section “Adding or Editing Customers”.

To edit an appointment, select an appointment on the table (highlighting it), then press the “Edit Appointment” button.
This will load that appointment’s information into the right side of the edit screen where you can make any changes you
need, and then can press “Save”.

To delete an appointment, select an appointment on the table (highlighting it), then press the “Delete Appointment” button.
You will receive a dialog box to confirm that you wish to delete the appointment, and if you confirm that appointment
will be deleted.

You can press the “Clear” button at any point to delete any information in the edit form and not save any edits or additions.

Appointment IDs are automatically generated, and you are not able to edit this field.
The rest of the fields are editable and required, but do not need any required format.

The application does not allow you to schedule an appointment for a customer that overlaps with another appointment
from that same customer.  You will get an error message with instructions to reschedule if that is attempted.

The application also does not allow an appointment to end at a time earlier than it began.
You will receive an error message with directions if that is attempted as well.

Press the “Exit” button in order to return to the Main Appointment screen.




Add, Edit, or Delete Customers

To add, edit or delete customer select the “Customers” button on the bottom left of the Main Appointment screen.
This will load the Customers screen.
By default, this table will also display the customers from earliest added into the system to latest.
You can again re-order and re-size the column by clicking on or dragging the columns, respectively.

To add a new customer, fill-in the textboxes and make the combo box selections on the right-side of the screen,
then press “Save”.
The Division combo box will appear once a selection for the Country combo box is made.

To edit a customer, select a customer on the table (highlighting it), then press the “Edit Customer” button.
This will load that customer’s information into the right side of the edit screen where you can make any changes you need,
and then can press “Save”.

To delete a customer, select an appointment on the table (highlighting it), then press the “Delete Customer” button.
You will receive a dialog box to confirm that you wish to delete the customer as well as all of their appointments,
and if you confirm that appointment will be deleted.

You can press the “Clear” button at any point to delete any information in the edit form and not save any edits or additions.

Customer IDs are automatically generated, and you are not able to edit this field.
The rest of the fields are editable and required, but do not need any required format.



Reports

There are three reports provided in separate tabs on the Report screen.
To see them press the “Reports” button on the bottom middle of the Main Appointment screen.

Customer Appointments by Type and Month is the first report you see.
Here, you can view the number of appointments in each month in the appointment calendar segregated by the type of meeting.
This has been represented with a stacked bar chart.
There is a key at the bottom that shows which color represent which type of meeting.

Contact Appointment Schedule is accessed by clicking “Contact Appt Schedule” tab at the top left of the screen
(just to the right of “Customer Appts By Type and Month”).
By default, there is a table of all of the appointments.  To view the appointments by the contact,
select a contact from the contact combo box at the top middle of the screen.
You will then only see appointments for that contact.
By default, this table will also display the appointments from earliest to latest.
You can again re-order and re-size the column by clicking on or dragging the columns, respectively.

Contact Productivity (Additional Report from A3f) is accessed by clicking the “Contact Productivity” tab at the
top middle of the screen (just to the right of “Contact Appt Schedule”.
To view a productivity pie chart for a contact, select a contact from the contact combo box.
This report uses the number of months currently scheduled in the Appointment Calendar and the average number of hours
spent per month in appointments for the contact.
It then displays the percentage of work hours that the contact is scheduled for appointments in a pie chart.
This allows for a quick display of the amount of time that the contact’s hours are utilized in appointments.
If you hover your mouse over one of the slices of the pie chart you will see a tool tip pop up with a rounded percentage
for a more exact measurement.

Exiting the Report screen is done by pressing the “Exit” button found at the bottom right of all three of the Report tabs.



End

Exit or end the application at any time by navigating back to the Main Appointment screen and
pressing the “Exit Application” button on the bottom right of the screen, or by pressing the red “x” at the top left
of the window at any time.
