package com.kimae.forallwebapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SerializationFormatter implements DataFormatter {
    
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyyyHHmm");

    @Override
    public String formatDate(Date date) {
        return dateFormatter.format(date);
    }
}
