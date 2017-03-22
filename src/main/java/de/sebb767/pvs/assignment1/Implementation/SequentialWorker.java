package de.sebb767.pvs.assignment1.Implementation;

import de.sebb767.pvs.assignment1.Worker;

import java.util.function.LongBinaryOperator;

/**
 * Created by proj on 3/22/17.
 */
public class SequentialWorker implements Worker {
    protected long sum = 0;

    @Override
    public void processData(Integer[] data, LongBinaryOperator lbn) {
        try {
            sum = (new IterativeWorker(data, 0, data.length, lbn)).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getResult() {
        return sum;
    }
}
