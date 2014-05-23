import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateBuilderTest {

    @Test
    public void testResolveDay() throws Exception {

        DateBuilder db = new DateBuilder();
        for (int i = 0; i <= 99; i++) {
            System.out.println(db.resolveDay(String.valueOf(i)));

        }

    }

    @Test
    public void testResolveMonth() throws Exception {

        DateBuilder db = new DateBuilder();
        for (int i = 0; i <= 12; i++) {
            System.out.println(db.resolveMonth(String.valueOf(i)));

        }
    }

    @Test
    public void testResolveYear() throws Exception {

        DateBuilder db = new DateBuilder();
        for (int i = 1800; i <= 2010; i += 10) {
            System.out.println(db.resolveYear(String.valueOf(i)));
        }
    }

    @Test
    public void testResolveDate() {
        DateBuilder db = new DateBuilder();
        LocalDate date = LocalDate.parse("2001-12-29", DateTimeFormatter.ISO_DATE);
        System.out.println(db.resolveDate(date));
    }


}