package de.sebb767.pvs.assignment1.Implementation;

import java.util.Arrays;
import java.util.function.LongBinaryOperator;

public class StreamWorker extends AbstractWorker {
    @Override
    public void processData(Integer[] data, LongBinaryOperator lbn) {
        result = Arrays.stream(data).parallel().mapToLong(i -> (long)i).parallel().sum();
    }
}
