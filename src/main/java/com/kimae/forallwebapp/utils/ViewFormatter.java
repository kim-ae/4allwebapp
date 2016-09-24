package com.kimae.forallwebapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewFormatter implements DataFormatter {
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
    @Override
    public String formatDate(Date date) {
        return dateFormatter.format(date);
    }

}
