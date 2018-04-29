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

            Set<List<Integer>> p = partitions(N);
            long r = process(p);

            System.out.println("Case #" + i + ": " + r);
        }
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