package com.intexsoft.webshop.productqueryservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonUtils {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    public JsonUtils(ObjectMapper mapper) {
        OBJECT_MAPPER = mapper;
    }

    public static String getAsString(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            log.debug("Unable to write object to JSON: {}", o, e);
            return o == null ? null : o.toString();
        }
    }
}