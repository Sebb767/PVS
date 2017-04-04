package de.sebb767.pvs.assignment3;

import de.sebb767.pvs.helper.NumberGenerator;

import java.util.Objects;

public class ArraySearchSequential {
    protected NumberGenerator.ArrayContainer data;

    public ArraySearchSequential(NumberGenerator.ArrayContainer data) {
        this.data = data;
    }

    public int search()
    {
        Integer[] ls = data.getData();
        Integer searched = data.getSearchedElement();

        for (int i = 0; i < ls.length; i++) {
            if(Objects.equals(ls[i], searched) && data.verifySearchedElement(i))
                return i;
        }

        return -1;
    }
}
