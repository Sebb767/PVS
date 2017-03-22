package de.sebb767.pvs.helper;

public class Benchmark {
    public void run(Runnable func)
    {
        this.run(func, "Benchmark");
    }

    public void run(Runnable func, String name)
    {
        System.out.print("Starting benchmark ... ");

        long endTime = 0;
        long startTime = System.currentTimeMillis();

        func.run();

        endTime = System.currentTimeMillis();

        System.out.printf("done. %s required %d ms.\n", name, endTime - startTime);
    }
}
