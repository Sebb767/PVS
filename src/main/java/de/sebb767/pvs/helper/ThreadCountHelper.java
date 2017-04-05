package de.sebb767.pvs.helper;

/**
 * Created by proj on 3/23/17.
 */
public class ThreadCountHelper {
    public static int getCpuCount()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public static int getIdealThreadCount()
    {
        return getCpuCount() * 2;
    }
}
