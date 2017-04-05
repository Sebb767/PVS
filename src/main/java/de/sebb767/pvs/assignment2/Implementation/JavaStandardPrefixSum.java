package de.sebb767.pvs.assignment2.Implementation;

import de.sebb767.pvs.assignment2.AbstractPrefixSum;
import de.sebb767.pvs.helper.NumberGenerator;

import java.util.Arrays;

public class JavaStandardPrefixSum extends AbstractPrefixSum {
    protected int[] dataAsIntArray;

    public JavaStandardPrefixSum(NumberGenerator.ArrayContainer data) {
        super(data);
    }

    @Override
    public void run() {
        Arrays.parallelPrefix(dataAsIntArray, (a,b) -> a+b);
    }

    @Override
    public void reset() {
        dataAsIntArray = Arrays.stream(data.getData()).mapToInt(i->i).toArray();
    }
}
