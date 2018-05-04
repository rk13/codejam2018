import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    static Map<Integer, Set<List<Integer>>> cache = new HashMap<>();

    static int N, L;
    static List<Integer> C;

    public static void main(String[] args) {
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            N = Integer.parseInt(s[0]);
            L = Integer.parseInt(s[1]);

            String[] s1 = in.nextLine().split(" ");
            C = Stream.of(s1).map(Integer::parseInt).collect(Collectors.toList());
            C.sort(Collections.reverseOrder());

            long r = knapsack();

            System.out.println("Case #" + i + ": " + r);
        }
    }


    public static int knapsack() {

        // We can easily compute f(1, b) since there is only at most one satisfying partition.

        int[] wt = new int[N];
        int[] x = new int[N];

        for (int i = 0; i < N; i++) {
            if (i < C.size()) {
                wt[i] += C.get(i);
            }
            if (i > 0) {
                x[i] = x[i - 1] + wt[i - 1];
            }
        }


        int[][] V = new int[N + 1][N + 1]; // Create a matrix. Items are in rows and weight at in columns +1 on each side

        for (int weight = 1; weight <= N; weight++) {
            if (weight >= wt[0])
                V[1][weight] = (int) Math.round((((double) weight) / N) * 100);
            else
                V[1][weight] = Integer.MIN_VALUE;

        }

        for (int item = 2; item <= N; item++) {
            for (int weight = 1; weight <= N; weight++) {
                //Is the current items weight less than or equal to running weight
                if (weight >= wt[item - 1]) {

                    //max (Ca ≤ i ≤ b) (round(i / N × 100) + f(a - 1, b - i))
                    int max = Integer.MIN_VALUE;
                    for (int i = wt[item - 1]; i <= weight - (x[item - 1]); i++) {
                        int tmp = (int) Math.round((((double) i) / N) * 100);
                        int sum = tmp + V[item - 1][weight - i];
                        max = Math.max(max, sum);
                    }

                    V[item][weight] = max;
                } else {
                    V[item][weight] = Integer.MIN_VALUE;
                }
            }
        }

            //Printing the matrix
//        for (int[] rows : V) {
//            for (int col : rows) {
//                System.out.format("%5d", col);
//            }
//            System.out.println();
//        }
            return V[N][N];
        }
}