import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            int[] a = Stream.of(s).mapToInt(Integer::parseInt).toArray();
            int[][] f = new int[a[0]][a[1]];

            for (int j = 0; j < a[0]; j++) {
                f[j] = in.nextLine().chars()
                        .map(x -> (char) x == '@' ? 1 : 0)
                        .toArray();
            }

            boolean r = process(f, a[0], a[1], a[2], a[3]);
            System.out.println("Case #" + i + ": " + (r ? "POSSIBLE" : "IMPOSSIBLE"));
        }
    }

    public static boolean process(int[][] w, int r, int c, int h, int v) {
        int[] rows = new int[r];
        int[] cols = new int[c];

        int totalChipsCount = chipCount(0, r - 1, 0, c - 1, w);
        int chipsOnSliceCount = totalChipsCount / ((h + 1) * (v + 1));

        if (chipsOnSliceCount * (h + 1) * (v + 1) != totalChipsCount) {
            return false;
        }

        if (totalChipsCount == 0) {
            return true;
        }

        for (int i = 0; i < r; i++) {
            rows[i] = chipCount(i, i, 0, c - 1, w);
        }
        for (int i = 0; i < c; i++) {
            cols[i] = chipCount(0, r - 1, i, i, w);
        }


        List<Pair> rowIntervals = new ArrayList<>();
        if (!findIntervals(rows, h, totalChipsCount / (h + 1), rowIntervals)) {
            return false;
        }

        List<Pair> colIntervals = new ArrayList<>();
        if (!findIntervals(cols, v, totalChipsCount / (v + 1), colIntervals)) {
            return false;
        }

        return checkIntervals(rowIntervals, colIntervals, w, chipsOnSliceCount);
    }

    private static boolean checkIntervals(List<Pair> rowIntervals, List<Pair> colIntervals, int[][] w, int count) {
        for (int i = 0; i < rowIntervals.size(); i++) {
            for (int j = 0; j < colIntervals.size(); j++) {
                Pair rpair = rowIntervals.get(i);
                Pair cpair = colIntervals.get(j);
                if (chipCount(rpair.min, rpair.max, cpair.min, cpair.max, w) != count) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean findIntervals(int[] a, int razlom, int sum, List<Pair> intervals) {
        int cum = 0;
        int cnt = 0;
        int last = -1;

        for (int i = 0; i < a.length; i++) {
            cum += a[i];
            if (cum == sum) {
                cnt++;
                cum = 0;
                intervals.add(new Pair(last + 1, i));
                last = i;
            } else if (cum > sum) {
                return false;
            }
        }
        return (cnt == razlom + 1) && (cum == 0);
    }


    private static int chipCount(int rmin, int rmax, int cmin, int cmax, int[][] w) {
        int sum = 0;
        for (int r = rmin; r <= rmax; r++) {
            for (int c = cmin; c <= cmax; c++) {
                sum += w[r][c];
            }
        }
        return sum;
    }

    static class Pair {
        public final int min, max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
