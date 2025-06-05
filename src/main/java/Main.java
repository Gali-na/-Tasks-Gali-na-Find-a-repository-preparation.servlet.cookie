

import java.io.*;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        final String[] availableIDs = TimeZone.getAvailableIDs();
        Arrays.stream(availableIDs).forEach(x-> System.out.println(x));

        /*1 прочитати парамерт

 -  відсутній
        * перевіряємо куки
              + куки маютьпояс -> виводимо на екран
              - куки не мають пояса -> виводимо ЮТС

+ присутній :
     +валідний -> повертаємо час згіно поясу
                           -> записуємо пояс в куки
     - не валідний ->  передано невалідний пояс
                                 ->  ящо куки мають пояс  видаляємо пояс*/
    }
}
