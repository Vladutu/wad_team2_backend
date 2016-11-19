package ro.ucv.ace.socket;

import io.socket.client.Ack;
import io.socket.client.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ctotolin on 19-Nov-16.
 */
public class SocketSender implements Callable<String> {

    private String type;
    private Socket socket;
    private String message;

    public SocketSender(Socket socket, String type, String message){
        this.socket = socket;
        this.message = message;
        this.type = type;
    }

    //TODO: Return actual response, send actual message
    @Override
    public String call() throws Exception {
        final BlockingQueue<String> response = new LinkedBlockingQueue<>();
        this.socket.emit(this.type, new Ack() {
            @Override
            public void call(Object... args) {
                //TODO: get response from args[0];
                System.out.println("Received response from server");
                response.offer("Response");
            }
        });
        return response.take();
    }
}
