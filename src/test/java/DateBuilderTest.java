import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateBuilderTest {

    @Test
    public void checkResolveDay() throws Exception {
        DateBuilder db = new DateBuilder();
        for (int i = 0; i <= 99; i++) {
            System.out.printf("%d: %s\n", i, db.resolveDay(String.valueOf(i)));
        }
    }

    @Test
    public void checkResolveMonth() throws Exception {
        DateBuilder db = new DateBuilder();
        for (int i = 0; i <= 12; i++) {
            System.out.printf("%d: %s\n", i, db.resolveMonth(String.valueOf(i)));
        }
    }

    @Test
    public void checkResolveYear() throws Exception {

        DateBuilder db = new DateBuilder();
        for (int i = 1990; i <= 2010; i += 1) {
            System.out.printf("%d: %s\n", i, db.resolveYear(String.valueOf(i)));
        }
    }

    @Test
    public void testResolveDate() {
        DateBuilder db = new DateBuilder();
        assertThat(db.resolveDate(date("2001-12-29")), is("dwudziestego dziewiątego grudnia dwa tysiące pierwszego roku"));
        assertThat(db.resolveDate(date("2000-12-29")), is("dwudziestego dziewiątego grudnia dwu tysięcznego roku"));
        assertThat(db.resolveDate(date("1900-12-29")), is("dwudziestego dziewiątego grudnia tysiąc dziewięćsetnego roku"));
        assertThat(db.resolveDate(date("2100-12-29")), is("dwudziestego dziewiątego grudnia dwa tysiące setnego roku"));
    }

    private static LocalDate date(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }


}