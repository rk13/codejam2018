import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.valueOf(in.nextLine());
        for (int i = 1; i <= t; ++i) {
            String[] s = in.nextLine().split(" ");
            BigInteger n = new BigInteger(s[0]);
            BigInteger k = new BigInteger(s[1]);
            BigInteger[] r = process(n, k);
            System.out.println("Case #" + i + ": " + r[0] + " " + r[1]);
        }
    }

    static BigInteger[] process(BigInteger n, BigInteger k) throws Exception {
        TreeMap<BigInteger, BigInteger> s = new TreeMap<>();
        s.put(n, BigInteger.ONE);

        BigInteger x1;
        BigInteger x0;
        BigInteger p = BigInteger.ZERO;
        for (; ; ) {
            Map.Entry<BigInteger, BigInteger> entry = s.lastEntry();
            BigInteger key = entry.getKey();
            BigInteger value = entry.getValue();

            p = p.add(value);
            s.remove(key);

            BigDecimal v = new BigDecimal(key.subtract(BigInteger.ONE)).divide(BigDecimal.valueOf(2));
            x0 = v.setScale(0, RoundingMode.CEILING).toBigInteger();
            x1 = v.setScale(0, RoundingMode.FLOOR).toBigInteger();

            if (p.compareTo(k) >= 0) {
                break;
            }

            s.put(x0, s.getOrDefault(x0, BigInteger.ZERO).add(value));
            s.put(x1, s.getOrDefault(x1, BigInteger.ZERO).add(value));
        }
        return new BigInteger[]{x0, x1};
    }
}