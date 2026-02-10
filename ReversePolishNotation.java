package Algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 逆波兰表达式
 */
public class ReversePolishNotation {
    private Map<Character, Integer> map = new HashMap<>() {
        {
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
            put('%', 2);
            put('^', 3);
        }
    };

    public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        nums.addLast(0);
        s = s.replaceAll(" ", "");
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        char[] cs = s.toCharArray();
        int i;
        for (i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                while (!ops.isEmpty()) {
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    while (j < n && isNum(cs[j])) {
                        u = u * 10 + (int) (cs[j] - '0');
                        j++;
                    }
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.size() < 2 || ops.isEmpty()) {
            return;
        }
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        } else if (op == '%') {
            ans = a % b;
        } else if (op == '^') {
            ans = (int) Math.pow(a, b);
        }
        nums.addLast(ans);
    }

    boolean isNum(char c) {
        return Character.isDigit(c);
    }
}
