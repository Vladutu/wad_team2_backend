package ro.ucv.ace.socket.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.springframework.stereotype.Component;
import ro.ucv.ace.socket.IJob;
import ro.ucv.ace.socket.ISocketManager;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ctotolin on 19-Nov-16.
 */
@Component
public class SocketManager implements ISocketManager {

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
     * Converts IJob to JSON using class mapper
     *
     * @param job Any type of supported job
     * @return Future of the job's result
     */
    @Override
    public Future<JobResult> sendJob(IJob job) {
        String jobString = "";

        // Convert job to a JSON format
        try {
            jobString = this.mapper.writeValueAsString(job);
            System.out.println(jobString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Submit async task to exec pool, and return a future variant of the job result
        return pool.submit(new SocketSender(this.socket, this.mapper, job.getType(), jobString));
    }
}
