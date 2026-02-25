package Algorithm.NumberTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * 埃拉托斯特尼筛法
 */
public class Eratosthenes {
    private boolean[] notPrime;

    public Eratosthenes(int N) {
        notPrime = new boolean[N + 1];
        prime = new ArrayList<>();
        calculate(N);
    }

    private List<Integer> prime;

    private void calculate(int n) {
        notPrime[0] = notPrime[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!notPrime[i]) {
                prime.addLast(i);
                if ((long) i * i > n) {
                    continue;
                }
                for (int j = i * i; j <= n; j += i) {
                    notPrime[j] = true;
                }
            }
        }
    }

    public boolean isPrime(int n) {
        if (n > notPrime.length) {
            throw new IndexOutOfBoundsException(
                    "your number is too big!"
            );
        }
        return !notPrime[n];
    }

    public List<Integer> getPrimeList() {
        return prime;
    }
}
