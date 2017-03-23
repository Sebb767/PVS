package de.sebb767.pvs.helper;

import java.util.Random;

/**
 * Created by proj on 3/22/17.
 */
public class NumberGenerator {
    public class ArrayContainer {
        protected Integer[] data;
        protected long sum;
        protected double average;

        public ArrayContainer(Integer[] data, long sum, double average) {
            this.data = data;
            this.sum = sum;
            this.average = average;
        }

        public Integer[] getData() {
            return data;
        }

        public long getSum() {
            return sum;
        }

        public double getAverage() {
            return average;
        }
    }

    public ArrayContainer generateRandomArray(int size) {
        return generateRandomArray(size, 0xFFFFFFFF);
    }

    public ArrayContainer generateRandomArray(int size, int mask) {
        Integer[] data = new Integer[size];
        long sum = 0;

        Random rng = new Random();

        for (int i = 0; i < size; i++) {
            data[i] = rng.nextInt() & mask;
            sum += data[i];
        }

        return new ArrayContainer(data, sum, ((double)sum) / size);
    }
}
