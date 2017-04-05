package de.sebb767.pvs.helper;

public class Benchmark {
    public void run(Runnable func)
    {
        this.run(func, "Benchmark");
    }

    public void run(Runnable func, String name)
    {
        this.run(func, "Benchmark", null);
    }

    public void run(Runnable func, String name, Runnable resetFn)
    {
        System.out.print("Starting benchmark ... ");

        long endTime = 0;
        long startTime = System.currentTimeMillis();

        func.run();

        endTime = System.currentTimeMillis();

        if(resetFn != null)
            resetFn.run();

        System.out.printf("done. %s required %d ms.\n", name, endTime - startTime);
    }
}
