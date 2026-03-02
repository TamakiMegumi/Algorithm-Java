package Algorithm.NumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeFactorization {
    private int maxNum;
    private List<Integer>[] primeFactors;

    public PrimeFactorization(int maxNum) {
        this.maxNum = maxNum;
        primeFactors = new ArrayList[maxNum + 1];
        Arrays.setAll(primeFactors, _ -> new ArrayList<>());
        for (int i = 2; i <= maxNum; i++) {
            if (primeFactors[i].isEmpty()) {
                for (int j = i; j <= maxNum; j += i) {
                    primeFactors[j].add(i);
                }
            }
        }
    }

    public List<Integer> getPrimeFactors(int x) {
        return primeFactors[x];
    }

    public boolean isPrime(int x) {
        return x >= 2 && primeFactors[x].size() == 1;
    }
}
