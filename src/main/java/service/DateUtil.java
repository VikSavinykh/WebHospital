package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service("dateUtil")
public class DateUtil {

    public static final String DATE_SIMPLE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_SIMPLE_FORMAT = "HH:mm";
    public static final String DATE_TIME_SIMPLE_FORMAT = "yyyy-MM-dd HH:mm";

    public static Date parse(String pattern, String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date);
    }

    public static String getDateInSimpleFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_SIMPLE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String getTimeSimpleFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_SIMPLE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static Date getToday(){
        return new Date();
    }

    public static String getDateTimeSimpleFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_SIMPLE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static List<DateTime> getAllMonthAndYear() {
        List<DateTime> allMonth = new ArrayList<>();
        DateTime dateTime = new DateTime().withDayOfMonth(1).withTime(0,0,0,0);
        int currentYear = dateTime.getYear();
        while (currentYear + 1 >= dateTime.getYear()) {
            allMonth.add(dateTime);
            dateTime = dateTime.plusMonths(1);
        }
        return allMonth;
    }

    public static String getMonthAndYearFormat(DateTime dateTime) {
        Locale loc = Locale.forLanguageTag("ru");
        String month = Month.of(dateTime.monthOfYear().get())
            .getDisplayName(TextStyle.FULL_STANDALONE, loc);
        return month + " " + dateTime.getYear() + " года";
    }
}
