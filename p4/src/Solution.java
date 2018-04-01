import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());

        for (int i = 1; i <= t; i++) {
            String[] dn = in.nextLine().split(" ");
            int d = Integer.parseInt(dn[0]);
            int n = Integer.parseInt(dn[1]);

            int[] k = new int[n];
            int[] s = new int[n];

            for (int j = 0; j < n; j++) {
                String[] ks = in.nextLine().split(" ");
                k[j] = Integer.parseInt(ks[0]);
                s[j] = Integer.parseInt(ks[1]);
            }

            String r = process(d, k, s);
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process(int d, int[] k, int[] s) {
        double maxTime = 0;
        for (int i = 0; i < k.length; i++) {
            double time = (double) (d - k[i]) / s[i];
            if (time > maxTime) {
                maxTime = time;
            }
        }
        return new BigDecimal(d / maxTime).setScale(6, RoundingMode.FLOOR).toString();
    }
}
