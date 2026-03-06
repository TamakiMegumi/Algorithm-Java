package Algorithm.NumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Divisor {
    private int maxnum;
    private List<Integer>[] divisors;

    public Divisor(int maxnum) {
        this.maxnum = maxnum;
        divisors = new ArrayList[maxnum + 1];
        Arrays.setAll(divisors, _ -> new ArrayList<>());
        for (int i = 1; i <= maxnum; i++) {
            for (int j = i; j <= maxnum; j += i) {
                divisors[j].add(i);
            }
        }
    }

}
