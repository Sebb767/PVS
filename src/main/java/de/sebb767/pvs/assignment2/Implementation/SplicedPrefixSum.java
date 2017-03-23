package de.sebb767.pvs.assignment2.Implementation;

import de.sebb767.pvs.assignment2.AbstractPrefixSum;
import de.sebb767.pvs.helper.NumberGenerator;
import de.sebb767.pvs.helper.ThreadCountHelper;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by proj on 3/23/17.
 */
public class SplicedPrefixSum extends AbstractPrefixSum {
    protected final int threads = ThreadCountHelper.getIdealThreadCount();
    protected Splice[] sp = new Splice[threads];
    protected final ExecutorService service = Executors.newCachedThreadPool();
    protected final ExecutorCompletionService ecs = new ExecutorCompletionService(service);

    protected class Splice
    {
        int start, end;
        int blockSum = 0;

        public Splice(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void stepOne()
        {
            for (int i = start + 1; i < end; i++) {
                arrayData[i] += arrayData[i] - 1;
            }

            blockSum = arrayData[end - 1];
        }

        public void stepTwo(int previous)
        {
            for (int i = start + 1; i < end; i++) {
                arrayData[i] += previous;
            }
        }

        public int getBlockSum() {
            return blockSum;
        }
    }

    public SplicedPrefixSum(NumberGenerator.ArrayContainer data) {
        super(data);
    }

    @Override
    public void run() {
        int pieceSize = arrayData.length / threads;
        int offset= arrayData.length % threads;

        for (int i = 0; i < threads; i++) {
            final Splice csp = sp[i] = new Splice((i == 0 ? 0 : (i * pieceSize) + offset), ((i + 1) * pieceSize) + offset);

            ecs.submit(() -> { csp.stepOne(); return null; });
        }

        joinAll();

        for (int i = 1; i < threads; i++) {
            final Splice psp = sp[i - 1];
            final Splice csp = sp[i];

            ecs.submit(() -> { csp.stepTwo(psp.getBlockSum()); return null; });
        }

        joinAll();
    }

    protected void joinAll()
    {
        for (int i = 0; i < threads; i++) {
            try {
                ecs.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
