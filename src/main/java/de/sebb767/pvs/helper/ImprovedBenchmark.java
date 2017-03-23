package de.sebb767.pvs.helper;

/**
 * Created by proj on 3/23/17.
 */
public class ImprovedBenchmark {
    public final int WARMUP_OFFSET = 75;

    public void run(Runnable func, int times)
    {
        this.run(func, times, "Benchmark");
    }

    public void run(Runnable func, int times, String name) {
        run(func, null, times, name);
    }

    public void run(Runnable func, Runnable reset, int times, String name)
    {
        System.out.print("Starting benchmark ... ");

        long[] measurements = new long[times + WARMUP_OFFSET];

        long endTime = 0, startTime = 0;

        for (int i = 0; i < times; i++) {
            startTime = System.currentTimeMillis();

            func.run();

            endTime = System.currentTimeMillis();

            if(reset != null) reset.run();

            measurements[i] = endTime - startTime;
        }

        double avg = 0;
        for (int i = WARMUP_OFFSET; i < times; i++) {
            avg += measurements[i];
        }
        avg /= times;

        System.out.printf("done. %s required %.2f ms on average.\n", name, avg);
    }
}
