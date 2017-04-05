package de.sebb767.pvs.assignment1.Implementation;

import de.sebb767.pvs.assignment1.Worker;

public abstract class AbstractWorker implements Worker {
    protected long result;

    public long getResult() {
        return result;
    }
}
