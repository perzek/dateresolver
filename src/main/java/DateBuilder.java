import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by perzek on 2014-05-23.
 */
//TODO fix years divisible by 100...
public class DateBuilder {

    private static final String unities[] = new String[]{"", "pierwszego", "drugiego", "trzeciego", "czwartego", "piątego", "szóstego", "siódmego", "ósmego", "dziewiątego", "dziesiątego", "jedenastego", "dwunastego", "trzynastego", "czternastego", "piętnastego", "szenastego", "siedemnastego", "osiemnastego", "dziewiętnastego"};
    private static final String tens[] = new String[]{"", "", "dwudziestego", "trzydziestego", "czterdziestego", "pięćdziesiątego", "sześćdziesiątego", "siedemdziesątego", "osiemdziesiątego", "dziwięćdziesiątego"};
    private static final String hundred[] = new String[]{"", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset", "osiemset", "dziewięćset"};
    private static final String thousands[] = new String[]{"", "tysiąc", "dwa tysiące", "trzy tysiące", "cztery tysiące", "pięć tysięcy", "sześć tysięcy", "siedem tysięcy", "osiem tysięcy", "dziewięć tysięcy"};

    private static final String months[] = new String[]{"", "stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "paźdzniernika", "listopada", "grudnia"};
    public static final char ZERO_VALUE = '0';

    public String resolveDate(LocalDate date) {
        return resolveDay(String.valueOf(date.getDayOfMonth())) + resolveMonth(String.valueOf(date.getMonthValue())) +
                resolveYear(String.valueOf(date.getYear())) + "roku";
    }

    public String resolveDay(String day) {
        char[] chars = addZeroIfTooShort(day.toCharArray(), 2);

        StringBuilder sb = new StringBuilder();

        if (asInt(chars[0]) >= 2) {
            //wartosci od 20 do 31
            sb.append(getValue(tens, asInt(chars[0]))).append(" ").append(getValue(unities, asInt(chars[1]))).append(" ");
        } else if (asInt(chars[0]) < 2) {
            //wartosci do 19
            sb.append(getValue(unities, asInt(chars[0]) * 10 + asInt(chars[1]))).append(" ");
        } else {
            throw new IllegalArgumentException("Day should have at least one characters");
        }

        return sb.toString();
    }

    public String resolveMonth(String month) {
        char[] chars = addZeroIfTooShort(month.toCharArray(), 2);
        StringBuilder sb = new StringBuilder();
        sb.append(getValue(months, asInt(chars[0]) * 10 + asInt(chars[1]))).append(" ");
        return sb.toString();
    }

    public String resolveYear(String year) {
        char[] chars = addZeroIfTooShort(year.toCharArray(), 4);
        StringBuilder sb = new StringBuilder();
        sb.append(getValue(thousands, asInt(chars[0]))).append(" ");
        if (asInt(chars[1]) > 0) {
            sb.append(getValue(hundred, asInt(chars[1]))).append(" ");
        }
        if (asInt(chars[2]) < 2) {
            sb.append(getValue(unities, asInt(chars[2]) * 10 + asInt(chars[3]))).append(" ");
        } else {
            sb.append(getValue(tens, asInt(chars[2]))).append(" ");
            sb.append(getValue(unities, asInt(chars[3]))).append(" ");
        }

        return sb.toString();
    }

    private char[] addZeroIfTooShort(char[] chars, int desiredLenght) {
        List<Character> characters = Lists.newArrayList(Chars.asList(chars));
        while (characters.size() < desiredLenght) {
            characters.add(0, '0');
        }
        return Chars.toArray(characters);
    }

    private String getValue(String[] feed, int value) {
        return feed[value];
    }

    private int asInt(int numberPosition) {
        return numberPosition - ZERO_VALUE;
    }

}
