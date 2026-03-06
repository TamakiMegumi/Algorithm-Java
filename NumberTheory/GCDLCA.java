package Algorithm.NumberTheory;

public class GCDLCA {
    private GCDLCA() {
    }

    public static long gcd(long a, long b) {
        while (a != 0) {
            long temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
