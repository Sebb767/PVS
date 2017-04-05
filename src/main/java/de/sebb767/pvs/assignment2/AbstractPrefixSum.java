package de.sebb767.pvs.assignment2;

import de.sebb767.pvs.helper.NumberGenerator;
import java.util.Arrays;

public abstract class AbstractPrefixSum {
    protected NumberGenerator.ArrayContainer data;
    protected Integer[] arrayData;

    public AbstractPrefixSum(NumberGenerator.ArrayContainer data) {
        this.data = data;
        reset();
    }

    abstract public void run();

    public void reset()
    {
        arrayData = Arrays.copyOf(data.getData(), data.getData().length);
    }

    public NumberGenerator.ArrayContainer getData() {
        return data;
    }
}
