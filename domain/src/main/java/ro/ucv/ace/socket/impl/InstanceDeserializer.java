package ro.ucv.ace.socket.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ro.ucv.ace.socket.IJobResult;

import java.io.IOException;

/**
 * Created by ctotolin on 25-Nov-16.
 */
public class InstanceDeserializer extends JsonDeserializer<IJobResult> {

    @Override
    public IJobResult deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jsonParser);
        Class<? extends IJobResult> instanceClass;
        switch(root.get("type").toString()){
            case "compile":
                instanceClass = CompilationJobResult.class;
                break;
            default:
                return null;
        }

        return mapper.readValue(jsonParser, instanceClass);
    }
}