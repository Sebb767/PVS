package de.sebb767.pvs.assignment3;

import de.sebb767.pvs.helper.NumberGenerator;
import de.sebb767.pvs.helper.ThreadCountHelper;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArraySearchParallel {
    protected NumberGenerator.ArrayContainer data;
    protected int threads = ThreadCountHelper.getIdealThreadCount();
    protected final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public ArraySearchParallel(NumberGenerator.ArrayContainer data) {
        this.data = data;
    }

    public Integer search()
    {
        return forkJoinPool.invoke(new FJWorker(0, data.getData().length));
    }

    protected class FJWorker extends RecursiveTask<Integer> {
        protected int start, end;

        public FJWorker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if(getCount() > (data.getData().length / threads) && (data.getData().length >= threads))
            {
                return forkToWorker();
            }
            else
            {
                return work();
            }
        }

        protected Integer forkToWorker()
        {
            int split = start + (getCount() / 2);
            RecursiveTask<Integer> iv = new FJWorker(start, split),
                   jv = new FJWorker(split + 1, end);

            iv.fork();
            jv.fork();

            Integer r = iv.join();
            if(r != null)
                return r;
            else
                return jv.join();
        }

        protected Integer work()
        {
            Integer[] ls = data.getData();
            Integer searched = data.getSearchedElement();

            for (int i = start; i < end; i++) {
                if(Objects.equals(ls[i], searched) && data.verifySearchedElement(i))
                    return i;
            }

            return null;
        }

        public int getCount()
        {
            return end-start;
        }
    }
}
