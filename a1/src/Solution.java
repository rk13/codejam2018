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

    private static boolean process(int[][] f, int r, int c, int h, int v) {
        int[] rows = new int[r];
        int[] cols = new int[c];

        int chips = chipCount(0, r - 1, 0, c - 1, f);
        int chipsOnSlice = chips / ((h + 1) * (v + 1));

        if (chips == 0) {
            return true;
        }

        if (chipsOnSlice * (h + 1) * (v + 1) != chips) {
            return false;
        }

        for (int i = 0; i < r; i++) {
            rows[i] = chipCount(i, i, 0, c - 1, f);
        }
        for (int i = 0; i < c; i++) {
            cols[i] = chipCount(0, r - 1, i, i, f);
        }

        List<Pair> rowIntervals = new ArrayList<>();
        if (!findIntervals(rows, h, chips / (h + 1), rowIntervals)) {
            return false;
        }

        List<Pair> colIntervals = new ArrayList<>();
        if (!findIntervals(cols, v, chips / (v + 1), colIntervals)) {
            return false;
        }

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

    private static boolean findIntervals(int[] a, int slices, int chipsOnSlice, List<Pair> intervals) {
        int cumChips = 0;
        int slicesActual = 0;
        int last = -1;

        for (int i = 0; i < a.length; i++) {
            cumChips += a[i];
            if (cumChips == chipsOnSlice) {
                slicesActual++;
                cumChips = 0;
                intervals.add(new Pair(last + 1, i));
                last = i;
            } else if (cumChips > chipsOnSlice) {
                return false;
            }
        }
        return (slicesActual == slices + 1) && (cumChips == 0);
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
        final int min, max;

        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
