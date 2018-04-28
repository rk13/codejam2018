import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    static int N, P;

    static int[][] F;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            N = Integer.parseInt(s[0]);
            P = Integer.parseInt(s[1]);

            F = new int[N][];
            for (int j = 0; j < N; j++) {
                String[] s1 = in.nextLine().split(" ");
                F[j] = Stream.of(s1).mapToInt(Integer::parseInt).toArray();
            }

            String r = process();
            System.out.println("Case #" + i + ": " + r);
        }
    }

    public static String process() {
        return N + " " + P;
    }
}