package de.fhws.fiw.pvs.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by proj on 4/20/17.
 */
public interface CounterRemoteInterface extends Remote {
    String EXAMPLE_SERVICE_NAME = "CounterService";

    void increase() throws RemoteException;

    int getCounter() throws RemoteException;
}
