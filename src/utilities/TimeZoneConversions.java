package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.*;

public class TimeZoneConversions {

    public static LocalDateTime toRealLocalDateTime(LocalDateTime datetime) {

        System.out.println("Time passed in was: " + datetime);
        ZonedDateTime utcZoneDateTime = datetime.atZone(ZoneId.of("UTC"));
        System.out.println("Original UTC DateTime was: " + utcZoneDateTime);
        ZonedDateTime localZoneDateTime = utcZoneDateTime.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("Converted to LocalDateTime was: " + localZoneDateTime);
        LocalDateTime realLocalDateTime = localZoneDateTime.toLocalDateTime();
        System.out.println("Making the real localdatetime be: " + realLocalDateTime);
        return realLocalDateTime;
    }

    public static ZonedDateTime toBusinessTime(ZonedDateTime datetime) {

        ZonedDateTime businessDateTime = datetime.withZoneSameInstant(ZoneId.of("America/New_York"));
        return businessDateTime;
    }


    /**
     * generates a list of EST Business times converted to the user's zoned datetime
     * Business time is 8 AM - 10 PM EST, including weekends  (14 hours, divided into quarters = 56 hours)
     * Not allowing a start time to be after 9:45 PM EST as 15 min is smallest appt time and business closes at 10 PM
     * @return availableBusinessTimes
     */
    public static ObservableList<LocalTime> getAvailableBusinessTimes() {

        ObservableList<LocalTime> availableBusinessTimes = FXCollections.observableArrayList();

        LocalTime openTimeEST = LocalTime.of(8, 0);
        ZonedDateTime zonedDateTimeEST = ZonedDateTime.of(LocalDate.now(), openTimeEST, ZoneId.of("America/New_York"));
        System.out.println("8am to zoned: " + zonedDateTimeEST);
        ZonedDateTime zonedTimeLocal = zonedDateTimeEST.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("8am EST To zoned local: " + zonedTimeLocal);
        LocalTime openTimeLocal = zonedTimeLocal.toLocalTime();
        System.out.println("8am EST to time only local: " + openTimeLocal);

        for (int i = 0; i < 56; i++) {
            availableBusinessTimes.add(openTimeLocal);
            System.out.println("Added the time: " + openTimeLocal);
            openTimeLocal = openTimeLocal.plusMinutes(15);
        }

        return availableBusinessTimes;
    }
}
