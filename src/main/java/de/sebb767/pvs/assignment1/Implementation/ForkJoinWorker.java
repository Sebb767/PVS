package de.sebb767.pvs.assignment1.Implementation;

import de.sebb767.pvs.helper.ThreadCountHelper;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.LongBinaryOperator;

public class ForkJoinWorker extends AbstractWorker {
    protected final ForkJoinPool fjp = new ForkJoinPool();

    protected class ForkJoinSlave extends RecursiveTask<Long> {
        protected final Integer[] array;
        protected final int start, end, cores;
        protected final LongBinaryOperator lbn;

        public ForkJoinSlave(Integer[] array, int start, int end, int cores, LongBinaryOperator lbn) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.cores = cores;
            this.lbn = lbn;
        }

        @Override
        protected Long compute() {
            try {
                if ((end - start) <= (array.length / cores)) {
                    IterativeWorker iw = new IterativeWorker(array, start, end, lbn);

                    return iw.call();

                }

                int split = (end - start) / 2;

                //invokeAll(new ForkJoinSlave(array, start, start + split, cores, lbn),
                        //new ForkJoinSlave(array, start + split, end, cores, lbn));

                ForkJoinTask<Long> lower = (new ForkJoinSlave(array, start, start + split, cores, lbn)).fork(),
                        upper = (new ForkJoinSlave(array, start + split, end, cores, lbn)).fork();

                return lbn.applyAsLong(lower.get(), upper.join());

            } catch (Exception e) {
                e.printStackTrace();
                return new Long(0);
            }
        }
    }

    @Override
    public void processData(Integer[] data, LongBinaryOperator lbn) {
        result = fjp.invoke(new ForkJoinSlave(data, 0, data.length - 1, ThreadCountHelper.getIdealThreadCount(), lbn));
    }
}
