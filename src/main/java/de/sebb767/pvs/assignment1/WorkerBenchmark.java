package de.sebb767.pvs.assignment1;

import de.sebb767.pvs.assignment1.Implementation.ExecutorServiceWorker;
import de.sebb767.pvs.assignment1.Implementation.ForkJoinWorker;
import de.sebb767.pvs.assignment1.Implementation.SequentialWorker;
import de.sebb767.pvs.assignment1.Implementation.StreamWorker;
import de.sebb767.pvs.helper.Benchmark;
import de.sebb767.pvs.helper.NumberGenerator;

public class WorkerBenchmark {
    protected final NumberGenerator.ArrayContainer data;

    public static void main(String[] args) {
        NumberGenerator.ArrayContainer data = (new NumberGenerator()).generateRandomArray(2 << 25);
        WorkerBenchmark wb = new WorkerBenchmark(data);

        wb.runBenchmark(new SequentialWorker());
        wb.runBenchmark(new ExecutorServiceWorker());
        wb.runBenchmark(new StreamWorker());
        wb.runBenchmark(new ForkJoinWorker());
    }

    public WorkerBenchmark(NumberGenerator.ArrayContainer data) {
        this.data = data;
    }

    public long runBenchmark(Worker w)
    {
        Benchmark benchmark = new Benchmark();

        benchmark.run(() -> w.processData(data.getData(), (x,y) -> x + y), w.getClass().getName());

        long ret = w.getResult();
        assert(((Long)data.getSum()).equals(ret));
        return ret;
    }
}
