package de.sebb767.pvs.assignment1.Implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.LongBinaryOperator;

public class ExecutorServiceWorker extends AbstractWorker {


    @Override
    public void processData(Integer[] data, LongBinaryOperator lbn) {
        int cores = 4;
        assert(data.length > cores);
        int pieceSize = data.length / cores;
        result = 0;

        ExecutorService s = Executors.newFixedThreadPool(cores);
        List<Future> futures = new LinkedList<>();


        for (int i = 0; i < cores; i++) {
            int start = i * pieceSize;
            int end = ((i + 1) * pieceSize) - 1;

            Future<Long> f = s.submit(
                    new IterativeWorker(data, start, end, lbn)
            );
            futures.add(f);
        }

        for (Future<Long> f : futures) {
            try {
                result = lbn.applyAsLong(f.get(), result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        s.shutdown();
    }
}
