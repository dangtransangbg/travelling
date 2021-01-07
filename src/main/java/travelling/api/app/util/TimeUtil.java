package travelling.api.app.util;

import org.apache.commons.lang3.StringUtils;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.exception.TimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private final static String PATTERN = "dd-MM-yyyy";
    private final static String PATTERN_FULL = "dd-MM-yyyy HH:mm:ss";
    private final static String PATTERN_FULL_2 = "MM-yyyy-dd HH:mm:ss";

    public static LocalDate convertToLocalDate(String date) {

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN));
    }

    public static LocalDate convertToLocalDate(String date, String pattern) {
        if (StringUtils.isNotBlank(pattern)) {
            if (StringUtils.isBlank(date))
                throw new TimeException(MessageConstant.TIME_ERROR.value());

            return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
        }

        return convertToLocalDate(date);
    }

    public static LocalDate plusDays(LocalDate date, long to) {
        return date.plusDays(to);
    }

    public static LocalDateTime convertToLocalDateTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(PATTERN_FULL));
    }

    public static String convertToStringFromLocalDateTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(PATTERN_FULL));
    }

    public static String convertToStringFromLocalDate(LocalDate time, String patter) {
        if (StringUtils.isBlank(patter))
            return time.format(DateTimeFormatter.ofPattern(PATTERN));

        return time.format(DateTimeFormatter.ofPattern(patter));
    }

    public static String convertToStringFromLocalDateTime2(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(PATTERN_FULL_2));
    }

    public static String covertToStringFromLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(PATTERN));
    }

}



