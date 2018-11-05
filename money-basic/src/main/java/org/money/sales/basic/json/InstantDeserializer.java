package org.money.sales.basic.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;

/**
 * Created by Lee on 2018/11/1.
 */
public class InstantDeserializer extends JsonDeserializer<Instant> {

    public static final InstantDeserializer INTANCE = new InstantDeserializer();

    private InstantDeserializer() {
    }

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value == null || value.trim().equals("")) return null;
        return Instant.parse(value);
    }
}
