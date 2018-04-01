
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        int t = Integer.valueOf(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            int n = Integer.valueOf(in.nextLine());

            BigInteger a = new BigInteger(s[0]);
            BigInteger b = new BigInteger(s[1]);

            process(a, b, n);
        }
    }

    public static String process(BigInteger a, BigInteger b, int n) {
        BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
        Response response;
        int i = 0;
        do {
            i++;

            BigInteger delta = b.subtract(a).divide(TWO);
            delta = delta.equals(BigInteger.ZERO) ? BigInteger.ONE : delta;
            BigInteger mid = a.add(delta);

            System.out.println(mid);

            response = Response.valueOf(in.nextLine());
            switch (response) {
                case TOO_BIG:
                    b = mid;
                    break;
                case TOO_SMALL:
                    a = mid;
                    break;
            }
        } while (response != Response.CORRECT && i < n);

        return "";
    }

    enum Response {
        TOO_BIG,
        TOO_SMALL,
        WRONG_ANSWER,
        CORRECT
    }
}