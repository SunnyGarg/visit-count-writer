package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static String convertDateToYearAndMonth(Date date) {
        return new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT).format(date);
    }
}
