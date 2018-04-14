
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution2 {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int R, B, C;

    static int[][] F;

    static long RESULT = 0;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            R = Integer.parseInt(s[0]);
            B = Integer.parseInt(s[1]);
            C = Integer.parseInt(s[2]);

            F = new int[C][];
            for (int j = 0; j < C; j++) {
                String[] s1 = in.nextLine().split(" ");
                F[j] = Stream.of(s1).mapToInt(Integer::parseInt).toArray();
            }

            String r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process() {
        RESULT = Long.MAX_VALUE;
        find(Math.min(R, C), B, IntStream.range(0, C).boxed().collect(Collectors.toSet()), 0);
        return "" + RESULT;
    }

    private static void find(int r, int b, Set<Integer> freeCachiers, long max) {

        if (b == 0) {
            RESULT = Math.min(RESULT, max);
            return;
        }

        if (r == 0) {
            return;
        }

        if (max >= RESULT) {
            return;
        }

        for (int bits = 1; bits <= b; bits++) {
            for (int cachier = 0; cachier < C; cachier++) {

                if (freeCachiers.contains(cachier)) {

                    if (F[cachier][0] >= bits) {

                        long spent = F[cachier][1] * bits + F[cachier][2];

                        freeCachiers.remove(cachier);

                        find(r - 1, b - bits, freeCachiers, Math.max(spent, max));

                        freeCachiers.add(cachier);
                    }
                }
            }
        }
    }
}


