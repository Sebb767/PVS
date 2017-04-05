package de.sebb767.pvs.assignment2.Implementation;

import de.sebb767.pvs.assignment2.AbstractPrefixSum;
import de.sebb767.pvs.helper.NumberGenerator;

/**
 * Created by proj on 3/23/17.
 */
public class ProceduralPrefixSum extends AbstractPrefixSum {
    public ProceduralPrefixSum(NumberGenerator.ArrayContainer data) {
        super(data);
    }

    @Override
    public void run() {
        for (int i = 1; i < arrayData.length; i++) {
            arrayData[i] += arrayData[i-1];
        }
    }
}
