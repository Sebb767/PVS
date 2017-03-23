package de.sebb767.pvs.assignment2;

import de.sebb767.pvs.assignment1.Implementation.ExecutorServiceWorker;
import de.sebb767.pvs.assignment1.Implementation.ForkJoinWorker;
import de.sebb767.pvs.assignment1.Implementation.SequentialWorker;
import de.sebb767.pvs.assignment1.Implementation.StreamWorker;
import de.sebb767.pvs.assignment1.Worker;
import de.sebb767.pvs.assignment1.WorkerBenchmark;
import de.sebb767.pvs.helper.ImprovedBenchmark;
import de.sebb767.pvs.helper.NumberGenerator;
import de.sebb767.pvs.helper.ThreadCountHelper;


public class WorkerBenchmarkRefactored extends WorkerBenchmark {

    public static void main(String[] args) {
        int size = 25;

        size = parseCommandLine(args, size);

        System.out.printf("Starting benchmark using %d threads and a dataset of 2^%.2f elements.\n" +
                "Starting array generation ...", ThreadCountHelper.getIdealThreadCount(), size);

        NumberGenerator.ArrayContainer data = (new NumberGenerator()).generateRandomArray(size);
        WorkerBenchmarkRefactored wb = new WorkerBenchmarkRefactored(data);

        System.out.println(" done.");


        wb.runBenchmark(new SequentialWorker());
        wb.runBenchmark(new ExecutorServiceWorker());
        wb.runBenchmark(new StreamWorker());
        wb.runBenchmark(new ForkJoinWorker());
    }

    public WorkerBenchmarkRefactored(NumberGenerator.ArrayContainer data) {
        super(data);
    }

    public long runBenchmark(Worker w)
    {
        ImprovedBenchmark benchmark = new ImprovedBenchmark();

        benchmark.run(() -> w.processData(data.getData(), (x,y) -> x + y), 200, w.getClass().getName());

        long ret = w.getResult();
        assert(((Long)data.getSum()).equals(ret));
        return ret;
    }
}
