package Algorithm.NumberTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * 埃拉托斯特尼筛法
 */
public class Eratosthenes {
    private boolean[] notPrime;
    private List<Integer> prime;

    public Eratosthenes(int maxnum) {
        if (maxnum < 0) {
            throw new IllegalArgumentException("maxnum must be >=0");
        }
        notPrime = new boolean[maxnum + 1];
        int capacity = (maxnum < 2 ? 0 : (int) (maxnum / Math.log(maxnum))) + 10;
        prime = new ArrayList<>(capacity);

        notPrime[0] = notPrime[1] = true;
        for (int i = 2; i <= maxnum; i++) {
            if (!notPrime[i]) {
                prime.add(i);
                for (long j = 1L * i * i; j <= maxnum; j += i) {
                    notPrime[(int) j] = true;
                }
            }
        }
    }

    public boolean isPrime(int n) {
        return !notPrime[n];
    }

    public List<Integer> getPrimeList() {
        return prime;
    }
}
