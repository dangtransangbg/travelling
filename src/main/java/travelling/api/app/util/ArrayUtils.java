package travelling.api.app.util;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {

    public static String convertToString(List<String> list) {
        if (list == null && list.isEmpty()) return "";

        return list.stream().collect(Collectors.joining(","));
    }

    public static List<String> convertToListFromString(String string) {
        if (StringUtils.isBlank(string)) return new ArrayList<>();

        return Arrays.asList(string.split(","));
    }
}
