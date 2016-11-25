package ro.ucv.ace.socket.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.Ack;
import io.socket.client.Socket;
import org.json.JSONObject;
import ro.ucv.ace.socket.IJobResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ctotolin on 19-Nov-16.
 */
public class SocketSender implements Callable<IJobResult> {

    private String type;

    private Socket socket;

    private String message;

    private ObjectMapper mapper;

    public SocketSender(Socket socket, ObjectMapper mapper, String type, String message) {
        this.socket = socket;
        this.message = message;
        this.type = type;
        this.mapper = mapper;
    }

    /**
     * Sends a job message to the code verifier server through existent socket
     *
     * @return JobResult result of that IJob message
     * @throws Exception
     */
    @Override
    public IJobResult call() throws Exception {
        final BlockingQueue<String> response = new LinkedBlockingQueue<>();

        this.socket.emit(this.type, this.message, (Ack) args -> {
            System.out.println("Received response from server");

            // Get JSONObject response
            JSONObject jsonResponse = (JSONObject) args[0];

            // Convert it to string
            String stringResponse = jsonResponse.toString();

            // Offer it to the queue
            response.offer(stringResponse);
        });

        // Read value from queue, convert to JobResult and return it
        // TODO: make sure response is a valid IJobResult , otherwise throw JsonParseException
        return mapper.readValue(response.take(), IJobResult.class);
    }
}
