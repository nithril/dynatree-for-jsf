package org.nigajuan.dynatree.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 05/07/12
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public class DTUtils {


    private static class SingletonHolder{
        private static ObjectWriter objectWriter = null;

        static {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectWriter = mapper.writer().without(SerializationFeature.INDENT_OUTPUT);
        }
    }


    public static ObjectWriter getObjectWriter() {
        return SingletonHolder.objectWriter;
    }
}
