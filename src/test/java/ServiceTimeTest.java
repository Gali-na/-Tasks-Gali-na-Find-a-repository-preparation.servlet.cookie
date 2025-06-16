import org.junit.jupiter.api.Test;

import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTimeTest {
    private  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void getTimeUTC() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime timeUTC = ZonedDateTime.now( ZoneId.of("UTC+00:00"));
        String rezut = timeUTC.format(dateTimeFormatter) +" UTC";
        ServiceTime.getTimeUTC();
        assertEquals(ServiceTime.getTimeUTC(),rezut);
    }

    @Test
    void getTimeByZoneId_SetNullZoneId_GetWrongNotification() {
        assertEquals("there is no such time zone"+ " null",ServiceTime.getTimeByZoneId(null));

    }

    @Test
    void getTimeByZoneId_SetEmptyZoneId_GetWrongNotification() {
        assertEquals("there is no such time zone"+ " ",ServiceTime.getTimeByZoneId(""));

    }

    @Test
    void getTimeByZoneId_SetExistedZoneId_GetTimeByZoneId() {
        ZoneOffset zoneOffset = ZoneOffset.of("+00:00");
        ZoneId zoneId = ZoneId.of(zoneOffset.getId());
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        String format = zonedDateTime.format(dateTimeFormatter) +" UTC+00:00";
        System.out.println(format);
        assertEquals(format,ServiceTime.getTimeByZoneId("UTC+00:00"));

    }

    @Test
    void checkZoneIdExist_SetNullZoneId_GetFlse() {
        assertFalse(ServiceTime.checkZoneIdExist(null));
    }

    @Test
    void checkZoneIdExist_SetEmptyString_GetFlse() {
        assertFalse(ServiceTime.checkZoneIdExist(""));
    }
    @Test
    void checkZoneIdExist_SetExistedZoneId_GetTrue() {
        assertTrue(ServiceTime.checkZoneIdExist("UTC−06:00"));
    }

    @Test
    void checkZoneIdExist_SetNullNotExistedZoneId_GetFlse() {
        assertFalse(ServiceTime.checkZoneIdExist("UTC−26:00"));
    }


}