package de.sebb767.pvs.assignment1;

import de.sebb767.pvs.assignment1.Implementation.ExecutorServiceWorker;
import de.sebb767.pvs.assignment1.Implementation.ForkJoinWorker;
import de.sebb767.pvs.assignment1.Implementation.SequentialWorker;
import de.sebb767.pvs.assignment1.Implementation.StreamWorker;
import de.sebb767.pvs.helper.Benchmark;
import de.sebb767.pvs.helper.NumberGenerator;

public class Main {
    public static void main(String[] args) {
        NumberGenerator.ArrayContainer data = (new NumberGenerator()).generateRandomArray(2 << 25);

        runBenchmark(new SequentialWorker(), data);
        runBenchmark(new ExecutorServiceWorker(), data);
        runBenchmark(new StreamWorker(), data);
        runBenchmark(new ForkJoinWorker(), data);
    }

    public static long runBenchmark(Worker w, NumberGenerator.ArrayContainer data)
    {
        Benchmark benchmark = new Benchmark();

        benchmark.run(() -> w.processData(data.getData(), (x,y) -> x + y), w.getClass().getName());

        long ret = w.getResult();
        assert(((Long)data.getSum()).equals(ret));
        return ret;
    }
}
