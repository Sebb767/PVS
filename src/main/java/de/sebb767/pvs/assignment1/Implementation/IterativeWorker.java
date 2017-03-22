package de.sebb767.pvs.assignment1.Implementation;

import java.util.concurrent.Callable;
import java.util.function.LongBinaryOperator;

public class IterativeWorker implements Callable<Long>
{
        Integer[] data;
        int start, end;
        LongBinaryOperator lbn;

        IterativeWorker(Integer[] data, int start, int end, LongBinaryOperator lbn) {
            this.data = data;
            this.start = start;
            this.end = end;
            this.lbn = lbn;
        }

        @Override
        public Long call() throws Exception {
            long result = 0;

            for (int i = start; i < end; i++) {
                result = lbn.applyAsLong(data[i], result);
            }

            return result;
        }
}
