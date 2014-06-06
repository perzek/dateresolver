import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by perzek on 2014-05-23.
 */
public class DateBuilder {

    private static final String UNITIES[] = new String[]{"", "pierwszego", "drugiego", "trzeciego", "czwartego", "piątego", "szóstego", "siódmego", "ósmego", "dziewiątego", "dziesiątego", "jedenastego", "dwunastego", "trzynastego", "czternastego", "piętnastego", "szenastego", "siedemnastego", "osiemnastego", "dziewiętnastego"};
    private static final String TENS[] = new String[]{"", "", "dwudziestego", "trzydziestego", "czterdziestego", "pięćdziesiątego", "sześćdziesiątego", "siedemdziesątego", "osiemdziesiątego", "dziwięćdziesiątego"};
    private static final String HUNDREDS[] = new String[]{"", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset", "osiemset", "dziewięćset"};
    private static final String HUNDRED_ALTERNATIVE[] = new String[]{"", "setnego", "dwusetnego", "trzysetnego", "czterysetnego", "pięćsetnego", "sześćsetnego", "siedemsetnego", "osiemsetnego", "dziewięćsetnego"};
    private static final String THOUSANDS[] = new String[]{"", "tysiąc", "dwa tysiące", "trzy tysiące", "cztery tysiące", "pięć tysięcy", "sześć tysięcy", "siedem tysięcy", "osiem tysięcy", "dziewięć tysięcy"};
    private static final String THOUSANDS_ALTERNATIVE[] = new String[]{"", "tysięcznego", "dwu tysięcznego", "trzy tysięcznego", "cztero tysięcznego", "pięcio tysięcznego", "sześcio tysięcznego", "siedmio tysięcznego", "ośmio tysięcznego", "dziewięcio tysięcznego"};

    private static final String MONTHS[] = new String[]{"", "stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "paźdzniernika", "listopada", "grudnia"};
    public static final char ZERO_VALUE = '0';

    public String resolveDate(LocalDate date) {
        return resolveDay(String.valueOf(date.getDayOfMonth())) + resolveMonth(String.valueOf(date.getMonthValue())) +
                resolveYear(String.valueOf(date.getYear())) + " roku";
    }

    public String resolveDay(String day) {
        char[] chars = addZeroIfTooShort(day.toCharArray(), 2);

        StringBuilder sb = new StringBuilder();

        if (asInt(chars[0]) >= 2) {
            // 20 to 31
            sb.append(getValue(TENS, asInt(chars[0]))).append(" ").append(getValue(UNITIES, asInt(chars[1]))).append(" ");
        } else if (asInt(chars[0]) < 2) {
            //below 19
            sb.append(getValue(UNITIES, asInt(chars[0]) * 10 + asInt(chars[1]))).append(" ");
        }

        return sb.toString();
    }

    public String resolveMonth(String month) {
        char[] chars = addZeroIfTooShort(month.toCharArray(), 2);
        StringBuilder sb = new StringBuilder();
        sb.append(getValue(MONTHS, asInt(chars[0]) * 10 + asInt(chars[1]))).append(" ");
        return sb.toString();
    }

    public String resolveYear(String year) {
        char[] chars = addZeroIfTooShort(year.toCharArray(), 4);
        List<String> result = Lists.newArrayList();
        result.add(getValue(THOUSANDS, asInt(chars[0])));
        Joiner joiner = Joiner.on(" ");
        //dated like 1800, 1900 etc
        if (asInt(chars[3]) == 0 && asInt(chars[2]) == 0) {
            result.add(HUNDRED_ALTERNATIVE[asInt(chars[1])]);
            //date 1000, 2000 etc
            if (asInt(chars[1]) == 0) {
                result.clear();
                result.add(THOUSANDS_ALTERNATIVE[asInt(chars[0])]);
            }
            return joiner.join(result);
        }
        if (asInt(chars[1]) > 0) {
            result.add(getValue(HUNDREDS, asInt(chars[1])));
        }
        if (asInt(chars[2]) < 2) {
            result.add(getValue(UNITIES, asInt(chars[2]) * 10 + asInt(chars[3])));
        } else {
            result.add(getValue(TENS, asInt(chars[2])));
            result.add(getValue(UNITIES, asInt(chars[3])));
        }

        return joiner.join(result);
    }

    private char[] addZeroIfTooShort(char[] chars, int desiredLength) {
        List<Character> characters = Lists.newArrayList(Chars.asList(chars));
        while (characters.size() < desiredLength) {
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
