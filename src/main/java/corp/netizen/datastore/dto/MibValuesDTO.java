package corp.netizen.datastore.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import corp.netizen.datastore.model.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
public class MibValuesDTO extends JsonSerializer<MibValuesDTO> implements Serializable {

    List<MibValueDTO> values;

    public MibValuesDTO() {
        this.values = new LinkedList<>();
    }

    @Override
    public void serialize(MibValuesDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStartArray();
        for (MibValueDTO val : value.getValues()) {
            gen.writeObjectField("values", val);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

    @Getter
    @Setter
    public static class MibValueDTO extends JsonSerializer<MibValueDTO> implements Serializable {
        Instant timestamp;
        Long clientId;
        String oid;
        String value;

        @Override
        public void serialize(MibValueDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("timestamp", value.getTimestamp().toString());
            gen.writeNumberField("clientId", value.getClientId());
            gen.writeStringField("oid", value.getOid());
            gen.writeStringField("value", value.getValue());
            gen.writeEndObject();
        }

        public static Object deserialize(byte[] data) {
            return SerializationUtils.deserialize(data);
        }

        public static byte[] serialize(Object object) {
            return SerializationUtils.serialize(object);
        }
    }


    public static Object deserialize(byte[] data) {
        return SerializationUtils.deserialize(data);
    }

    public static byte[] serialize(Object object) {
        return SerializationUtils.serialize(object);
    }
}
