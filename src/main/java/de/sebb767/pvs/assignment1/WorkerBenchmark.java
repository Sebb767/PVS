package de.sebb767.pvs.assignment1;

import de.sebb767.pvs.assignment1.Implementation.ExecutorServiceWorker;
import de.sebb767.pvs.assignment1.Implementation.ForkJoinWorker;
import de.sebb767.pvs.assignment1.Implementation.SequentialWorker;
import de.sebb767.pvs.assignment1.Implementation.StreamWorker;
import de.sebb767.pvs.helper.Benchmark;
import de.sebb767.pvs.helper.NumberGenerator;
import de.sebb767.pvs.helper.ThreadCountHelper;

public class WorkerBenchmark {
    protected final NumberGenerator.ArrayContainer data;

    public static void main(String[] args) {
        int size = 25;

        size = parseCommandLine(args, size);

        runBenchmarkForAllWorkes(size);
    }

    public static int parseCommandLine(String args[], int defaultSize)
    {
        int size = defaultSize;

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--help":
                case "-h":
                    System.out.printf("Usage: %s [--size|-s n] [--help|-h]", args[0]);
                    System.exit(0);
                    break;

                case "--size":
                case "-s":
                    try {
                        size = Integer.parseInt(args[++i]);
                    }
                    catch(ArrayIndexOutOfBoundsException ex) {
                        System.out.println("The parameter size needs a valid integer as parameter!");
                        System.exit(2);
                    }
                    catch(NumberFormatException ex) {
                        System.out.println("The parameter size needs a valid integer as parameter!");
                        System.exit(1);
                    }
                    break;

                default:
                    System.out.printf("Unknown parameter %s!\n", args[i]);
                    System.exit(4);
                    break;
            }
        }

        return size;
    }

    public static void runBenchmarkForAllWorkes(int arraySize)
    {
        System.out.printf("Starting benchmark using %d threads and a dataset of 2^%.2f elements.\n" +
                "Starting array generation ... ", ThreadCountHelper.getIdealThreadCount(), arraySize);

        NumberGenerator.ArrayContainer data = (new NumberGenerator()).generateRandomArray(2 << arraySize);
        WorkerBenchmark wb = new WorkerBenchmark(data);

        System.out.println("done.");

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
