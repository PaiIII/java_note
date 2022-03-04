import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FunctionTest {
    public static String DateFormat(LocalDateTime dateTime, String partten) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(partten);
        return dateTime.format(dateTimeFormatter);
    }
}
