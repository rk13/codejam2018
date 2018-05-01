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

            int[] a;
            Arrays.stream(a).sum()


            knapsack();

            //long r = process();

            System.out.println("Case #" + i + ": " + "");
        }
    }


    public static int knapsack() {

        // We can first handle the base case of the function.
        // We can easily compute f(1, b) since there is only at most one satisfying partition.
        // Therefore, f(1, b) = round(b / N × 100) if b ≥ C1, or -∞ otherwise.


        int[] CC = new int[N];
        for (int i = 0; i < C.size(); i++) {
            CC[i] = C.get(i);
        }


        int[][] F = new int[N + 1][N + 1]; //Create a matrix. Items are in rows and weight at in columns +1 on each side

        for (int i = 1; i <= N; i++) {
            if (i >= CC[1])
                F[1][i] = (int) Math.round(((double) i / N) * 100);
            else
                F[1][i] = Integer.MAX_VALUE;

        }

        // for a > 1, f(a, b) = max (Ca ≤ i ≤ b) (round(i / N × 100) + f(a - 1, b - i)).

        for (int item = 1; item <= N; item++) {
            for (int weight = 1; weight <= N; weight++) {
                //Is the current items weight less than or equal to running weight
                if (wt[item - 1] <= weight) {
                    //Given a weight, check if the value of the current item + value of the item that we could afford with the remaining weight
                    //is greater than the value without the current item itself
                    int valWithoutCurrent = V[item - 1][weight];
                    int valCurrentAndMaxForRemainingWeight = val[item - 1] + V[item - 1][weight - wt[item - 1]];
                    V[item][weight] = Math.max(valCurrentAndMaxForRemainingWeight, valWithoutCurrent);
                } else {
                    //If the current item's weight is more than the running weight, just carry forward the value without the current item
                    V[item][weight] = V[item - 1][weight];
                }
            }
        }

//        //Printing the matrix
//        for (int[] rows : V) {
//            for (int col : rows) {
//                System.out.format("%5d", col);
//            }
//            System.out.println();
//        }
//        return V[N][W];
        return 0;
    }

    private static Set<List<Integer>> partitions(Integer n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        Set<List<Integer>> result = new HashSet<>();
        result.add(Collections.singletonList(n));

        for (int i = 1; i < n; i++) {
            int a = n - i;
            Set<List<Integer>> subresults = partitions(i);
            for (List<Integer> sub : subresults) {
                if (sub.get(0) <= a) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(a);
                    tmp.addAll(sub);
                    result.add(tmp);
                }
            }

        }

        cache.put(n, result);
        return result;
    }

    private static long process(Set<List<Integer>> p) {
        return p.stream()
                .filter(Solution::validPartition)
                .map(Solution::toPercents)
                .max(Long::compareTo)
                .orElseThrow(RuntimeException::new);
    }

    private static boolean validPartition(List<Integer> partition) {
        if (partition.size() < C.size()) {
            return false;
        }
        for (int i = 0; i < C.size(); i++) {
            if (C.get(i) > partition.get(i)) {
                return false;
            }
        }
        return true;
    }

    private static long toPercents(List<Integer> partition) {
        return partition.stream()
                .mapToDouble(Integer::intValue)
                .mapToLong(x -> Math.round((x / N) * 100))
                .sum();
    }
}