package com.ranag.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonConverter {
    public static final Logger log = LogManager.getLogger(JsonConverter.class);
    public static String getJson(Object object) {
        if(object==null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e);
        }
        return jsonString;
    }
}
