package ro.ucv.ace.socket;

import ro.ucv.ace.socket.impl.JobResult;

import java.util.concurrent.Future;

/**
 * Created by Geo on 19.11.2016.
 */
public interface ISocketManager {

    Future<JobResult> sendJob(IJob job);
}
