package de.sebb767.pvs.assignment3;


import de.sebb767.pvs.helper.ImprovedBenchmark;
import de.sebb767.pvs.helper.NumberGenerator;

public class ex3 {
    protected static ImprovedBenchmark ib = new ImprovedBenchmark();


    public static void main(String[] args) {
        NumberGenerator.ArrayContainer ac = (new NumberGenerator()).generateRandomArray(2 << 22);

        ArraySearchSequential asc = new ArraySearchSequential(ac);
        runBenchmark(asc::search, asc);

        ArraySearchParallel asp = new ArraySearchParallel(ac);
        runBenchmark(asp::search, asp);
    }

    public static void runBenchmark(Runnable fn, Object classname)
    {
        ib.run(fn,200, classname.getClass().getName());
    }
}
