package de.sebb767.pvs.assignment1;


import java.util.function.LongBinaryOperator;

public interface Worker {
    void processData(Integer[] data, LongBinaryOperator lbn);
    long getResult();
}
