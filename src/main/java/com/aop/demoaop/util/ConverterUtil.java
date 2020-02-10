package com.aop.demoaop.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConverterUtil {

    private ConverterUtil() {
    }

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static <T> T convertirJsonToClass(String json, Class<?> aClass) throws Exception {
        try {
            return mapper.readValue(json, mapper.constructType(aClass));
        } catch (Exception e) {
            log.info("Json: {}", json);
            log.info("Exception : ", e);
            throw e;
        }
    }

    public static String convertObjectToJson(Object object) throws Exception {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
        	log.info("Object: {}", object);
        	log.info("Exception : ", e);
            throw e;
        }
    }
    
    public static String convertTimestampToStringValueFormat(Timestamp date, String outputFormat) {
    	if (Objects.nonNull(date)) {
        	SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
        	return formatter.format(date);
    	}
    	return null;
    }

}
