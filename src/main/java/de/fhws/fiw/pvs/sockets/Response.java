package de.fhws.fiw.pvs.sockets;

/**
 * Created by proj on 4/6/17.
 */
class Response {
    int cntr;
    String message;

    public Response(int cntr, String message) {
        this.cntr = cntr;
        this.message = message;
    }

    public int getCntr() {
        return cntr;
    }

    public String getMessage() {
        return message;
    }
}
