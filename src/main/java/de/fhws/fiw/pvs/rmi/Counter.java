package de.fhws.fiw.pvs.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Counter extends UnicastRemoteObject implements CounterRemoteInterface {
    int counter = 0;

    protected Counter() throws RemoteException {
    }

    @Override
    public void increase() throws RemoteException { counter++; }

    @Override
    public int getCounter() throws RemoteException {
        return counter;
    }

    public static class A {

    }
}
