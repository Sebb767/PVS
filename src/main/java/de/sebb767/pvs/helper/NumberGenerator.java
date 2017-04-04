package de.sebb767.pvs.helper;

import com.google.gson.Gson;

import java.io.*;
import java.util.Random;

/**
 * Created by proj on 3/22/17.
 */
public class NumberGenerator {
    public class ArrayContainer implements Serializable {
        protected Integer[] data;
        protected long sum;
        protected double average;
        protected int searchedElement;

        transient protected boolean fromCache = false;

        public ArrayContainer(Integer[] data, long sum, double average) {
            this.data = data;
            this.sum = sum;
            this.average = average;

            searchedElement = (int)(Math.random() * data.length);
        }

        public Integer[] getData() {
            return data;
        }

        public long getSum() {
            return sum;
        }

        public Integer getSearchedElement() {
            return data[searchedElement];
        }

        public boolean verifySearchedElement(int index)
        {
            return searchedElement == index;
        }

        public boolean isFromCache() {
            return fromCache;
        }

        public double getAverage() {
            return average;
        }
    }

    public ArrayContainer generateRandomArray(int size) {
        return generateRandomArray(size, 0xFFFFFFFF);
    }

    public ArrayContainer generateRandomArray(int size, int mask) { return generateRandomArray(size, mask, true); }

    public ArrayContainer generateRandomArray(int size, int mask, boolean useCached) {

        String cachePath = "/tmp/arraycache_s" + size + "_m" + mask + ".json";
        Gson gs = new Gson();

        if(useCached)
        {
            try {
                ArrayContainer ac = gs.fromJson(new FileReader(cachePath), ArrayContainer.class);
                return ac;
            } catch (FileNotFoundException e) {
                // nothing cached
            }
        }


        Integer[] data = new Integer[size];
        long sum = 0;

        Random rng = new Random();

        for (int i = 0; i < size; i++) {
            data[i] = rng.nextInt() & mask;
            sum += data[i];
        }

        ArrayContainer ac = new ArrayContainer(data, sum, ((double)sum) / size);

        if(useCached){
            try {
                FileWriter fw = new FileWriter(cachePath);
                gs.toJson(ac, fw);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ac;
    }
}
