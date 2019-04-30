import Exe2Ver1.Numbers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NumbersTests {
    @Test
    public void resultsValidityTest(){
        for (int nThreads = 1; nThreads <= 10; ++nThreads) {
            System.out.println("Test for " + nThreads + " threads");
            double[] data = new double[100];
            Arrays.fill(data, 1);
            Numbers numbers = new Numbers();
            double[] results = numbers.compute(d -> d + 1, data, nThreads);
            assert results != null;
            assert results.length == data.length;
            for (int i = 0; i < data.length; ++i)
                assert data[i] + 1 == results[i];
        }
    }
}
