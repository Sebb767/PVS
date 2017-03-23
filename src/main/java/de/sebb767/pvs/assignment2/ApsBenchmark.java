package de.sebb767.pvs.assignment2;

import de.sebb767.pvs.helper.ImprovedBenchmark;
import de.sebb767.pvs.helper.NumberGenerator;

/**
 * Created by proj on 3/23/17.
 */
public class ApsBenchmark {
    protected static ImprovedBenchmark ib = new ImprovedBenchmark();

    public static void main(String[] args) {

        NumberGenerator.ArrayContainer data = (new NumberGenerator()).generateRandomArray(2 << 24, 0x000000FF);


    }

    public static void RunBenchmark(AbstractPrefixSum aps)
    {
        ib.run(() -> aps.run(), () -> aps.reset(), 200, aps.getClass().getName());
    }
}
