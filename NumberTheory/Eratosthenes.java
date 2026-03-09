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
        notPrime = new boolean[maxnum + 1];
        prime = new ArrayList<>();

        notPrime[0] = notPrime[1] = true;
        for (int i = 2; i <= maxnum; i++) {
            if (!notPrime[i]) {
                prime.addLast(i);
                for (int j = i * i; j <= maxnum; j += i) {
                    notPrime[j] = true;
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
