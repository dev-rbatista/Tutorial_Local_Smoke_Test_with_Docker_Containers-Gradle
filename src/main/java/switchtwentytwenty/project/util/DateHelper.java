package switchtwentytwenty.project.util;


import switchtwentytwenty.project.exceptions.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateHelper {

    static final String DATE_IS_INVALID = "Date is in invalid format";


    private DateHelper() {
    }


    public static Calendar parseDateToCalendar(String dateString) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(dateString));
        } catch (NullPointerException | ParseException e) {
            throw new InvalidDateException(DATE_IS_INVALID);
        }
        return calendar;
    }

    // from https://www.baeldung.com/java-check-two-dates-on-same-day
    public static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

}
