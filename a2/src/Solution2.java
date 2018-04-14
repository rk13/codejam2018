
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution2 {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int R, B, C;

    static int[][] F;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());

        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            R = Integer.parseInt(s[0]);
            B = Integer.parseInt(s[1]);
            C = Integer.parseInt(s[1]);

            F = new int[C][];
            for (int j = 0; j < C; j++) {
                String[] s1 = in.nextLine().split(" ");
                F[j] = Stream.of(s1).mapToInt(Integer::parseInt).toArray();
            }

            int r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static int process() {
        int hi = Integer.MAX_VALUE;
        int lo = 0;

        while (hi > lo) {
            int mid = (hi + lo) / 2;
            if (func(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    private static boolean func(int t) {
        Integer x[] = new Integer[C];
        for (int i = 0; i < C; i++) {
            x[i] = (int) Math.min(Math.floor((t - F[i][2]) / F[i][1]), F[i][0]);
        }

        Arrays.sort(x, Collections.reverseOrder());

        long sum = 0;
        for (int i = 0; i < R; i++) {
            sum += x[i];
        }
        return sum >= B;
    }
}

