import java.io.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServiceTime {
   private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String getTimeUTC () {
        ZonedDateTime timeUTC = ZonedDateTime.now( ZoneId.of("UTC+00:00"));
        final String format = timeUTC.format(dateTimeFormatter);
        return format +" UTC";
    }
    public static String getTimeByZoneId (String zoneId) {
        return  getTime(zoneId) + " "+zoneId;

    }
    private static String  getTime(String zoneId){
        ZonedDateTime timeUTC = ZonedDateTime.now( ZoneId.of(zoneId));
        final String format = timeUTC.format(dateTimeFormatter);
        return format;
    }
    public static  boolean checkZoneIdExist(String zoneId){
        List<String> listzoneIdExisted = loadExistingZoneId();
        return listzoneIdExisted.contains(zoneId);
    }
    private static List<String>loadExistingZoneId () {
        List<String> zoneIdExisted = new ArrayList<>();
        try (BufferedReader jreader = new BufferedReader(new InputStreamReader(
                ServiceTime.class.getClassLoader().getResourceAsStream("zoneId.txt")))) {
            String line;
            while ((line = jreader.readLine()) != null) {
                zoneIdExisted.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return zoneIdExisted;
    }
}
