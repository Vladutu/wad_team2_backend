package ro.ucv.ace.socket;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ro.ucv.ace.socket.impl.InstanceDeserializer;

/**
 * Created by ctotolin on 25-Nov-16.
 */
@JsonDeserialize(using = InstanceDeserializer.class)
public interface IJobResult {
    String getError();
    String getResponse();
}
