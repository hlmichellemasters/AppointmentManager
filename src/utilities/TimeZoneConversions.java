/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * utility class for converting time zones
 */
package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.*;

/**
 * provides time zone conversion methods to the application
 */
public class TimeZoneConversions {

    /**
     * converts a LocalDateTime object that is in UTC zone into a LocalDateTime object based on user's ZoneID
     * @param datetime the LocalDateTime object to convert to user's zone
     * @return the LocalDateTime that has been converted into the user's timezone
     */
    public static LocalDateTime toRealLocalDateTime(LocalDateTime datetime) {

        ZonedDateTime utcZoneDateTime = datetime.atZone(ZoneId.of("UTC"));

        ZonedDateTime localZoneDateTime = utcZoneDateTime.withZoneSameInstant(ZoneId.systemDefault());

        LocalDateTime realLocalDateTime = localZoneDateTime.toLocalDateTime();

        return realLocalDateTime;
    }


    /**
     * converts a user zoned LocalDateTime back into UTC time to be loaded into database
     * @param datetime is the user zoned LocalDateTime
     * @return LocalDateTime of UTC zone
     */
    public static LocalDateTime toUTCTime(LocalDateTime datetime) {

        ZonedDateTime zonedLocalDateTime = datetime.atZone(ZoneId.systemDefault());
        LocalDateTime utcDateTime = zonedLocalDateTime.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        return utcDateTime;
    }


    /**
     * generates a list of EST Business times converted to the user's zoned datetime
     * Business time is 8 AM - 10 PM EST, including weekends  (14 hours, divided into quarters = 56 hours)
     * Not allowing a start time to be after 9:45 PM EST as 15 min is smallest appt time and business closes at 10 PM
     *
     * @return availableBusinessTimes
     */
    public static ObservableList<LocalTime> getBusinessTimes(int hours, int minutes, int numHoursOpen) {

        ObservableList<LocalTime> availableBusinessTimes = FXCollections.observableArrayList();

        int quarterHourIterations = numHoursOpen * 4;

        LocalTime openTimeEST = LocalTime.of(hours, minutes);
        ZonedDateTime zonedDateTimeEST = ZonedDateTime.of(LocalDate.now(), openTimeEST, ZoneId.of("America/New_York"));

        ZonedDateTime zonedTimeLocal = zonedDateTimeEST.withZoneSameInstant(ZoneId.systemDefault());

        LocalTime openTimeLocal = zonedTimeLocal.toLocalTime();


        for (int i = 0; i < quarterHourIterations; i++) {

            availableBusinessTimes.add(openTimeLocal);
            openTimeLocal = openTimeLocal.plusMinutes(15);
        }

        return availableBusinessTimes;
    }
}
