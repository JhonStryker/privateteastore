package com.priavteTeaStore.zforTest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.util.ERR;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class JsonConverter {

    static public Map<String, String> convertToMap(Object item) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(item, Map.class);
    }

    static public Object convertToObj(Map<String, String> map, Class<?> classObj) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(map, classObj);
    }

    static public String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ToClientException(ERR.system_error_for_unknown_reson);
        }
    }

    static public Object convertToObj(String jsonStr, Class<?> classObj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, classObj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ToClientException(ERR.system_error_for_unknown_reson);
        }
    }
}
