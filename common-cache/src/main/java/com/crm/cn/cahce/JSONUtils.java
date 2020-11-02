package com.crm.cn.cahce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JSONUtils {


    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json格式字符串
     *
     * @param obj
     * @return
     */
    public String obj2Str(Object obj) {

        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 字符串转普通对象
     */
    public <T> T str2Obj(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            if(!StringUtils.isEmpty(jsonStr)){
                t = objectMapper.readValue(jsonStr, clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 字符串转List集合
     */
    public <T> List<T> str2List(String jsonStr, Class<T> clazz) {
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        List<T> list = null;
        try {
            list = objectMapper.readValue(jsonStr, collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 字符串转Map
     */
    public <K, T> Map<K, T> str2Map(String jsonStr, Class<K> clazz, Class<T> valueClazz) {
        MapType mapType = objectMapper.getTypeFactory().constructMapType(Map.class, clazz, valueClazz);

        Map<K, T> map = null;
        try {
            map = objectMapper.readValue(jsonStr, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
