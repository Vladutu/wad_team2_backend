package ro.ucv.ace.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ctotolin on 19-Nov-16.
 */
@Component
public class SocketManager {

    @Autowired
    private Environment environment;

    private String protocol;
    private String host;
    private String port;
    private Socket socket = null;
    private boolean connected = false;
    private ExecutorService pool;
    private ObjectMapper mapper;

    public SocketManager(String protocol, String port, String host) {
        this.protocol = protocol;
        this.port = port;
        this.host = host;
        this.pool = Executors.newFixedThreadPool(10);
        this.mapper = new ObjectMapper();

        startListening();
    }

    private void startListening() {
        try {
            this.socket = IO.socket(protocol + "://" + host + ":" + port);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.socket.on(Socket.EVENT_CONNECT, args -> {
            this.connected = true;
        }).on(Socket.EVENT_DISCONNECT, args -> {
            this.connected = false;
        });
        this.socket.connect();
    }

    /**
     * Sends a job to the Code Verifier server
     * Converts Job to JSON using class mapper
     * @param job Any type of supported job
     * @return Future of the job's result
     */
    public Future<JobResult> sendJob(Job job) {
        // Convert job to a JSON format
        String jobString;
        try {
             jobString = this.mapper.writeValueAsString(job);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Get job result
        return pool.submit(new SocketSender(this.socket, this.mapper, "test", "sad"));
    }
}
