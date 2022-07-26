package com.emailservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class CommonUtils {

    public static String objToJson(Object obj) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

}
