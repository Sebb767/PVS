package de.fhws.fiw.pvs.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Counter  implements Remote, Serializable {
    int counter = 0;

    protected Counter() throws RemoteException {
    }

    void increase() { counter++; }

    public int getCounter() {
        return counter;
    }

    public static class A {

    }
}
