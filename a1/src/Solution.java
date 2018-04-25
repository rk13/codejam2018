import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= t; i++) {
            String[] s = in.nextLine().split(" ");
            int[] a = Stream.of(s).mapToInt(Integer::parseInt).toArray();

            int[][] f = new int[a[0]][a[1]];
            for (int r = 0; r < a[0]; r++) {
                f[r] = in.nextLine().chars().map(x -> (char)x == '@' ? 1 : 0).toArray();
            }

            boolean r = process(f, a[0], a[1], a[2], a[3]);
            System.out.println("Case #" + i + ": " + (r ? "POSSIBLE" : "IMPOSSIBLE"));
        }
    }

    public static boolean process(int[][] f, int r, int c, int h, int v) {
        int[] rows = new int[r];
        int[] cols = new int[c];

        int chips = chipCount(0, r - 1, 0, c - 1, f);
        int chipsOnSlice = chips / ((h + 1) * (v + 1));

        if (chips == 0) {
            return true;
        }

        for (int i = 0; i < r; i++) {
            rows[i] = chipCount(i, i, 0, c - 1, f);
        }
        for (int i = 0; i < c; i++) {
            cols[i] = chipCount(0, r - 1, i, i, f);
        }

        List<Pair> rowIntervals = findIntervals(rows, h, chips / (h + 1));
        List<Pair> colIntervals = findIntervals(cols, v, chips / (v + 1));

        return checkIntervals(rowIntervals, colIntervals, f, chipsOnSlice);
    }

    private static boolean checkIntervals(List<Pair> rowIntervals, List<Pair> colIntervals, int[][] w, int count) {
        for (Pair rpair : rowIntervals) {
            for (Pair cpair : colIntervals) {
                if (chipCount(rpair.min, rpair.max, cpair.min, cpair.max, w) != count) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Pair> findIntervals(int[] a, int intervalCount, int chipsOnSlice) {
        int cumChips = 0;
        int last = -1;
        List<Pair> intervals = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            cumChips += a[i];
            if (cumChips >= chipsOnSlice) {
                cumChips = 0;
                intervals.add(new Pair(last + 1, i));
                last = i;
                if (intervals.size() >= intervalCount) {
                    break;
                }
            }
        }

        if (last != a.length - 1) {
            intervals.add(new Pair(last + 1, a.length - 1));
        }

        return intervals;
    }

    private static int chipCount(int rmin, int rmax, int cmin, int cmax, int[][] f) {
        int sum = 0;
        for (int r = rmin; r <= rmax; r++) {
            for (int c = cmin; c <= cmax; c++) {
                sum += f[r][c];
            }
        }
        return sum;
    }

    static class Pair {
        final int min, max;

        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
