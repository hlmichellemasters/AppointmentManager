package utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

    public static LocalDateTime toBusinessTime
}
