package org.nigajuan.dynatree.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nigajuan
 * Date: 07/07/12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class Function implements JsonSerializable {

    @Getter @Setter
    private String function;

    public Function(String function) {
        this.function = function;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeRawValue(function);
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        jsonGenerator.writeRawValue(function);
    }
}
