package Algorithm.NumberTheory;

import java.util.ArrayList;
import java.util.List;

public class LPFFactorization {
    private int maxsize;
    private int[] lpf;

    public LPFFactorization(int maxsize) {
        this.maxsize = maxsize;
        lpf = new int[maxsize + 1];
        for (int i = 2; i <= maxsize; i++) {
            if (lpf[i] == 0) {
                for (int j = i; j <= maxsize; j += i) {
                    if (lpf[j] == 0) {
                        lpf[j] = i;
                    }
                }
            }
        }
    }

    public List<int[]> primeFactorization(int x) {
        List<int[]> res = new ArrayList<>();
        while (x > 1) {
            int p = lpf[x];
            int e = 1;
            for (x /= p; x % p == 0; x /= p) {
                e++;
            }
            res.add(new int[]{p, e});
        }
        return res;
    }

    public int getLPF(int x) {
        return lpf[x];
    }

    public boolean isPrime(int x) {
        return x >= 2 && lpf[x] == x;
    }
}
